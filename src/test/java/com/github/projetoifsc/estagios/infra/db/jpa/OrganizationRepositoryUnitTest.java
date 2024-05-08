package com.github.projetoifsc.estagios.infra.db.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.github.projetoifsc.estagios.core.IOrganization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OrganizationRepositoryUnitTest {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    OrganizationDBImpl organizationDB;

    Faker faker = new Faker();
    GeradorCnpj geradorCnpj = new GeradorCnpj();
    OrgMocker orgMocker = new OrgMocker(faker, geradorCnpj);

    ObjectMapper jsonMapper = new ObjectMapper();
    ModelMapper modelMapper = new ModelMapper();

//    @Autowired
//    public OrganizationEntityRepositoryUnitTest(OrganizationRepository organizationRepository) {
//        this.organizationRepository = organizationRepository;
//    }

    OrganizationEntity organizationEntity;

    @BeforeEach
    void setUp() {
        organizationEntity = orgMocker.generate();
    }

    @Test
    void saveOrganization() {
        for (int i = 0; i < 10; i++) {
            organizationEntity = orgMocker.generate();
            var saved = organizationRepository.save(organizationEntity);
            System.out.println(saved);
        }
    }


    @Test
    @Transactional
    void updateOrganization() {
//        var org = organizationRepository.findById(6L, PrivateOrgProfileProjection.class).get();
//        org.ie = !org.ie;
//        var saved = organizationRepository.save(org);
//        var retrieved = organizationRepository.findById(6L).get();
//        System.out.println(retrieved);
    }

    @Test
    @Transactional
    void findAll() {
        var response = organizationRepository.findAll(PageRequest.of(1, 20));
        //System.out.println(response.stream().count());
        System.out.println(response.getTotalElements());
        System.out.println(response.getTotalPages());
        System.out.println(response.getNumberOfElements());
        System.out.println(response.getSize());
        System.out.println(response.getNumber());
        System.out.println(response.getContent());

    }

    @Test
    void findAllUnpaged() {
        var response = organizationRepository.findAll(Pageable.unpaged());
        System.out.println(response);
        System.out.println(response.stream().count());
        System.out.println(response.getContent());
        response.getContent().forEach(org -> System.out.println(org));
    }

    public static class LocalDTO implements IOrganization {

        private String id;
        private String nome;
        private boolean ie;
        private String info;
        private String website;
        private String redesSociais;

        public LocalDTO() {
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public void setId(String id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public Boolean getIe() {
            return ie;
        }

        public void setIe(boolean ie) {
            this.ie = ie;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getRedesSociais() {
            return redesSociais;
        }

        public void setRedesSociais(String redesSociais) {
            this.redesSociais = redesSociais;
        }

    }


    @Test
    void findByIdPublicProfile() {

        var projection = organizationRepository.findById(5, PublicOrgProfileProjection.class);
        System.out.println(projection.get());

        System.out.println("Projection: ");
        try {
            System.out.println(jsonMapper.writeValueAsString(projection.get()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        var mapped = modelMapper.map(projection.get(), LocalDTO.class);
        System.out.println("Mapped object");
        try {
            System.out.println(jsonMapper.writeValueAsString(mapped));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(mapped.id, projection.get().getId());
        assertEquals(mapped.nome,projection.get().getNome());

    }




}
