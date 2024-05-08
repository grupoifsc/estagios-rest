package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IOrganizationDB;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
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

    OrganizationRepository organizationRepository;
    JobRepository jobRepository;

    // TODO arrumar esse mapper aqui.. Deixar escolher qual o mapper a utilizar
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    OrganizationDBImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
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
    // TODO pesquisar se há outras soluções possíveis
    @Override
    public IOrganization save(IOrganization organization) {
        var entity = modelMapper.map(organization, OrganizationEntity.class);
        var saved = organizationRepository.save(entity);
        var dto = organizationRepository.findById(Long.parseLong(saved.getId()), PrivateOrgProfileProjection.class);
        return (IOrganization) dto.orElse(null);
    }

    @Override
    @Transactional
    public void delete(String id) {
        var optional = organizationRepository.findById(Long.parseLong(id));
        var entity = optional.orElseThrow(EntityNotFoundException::new);
        organizationRepository.delete(entity);
    }

    @Override
    public IOrganization getPublicProfile(String id) {
        var organizationProfile = organizationRepository.findById(Long.parseLong(id), PublicOrgProfileProjection.class);
        return (IOrganization) organizationProfile.orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public IOrganization getPrivateProfile(String id) {
        var organizationProfile = organizationRepository.findById(Long.parseLong(id), PrivateOrgProfileProjection.class);
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
        var page = organizationRepository.findAllProjectedBy(PageRequest.of(0, 20), PublicOrgProfileProjection.class);
        return page.map(org -> (IOrganization) org);
    }


    @Override
    public Page<IOrganization> getSchoolsPublicProfile() {
        var page = organizationRepository.findByIe(true, PageRequest.of(0, 20), PublicOrgProfileProjection.class);
        return page.map(org -> (IOrganization) org);
    }

}
