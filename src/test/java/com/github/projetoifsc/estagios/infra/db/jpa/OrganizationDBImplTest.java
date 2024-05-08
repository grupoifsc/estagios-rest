package com.github.projetoifsc.estagios.infra.db.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.core.IOrganization;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
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


@SpringBootTest
class OrganizationDBImplTest {

    @Autowired
    OrganizationRepository repository;

    @Autowired
    OrganizationDBImpl organizationDB;

    ObjectMapper jsonMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .build();

    @Autowired
    private OrganizationRepository organizationRepository;


    @Test
    void findByIdReturnsBasicInfoOrNull() {

        var dto = organizationDB.findById("1");

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
        var ids = List.of("1", "2", "3", "10000");
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
        var dto = organizationDB.findByUsername("kilback");

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
        var organizationEntity = new OrgMocker().generate();
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

        organizationDB.delete("5");
        var dto = organizationDB.getPrivateProfile("5");

        try {
            System.out.println(jsonMapper.writeValueAsString(dto));
        } catch (Exception e) {
            System.out.println(
                    e.getMessage()
            );
        }

    }


    @Test
    void getPublicProfile() {

        var dto = organizationDB.getPublicProfile("1");


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

        var dto = organizationDB.getPrivateProfile("1");

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
