package com.github.projetoifsc.estagios.infra.db.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import com.github.projetoifsc.estagios.app.interfaces.OrgPublicProfileProjection;
import com.github.projetoifsc.estagios.core.IOrganization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OrganizationRepositoryUnitTest {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    OrganizationDBImpl organizationDB;

    Faker faker = new Faker();
    GeradorCnpj geradorCnpj = new GeradorCnpj();
    OrgMocker orgMocker = new OrgMocker(faker, geradorCnpj);

    JsonMapper jsonMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .build();

    ModelMapper modelMapper = new ModelMapper();

//    @Autowired
//    public OrganizationEntityRepositoryUnitTest(OrganizationRepository organizationRepository) {
//        this.organizationRepository = organizationRepository;
//    }

    OrganizationEntity organizationEntity;

    @BeforeEach
    void setUp() {
        organizationEntity = orgMocker.generate();
        organizationEntity = organizationRepository.save(organizationEntity);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void saveOrganization() {
        for (int i = 0; i < 5; i++) {
            organizationEntity = orgMocker.generate();
            organizationRepository.save(organizationEntity);
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

        var projection = organizationRepository.findById(Long.parseLong(organizationEntity.getId()), OrgPublicProfileProjection.class);
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


    @Test
    @Transactional
    void findingById() {

        var entitiy = organizationRepository.findById(Long.parseLong(organizationEntity.getId())).orElse(null);

        System.out.println("Elemento recuperado do banco de dados com findById: ");
        try {
            System.out.println(jsonMapper.writeValueAsString(entitiy));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        
    }

    @Transactional
    @Rollback(value = false)
    @Test
    void lazyLoadingFromSaved() {
        var ent = organizationRepository.save(orgMocker.generate());

        var address = new AddressMain();
        address.setOwner(ent);
        address = addressRepository.save(address);

        var otheraddress = new AddressMain();
        otheraddress.setOwner(ent);
        otheraddress = addressRepository.save(otheraddress);

        var enderecos = addressRepository.findByOwner(ent);

        System.out.println(enderecos);

        var main = addressRepository.findFirstAddressMainByOwner(ent);

        System.out.println(main);

    }



}
