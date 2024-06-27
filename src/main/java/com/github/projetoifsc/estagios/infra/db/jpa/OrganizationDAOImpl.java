package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.*;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import com.github.projetoifsc.estagios.core.models.IAddress;
import com.github.projetoifsc.estagios.core.models.IContact;
import com.github.projetoifsc.estagios.core.models.IOrg;
import com.github.projetoifsc.estagios.core.models.IOrgEntryData;
import com.github.projetoifsc.estagios.core.models.projections.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


// TODO: única forma que achei de fazer funcionar sem tornar muitas classes públicas foi coloca
// a anotação do Spring aqui... e assim perde toda a modularidade... aff... Fica aí o problema pra resolver
@Component
public class OrganizationDAOImpl implements IOrganizationDAO {

    private final OrganizationRepository organizationRepository;
    private final JobRepository jobRepository;
    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;
    private final UserCredentialsRepository userCredentialsRepository;
    private final Mapper mapper;
    private final JsonParser jsonParser;

    @Autowired
    public OrganizationDAOImpl(OrganizationRepository organizationRepository, JobRepository jobRepository, AddressRepository addressRepository, ContactRepository contactRepository, UserCredentialsRepository userCredentialsRepository, Mapper mapper, JsonParser jsonParser) {
        this.organizationRepository = organizationRepository;
        this.jobRepository = jobRepository;
        this.addressRepository = addressRepository;
        this.contactRepository = contactRepository;
        this.userCredentialsRepository = userCredentialsRepository;
        this.mapper = mapper;
        this.jsonParser = jsonParser;
    }


    @Override
    public IOrg findById(String id) {
        var optional = organizationRepository.findById(Long.parseLong(id), OrgSummaryProjection.class);
        return (IOrg) optional.orElseThrow(EntityNotFoundException::new);

    }


    @Override
    public List<IOrg> findAllById(List<String> ids) {

        List<Long> longIds = ids.stream()
                .map(Long::valueOf)
                .toList();

        var found =  organizationRepository.findAllByIdIn(longIds, OrgSummaryProjection.class);

        if(ids.size() == found.size()) {
            return found.stream()
                    .map(org -> (IOrg) org)
                    .toList();
        }

        List<String> notfound = new ArrayList<>(ids);
        for(OrgSummaryProjection org : found) {
            for(String id : notfound) {
                if(org.getId().equalsIgnoreCase(id)) {
                    notfound.remove(id);
                    break;
                }
            }
        }

        throw new EntityNotFoundException("Organizations not found: " + notfound);

    }


    @Override
    public IOrg findByUsername(String username) {
        var optionalOrganization = organizationRepository.findByUserCredentialsEmail(username, OrgSummaryProjection.class);
        return (IOrg) optionalOrganization.orElseThrow(EntityNotFoundException::new);
    }


    // Estou sacrificando um pouco de performance para poder continuar usando projeção com interfaces
    // E para não ter que mapear
    // TODO pesquisar se há outras soluções possíveis
    // Aqui vamos usar transaction para salvar os objetos "menores" tbm!
    @Override
    @Transactional
    public OrgPrivateProfileProjection save(IOrgEntryData organization) {
        var entity = mapper.map(organization, OrgEntity.class);

        entity.setUserCredentials(null);
        var savedEntity = organizationRepository.save(entity);

        var userCredentials = mapper.map(organization.getUserCredentials(), UserCredentialsEntity.class);

        // TODO: Talvez isso fique melhor lá na camada da API, que é onde controla a Autorização
        userCredentials.setRole(
                organization.getIe() ? "IE" : "EMPRESA"
        );
        userCredentials.setOrganization(savedEntity);
        userCredentialsRepository.save(userCredentials);

        // TODO Refactor: Olha uma dependẽncia escondida aqui!
        saveAddressAndContact((IOrgEntryData) organization, savedEntity);

        var dto = organizationRepository.findById(Long.parseLong(savedEntity.getId()), OrgPrivateProfileProjection.class);
        return dto.orElse(null);
    }


