package com.github.projetoifsc.estagios.infra.db.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import com.github.projetoifsc.estagios.app.interfaces.OrgPrivateProfileProjection;
import com.github.projetoifsc.estagios.app.view.OrgPrivateProfileBasicView;
import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.utils.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@SpringBootTest
class OrganizationDBImplTest {

    OrgMocker orgMocker = new OrgMocker(new Faker(new Locale("pt-BR")), new GeradorCnpj());

    @Autowired
    OrganizationRepository repository;

    @Autowired
    OrganizationDBImpl organizationDB;

    @Autowired
    Mapper mapper;

    ObjectMapper jsonMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .build();

    @Autowired
    private OrganizationRepository organizationRepository;


    IOrganization org;

    @BeforeEach
    void setUp() {
        org = organizationRepository.save(orgMocker.generate());
    }

    @Test
    void findByIdReturnsBasicInfoOrNull() {

        var dto = organizationDB.findById(org.getId());

        try {
            System.out.println(jsonMapper.writeValueAsString(dto));
        } catch (Exception e) {
            System.out.println(
                    e.getMessage()
            );
        }

    }

    @Test
    void findAllByIdReturnsBasicInfoOrEmptyList() {
        var ids = List.of(org.getId());
        var dtos = organizationDB.findAllById(ids);

        try {
            System.out.println(jsonMapper.writeValueAsString(dtos));
        } catch (Exception e) {
            System.out.println(
                    e.getMessage()
            );
        }


    }

    @Test
    void findByUsernameReturnsBasicInfoOrNull() {
        var ent = mapper.map(org, OrgPrivateProfileBasicView.class);
        var dto = organizationDB.findByUsername(ent.getUsername());

        try {
            System.out.println(jsonMapper.writeValueAsString(dto));
        } catch (Exception e) {
            System.out.println(
                    e.getMessage()
            );
        }

    }

    @Test
    void saveReturnsPrivateProfile() {
        var entity = new OrgMocker().generateWithIdAsZero();
        var organizationEntity = mapper.map(entity, OrgPrivateProfileBasicView.class);
        var saved = organizationDB.save(organizationEntity);

        try {
            System.out.println(jsonMapper.writeValueAsString(saved));
        } catch (Exception e) {
            System.out.println(
                    e.getMessage()
            );
        }

    }

    @Test
    void delete() {
        organizationDB.delete(org.getId());
    }


    @Test
    void getPublicProfile() {

        var dto = organizationDB.getPublicProfile(org.getId());

        try {
            System.out.println(jsonMapper.writeValueAsString(dto));
        } catch (Exception e) {
            System.out.println(
                    e.getMessage()
            );
        }

    }


    @Test
    void getPrivateProfile() {

        var dto = organizationDB.getPrivateProfile(org.getId());

        try {
            System.out.println(jsonMapper.writeValueAsString(dto));
        } catch (Exception e) {
            System.out.println(
                    e.getMessage()
            );
        }

    }

    @Test
    void getCreatedJobsIsPaginated() {

    }

    @Test
    void getExclusiveReceivedJobsIsPaginated() {

    }

    @Test
    void getCreatedJobsReturnsJobsPrivateInfo() {

    }

    @Test
    void getExclusiveReceivedJobsReturnsJobsPublicInfo() {

    }


    @Test
    void getAllPublicProfileIsPaginated() {

        var dtos = organizationDB.getAllPublicProfile();

        try {
            System.out.println(jsonMapper.writeValueAsString(dtos));
        } catch (Exception e) {
            System.out.println(
                    e.getMessage()
            );
        }

    }


    @Test
    void getSchoolsPublicProfileIsPaginated() {

        var dtos = organizationDB.getSchoolsPublicProfile();

        try {
            System.out.println(jsonMapper.writeValueAsString(dtos));
        } catch (Exception e) {
            System.out.println(
                    e.getMessage()
            );
        }
    }


}
