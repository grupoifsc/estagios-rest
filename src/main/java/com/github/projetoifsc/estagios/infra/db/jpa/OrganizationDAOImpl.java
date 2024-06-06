package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.app.model.interfaces.*;
import com.github.projetoifsc.estagios.core.*;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.app.utils.Mapper;
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
    public IOrganization findById(String id) {
        var optional = organizationRepository.findById(Long.parseLong(id), OrgBasicInfoProjection.class);
        return (IOrganization) optional.orElseThrow(EntityNotFoundException::new);

    }


    @Override
    public List<IOrganization> findAllById(List<String> ids) {

        List<Long> longIds = ids.stream()
                .map(Long::valueOf)
                .toList();

        var found =  organizationRepository.findAllByIdIn(longIds, OrgBasicInfoProjection.class);

        if(ids.size() == found.size()) {
            return found.stream()
                    .map(org -> (IOrganization) org)
                    .toList();
        }

        List<String> notfound = new ArrayList<>(ids);
        for(OrgBasicInfoProjection org : found) {
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
    public IOrganization findByUsername(String username) {
        var optionalOrganization = organizationRepository.findByUserCredentialsEmail(username, OrgBasicInfoProjection.class);
        return (IOrganization) optionalOrganization.orElseThrow(EntityNotFoundException::new);
    }


    // Estou sacrificando um pouco de performance para poder continuar usando projeção com interfaces
    // E para não ter que mapear
    // TODO pesquisar se há outras soluções possíveis
    // Aqui vamos usar transaction para salvar os objetos "menores" tbm!
    @Override
    @Transactional
    public IOrganization save(INewUser organization) {
        var entity = mapper.map(organization, OrganizationEntity.class);
        jsonParser.printValue(entity);
        var savedEntity = organizationRepository.save(entity);

        var userCredentials = mapper.map(organization, UserCredentialsEntity.class);
        // TODO: Talvez isso fique melhor lá na camada da API, que é onde controla a Autorização
        userCredentials.setRole(
                organization.getIe() ? "school" : "user"
        );
        userCredentials.setOrganization(savedEntity);
        jsonParser.printValue(userCredentials);
        userCredentialsRepository.save(userCredentials);



        // TODO Refactor: Olha uma dependẽncia escondida aqui!
        saveAddressAndContact((INewUser) organization, savedEntity);

        var dto = organizationRepository.findById(Long.parseLong(savedEntity.getId()), OrgPrivateProfileProjection.class);
        return (IOrganization) dto.orElse(null);
    }


    private void saveAddressAndContact(INewUser dto, OrganizationEntity organization) {
        saveMainAddress(dto, organization);
        saveMainContact(dto, organization);
        saveApplianceContact(dto, organization);
    }


    private void saveMainContact(INewUser dto, OrganizationEntity org) {
        var mainContact = contactRepository.findFirstContactMainByOwner(org)
                .orElse(new ContactMainEntity());

        if (dto.getMainContact() != null)
            mapper.map(dto.getMainContact(), mainContact);

        mainContact.setOwner(org);
        contactRepository.save(mainContact);
    }


    private void saveApplianceContact(INewUser dto, OrganizationEntity org) {
        var applianceContact = contactRepository.findFirstContactApplianceByOwner(org)
                .orElse(new ContactApplianceEntity());

        if (dto.getApplianceContact() != null)
            mapper.map(dto.getMainContact(), applianceContact);

        applianceContact.setOwner(org);
        contactRepository.save(applianceContact);
    }


    void saveMainAddress(INewUser dto, OrganizationEntity org) {
        var mainAddress = addressRepository.findFirstAddressMainByOwner(org)
                    .orElse(new AddressMainEntity());

        if (dto.getMainAddress() != null)
            mapper.map(dto.getMainAddress(), mainAddress);

        mainAddress.setOwner(org);
        addressRepository.save(mainAddress);
    }


    @Override
    @Transactional
    public void delete(String id) {
        var optional = organizationRepository.findById(Long.parseLong(id), OrganizationEntity.class);
        var entity = optional.orElseThrow(EntityNotFoundException::new);
        organizationRepository.delete(entity);
    }


    @Override
    public IOrganization getOnePublicProfile(String id) {
        var organizationProfile = organizationRepository.findById(Long.parseLong(id), OrgPublicProfileProjection.class);
        return organizationProfile.orElseThrow(EntityNotFoundException::new);
    }


    @Override
    public IOrganization getOnePrivateProfile(String id) {
        var organizationProfile = organizationRepository.findById(Long.parseLong(id), OrgPrivateProfileProjection.class);
        return organizationProfile.orElseThrow(EntityNotFoundException::new);
    }


    @Override
    public Page<IOrganization> getAllSchoolsPublicProfile() {
        var page = organizationRepository.findAllByIe(true, PageRequest.of(0, 20), OrgPublicProfileProjection.class);
        return page.map(org -> (IOrganization) org);
    }


    @Override
    public IAddress getMainAddress(String orgId) {
        return addressRepository.findFirstByOwnerId(Long.parseLong(orgId))
                .orElseThrow(EntityNotFoundException::new);
    }


    @Override
    public IContact getMainContact(String orgId) {
        var optional = organizationRepository.findById(Long.parseLong(orgId), OrganizationEntity.class);
        var entity = optional.orElseThrow(EntityNotFoundException::new);
        return entity.getMainContact();
    }

    @Override
    public List<IAddress> getAllAddresses(String orgId) {
        return List.of();
    }

    @Override
    public List<IContact> getAllContacts(String orgId) {
        return List.of();
    }


}
