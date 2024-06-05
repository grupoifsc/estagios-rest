package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import com.github.projetoifsc.estagios.app.model.interfaces.JobPrivateSummaryProjection;
import com.github.projetoifsc.estagios.app.model.interfaces.JobPublicSummaryProjection;
import com.github.projetoifsc.estagios.app.model.request.NewOrgProfileRequest;
import com.github.projetoifsc.estagios.app.model.response.PrivateOrgProfileResponse;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;


@SpringBootTest
class OrganizationDBImplTest {

    OrgMocker orgMocker = new OrgMocker(new Faker(new Locale("pt-BR")), new GeradorCnpj());

    @Autowired
    OrganizationRepository repository;

    @Autowired
    OrganizationDAOImpl organizationDB;

    @Autowired
    Mapper mapper;

    @Autowired
    JsonParser jsonParser;


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

        jsonParser.printValue(dto);

    }

    @Test
    void findAllByIdReturnsBasicInfoOrEmptyList() {
        var ids = List.of(org.getId());
        var dtos = organizationDB.findAllById(ids);

        jsonParser.printValue(dtos);

    }

    @Test
    void findByUsernameReturnsBasicInfoOrNull() {
        var ent = mapper.map(org, PrivateOrgProfileResponse.class);
        var dto = organizationDB.findByUsername(ent.getUsername());

        jsonParser.printValue(dto);

    }

    @Test
    void saveReturnsPrivateProfile() {
        var entity = new OrgMocker().generateWithIdAsZero();
        var newUser = mapper.map(entity, NewOrgProfileRequest.class);
        newUser.setEmail("teste@teste.com");
        newUser.setPassword("minhaSenha");
        jsonParser.printValue(newUser);
        var saved = organizationDB.save(newUser);

        jsonParser.printValue(saved);

    }

    @Test
    void delete() {
        jsonParser.printValue(org);
        organizationDB.delete(org.getId());
    }


    @Test
    void getPublicProfile() {

        var dto = organizationDB.getOnePublicProfile(org.getId());
        jsonParser.printValue(dto);

    }


    @Test
    void getOnePrivateProfile() {

        var dto = organizationDB.getOnePrivateProfile(org.getId());

        jsonParser.printValue(dto);

    }

    @Test
    void getAllCreatedJobsSummaryFromOrgIsPaginated() {

    }

    @Test
    void getExclusiveReceivedJobsSummaryForOrgIsPaginated() {

    }

    @Test
    void getCreatedJobsReturnsJobsSummaryPrivateInfo() {
        String id = "195";
        var createdJobs = organizationDB.getAllCreatedJobsSummaryFromOrg(id);
        createdJobs.getContent().forEach(
                job -> {
                    assertInstanceOf(JobPrivateSummaryProjection.class, job);
                    jsonParser.printValue(job);
                }
        );
    }

    @Test
    void getExclusiveReceivedJobsReturnsJobsSummaryPublicInfo() {
        String id = "274";
        var createdJobs = organizationDB.getExclusiveReceivedJobsSummaryForOrg(id);
        createdJobs.forEach(
                job -> {
                    assertInstanceOf(JobPublicSummaryProjection.class, job);
                    jsonParser.printValue(job);
                }
        );
    }



    @Test
    void getAllSchoolsPublicProfileIsPaginated() {

        var dtos = organizationDB.getAllSchoolsPublicProfile();

        jsonParser.printValue(dtos);
    }


    @Test
    void getMainAddressByOwnerId() {
        String id = "195";
        var mainAddr = organizationDB.getMainAddress(id);
        jsonParser.printValue(mainAddr);
    }

}
