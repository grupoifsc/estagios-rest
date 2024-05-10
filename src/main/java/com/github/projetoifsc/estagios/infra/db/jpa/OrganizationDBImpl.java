package com.github.projetoifsc.estagios.infra.db.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.projetoifsc.estagios.app.interfaces.OrgPrivateProfileProjection;
import com.github.projetoifsc.estagios.app.interfaces.OrgPublicProfileProjection;
import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IOrganizationDB;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


// TODO Mapper Implementation a ser utilizada (mas não vamos fazer isso agora)


// TODO: única forma que achei de fazer funcionar sem tornar muitas classes públicas foi coloca
// a anotação do Spring aqui... e assim perde toda a modularidade... aff... Fica aí o problema pra resolver
@Component
class OrganizationDBImpl implements IOrganizationDB {

    JsonMapper jsonMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .build();

    OrganizationRepository organizationRepository;
    JobRepository jobRepository;
    AddressRepository addressRepository;
    ContactRepository contactRepository;

    // TODO arrumar esse mapper aqui.. Deixar escolher qual o mapper a utilizar
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public OrganizationDBImpl(OrganizationRepository organizationRepository, JobRepository jobRepository, AddressRepository addressRepository,
                              ContactRepository contactRepository) {
        this.organizationRepository = organizationRepository;
        this.jobRepository = jobRepository;
        this.addressRepository = addressRepository;
        configureModelMapper();
        this.contactRepository = contactRepository;
    }

    private void configureModelMapper() {
        modelMapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull())
                .setMethodAccessLevel(Configuration.AccessLevel.PACKAGE_PRIVATE);
    }

    @Override
    public IOrganization findById(String id) {
        var optional = organizationRepository.findById(Long.parseLong(id), OrgBasicInfoProjection.class);
        return optional.orElseThrow(EntityNotFoundException::new);
    }


    // TODO Como retornar erro se um id não existir?
    //  -> Vai ser na outra camada que vai ter que gerar erro...
    // Aqui que tá uma bosta... a exceção vai retornar na camada da lógica...
    // MAS ISSO É REGRA DE NEGÓCIO SIM !  OU NÃO É ?
    @Override
    public List<IOrganization> findAllById(List<String> ids) {

        List<Long> longIds = ids.stream()
                .map(Long::valueOf)
                .toList();

        var found =  organizationRepository.findByIdIn(longIds, OrgBasicInfoProjection.class);

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
        var optionalOrganization = organizationRepository.findByUsername(username, OrgBasicInfoProjection.class);
        return (IOrganization) optionalOrganization.orElseThrow(EntityNotFoundException::new);
    }


    // Estou sacrificando um pouco de performance para poder continuar usando projeção com interfaces
    // E para não ter que mapear
    // TODO pesquisar se há outras soluções possíveis
    // Aqui vamos usar transaction para salvar os objetos "menores" tbm!
    @Override
    @Transactional
    public IOrganization save(IOrganization organization) {
        var entity = modelMapper.map(organization, OrganizationEntity.class);
        var saved = organizationRepository.save(entity);

        // TODO: Olha uma dependẽncia escondida aqui!
        saveAddressAndContact((OrgPrivateProfileProjection) organization, saved);

        var dto = organizationRepository.findById(Long.parseLong(saved.getId()), OrgPrivateProfileProjection.class);
        return (IOrganization) dto.orElse(null);
    }

    private void saveAddressAndContact(OrgPrivateProfileProjection dto, OrganizationEntity organization) {
        saveMainAddress(dto, organization);
        saveMainContact(dto, organization);
        saveApplianceContact(dto, organization);
    }


    private void saveMainContact(OrgPrivateProfileProjection dto, OrganizationEntity org) {

        var mainContact = contactRepository.findFirstContactMainByOwner(org)
                .orElse(new ContactMain());

        if (dto.getMainContact() != null)
            modelMapper.map(dto.getMainContact(), mainContact);

        mainContact.setOwner(org);
        contactRepository.save(mainContact);

    }


    private void saveApplianceContact(OrgPrivateProfileProjection dto, OrganizationEntity org) {

        var applianceContact = contactRepository.findFirstContactApplianceByOwner(org)
                .orElse(new ContactAppliance());

        if (dto.getApplianceContact() != null)
            modelMapper.map(dto.getMainContact(), applianceContact);

        applianceContact.setOwner(org);
        contactRepository.save(applianceContact);

    }


    void saveMainAddress(OrgPrivateProfileProjection dto, OrganizationEntity org) {

        var mainAddress = addressRepository.findFirstAddressMainByOwner(org)
                    .orElse(new AddressMain());

        if (dto.getMainAddress() != null)
            modelMapper.map(dto.getMainAddress(), mainAddress);

        mainAddress.setOwner(org);
        addressRepository.save(mainAddress);

    }


    @Override
    @Transactional
    public void delete(String id) {
        var optional = organizationRepository.findById(Long.parseLong(id));
        var entity = optional.orElseThrow(EntityNotFoundException::new);
        organizationRepository.delete(entity);
    }

    @Override
    @Transactional
    public IOrganization getPublicProfile(String id) {
        var organizationProfile = organizationRepository.findById(Long.parseLong(id), OrgPublicProfileProjection.class);
        return (IOrganization) organizationProfile.orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public IOrganization getPrivateProfile(String id) {
        var organizationProfile = organizationRepository.findById(Long.parseLong(id), OrgPrivateProfileProjection.class);
        return (IOrganization) organizationProfile.orElseThrow(EntityNotFoundException::new);
    }


    // TODO: Já repensar aqui a questão da aprovação!
    // TODO Aceitar Pageable!
    @Override
    public Page<IJob> getCreatedJobs(String organizationId) {
        return null;
    }

    @Override
    public List<IJob> getExclusiveReceivedJobs(String organizationId) {
        return List.of();
    }

    // TODO Accept Pageable as Parameter
    @Override
    public Page<IOrganization> getAllPublicProfile() {
        var page = organizationRepository.findAllProjectedBy(PageRequest.of(0, 20), OrgPublicProfileProjection.class);
        return page.map(org -> (IOrganization) org);
    }


    @Override
    public Page<IOrganization> getSchoolsPublicProfile() {
        var page = organizationRepository.findByIe(true, PageRequest.of(0, 20), OrgPublicProfileProjection.class);
        return page.map(org -> (IOrganization) org);
    }

}