    private void saveAddressAndContact(IOrgEntryData dto, OrgEntity organization) {
        saveMainAddress(dto, organization);
        saveMainContact(dto, organization);
        saveApplianceContact(dto, organization);
    }


    private void saveMainContact(IOrgEntryData dto, OrgEntity org) {
        var mainContact = contactRepository.findFirstContactMainByOwner(org)
                .orElse(new ContactMainEntity());

        if (dto.getMainContact() != null)
            mapper.map(dto.getMainContact(), mainContact);

        mainContact.setOwner(org);
        contactRepository.save(mainContact);
    }


    private void saveApplianceContact(IOrgEntryData dto, OrgEntity org) {
        var applianceContact = contactRepository.findFirstContactApplianceByOwner(org)
                .orElse(new ContactApplianceEntity());

        if (dto.getApplianceContact() != null)
            mapper.map(dto.getMainContact(), applianceContact);

        applianceContact.setOwner(org);
        contactRepository.save(applianceContact);
    }


    void saveMainAddress(IOrgEntryData dto, OrgEntity org) {
        var mainAddress = addressRepository.findFirstAddressMainByOwnerId(Long.parseLong(org.getId()))
                    .orElse(new AddressMainEntity());

        if (dto.getMainAddress() != null)
            mapper.map(dto.getMainAddress(), mainAddress);

        mainAddress.setOwner(org);
        addressRepository.save(mainAddress);
    }

    @Override
    @Transactional
    public void delete(String id) {
        var optional = organizationRepository.findById(Long.parseLong(id), OrgEntity.class);
        var entity = optional.orElseThrow(EntityNotFoundException::new);
        organizationRepository.delete(entity);
    }

    @Override
    @Transactional
    public OrgPublicProfileProjection getOrgPublicProfile(String id) {
        var organizationProfile = organizationRepository.findById(Long.parseLong(id), OrgPublicProfileProjection.class);
        if(organizationProfile.isEmpty()) {
            throw new EntityNotFoundException("Organização não encontrada");
        }

        var org = organizationProfile.get();
        org.getMainAddress();
        org.getApplianceContact();
        org.getMainContact();

        return org;
    }

    @Override
    @Transactional
    public OrgPrivateProfileProjection getOrgPrivateProfile(String id) {
        var organizationProfile = organizationRepository.findById(Long.parseLong(id), OrgPrivateProfileProjection.class);

        if(organizationProfile.isEmpty()) {
            throw new EntityNotFoundException("Organização não encontrada");
        }

        var org = organizationProfile.get();
        org.getMainAddress();
        org.getApplianceContact();
        org.getMainContact();

        return org;
    }

    @Override
    public Page<OrgPublicProfileProjection> getAllSchoolsPublicProfile() {
        return organizationRepository.findAllByIe(true, PageRequest.of(0, 20), OrgPublicProfileProjection.class);
    }

    @Override
    public List<IOrg> getExclusiveReceiversForJob(String id) {
        return organizationRepository.findAllByExclusiveReceivedJobsId(Long.parseLong(id), IOrg.class);
    }


    @Override
    public IAddress getOrgMainAddress(String orgId) {
        return addressRepository.findFirstAddressMainByOwnerId(Long.parseLong(orgId))
                .orElseThrow(EntityNotFoundException::new);
    }

    // TODO Refactor: Contatos e Endereços está um lamaçal, precisa refatorar
    @Override
    public IContact getOrgMainContact(String orgId) {
        var optional = organizationRepository.findById(Long.parseLong(orgId), OrgEntity.class);
        var entity = optional.orElseThrow(EntityNotFoundException::new);
        return entity.getMainContact();
    }

    @Override
    public List<AddressDetailsProjection> getAllAddressesFromOrg(String orgId) {
        return addressRepository.findByOwnerId(Long.parseLong(orgId), AddressDetailsProjection.class);
    }

    @Override
    public List<ContactDetailsProjection> getAllContactsFromOrg(String orgId) {
        return contactRepository.findByOwnerId(Long.parseLong(orgId), ContactDetailsProjection.class);
    }

}
