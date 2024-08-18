package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.app.service.VagaService;
import com.github.projetoifsc.estagios.core.IJobDAO;
import com.github.projetoifsc.estagios.core.IJobUseCases;
import com.github.projetoifsc.estagios.core.models.projections.JobPrivateDetailsProjection;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import com.github.projetoifsc.estagios.core.models.projections.JobPublicDetailsProjection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class JobDAOImplTest {

    JobMocker mocker =
            new JobMocker(new Faker(new Locale("pt-Br")));

    private final JobDAOImpl jobDAOImpl;
    private final Mapper mapper;
    private final JsonParser jsonParser;

    JobEntity jobEntity;

    @Autowired
    JobDAOImplTest(JobDAOImpl jobDAOImpl, Mapper mapper, JsonParser jsonParser) {
        this.jobDAOImpl = jobDAOImpl;
        this.mapper = mapper;
        this.jsonParser = jsonParser;
    }

    @BeforeEach
    void setUp() {
        jobEntity = mocker.generate();
    }


    @Test
    void getJobBasicInfoByIdReturnsEntityOrThrowsException() {
        assertDoesNotThrow(() -> jobDAOImpl.getJobSummary("13"));
        assertThrows(Exception.class, () -> jobDAOImpl.getJobSummary("0"));
    }


    @Test
    void getJobPublicDetails() {
        String id = "16";
        //var publicProjection = jobDBImpl.getPublicDetails(id);
        var publicProjection = jobDAOImpl.getJobPublicDetails(id);
        jsonParser.printValue(publicProjection);
//        assertFalse(jsonString.contains("receivers"));
        assertInstanceOf(JobPublicDetailsProjection.class, publicProjection);
    }

    @Test
    void getModerationInfo() {
        var info = jobDAOImpl.getModerationInfo("427", "8");
        jsonParser.printValue(info);

    }

    @Test
    void getJobPrivateDetails() {
        String id = "90";

        var privateProjection = jobDAOImpl.getJobPrivateDetails(id);
        jsonParser.printValue(privateProjection);
        assertTrue(jsonParser.valueAsString(privateProjection).contains("receivers"));
        assertInstanceOf(JobPrivateDetailsProjection.class, privateProjection);
    }


//    @Test
//    void approveJob() {
//        jobDAORead.setJobApprovedByOrg("34", "378");
//    }
//
//    @Test
//    void rejectJob() {
//        jobDAORead.setJobRejectedByOrg("36", "378");
//    }



    @Test
    void getAllRejected() {
        var id = "397";
        var vagas = jobDAOImpl.getAllRejectedBy(id);
        System.out.println(vagas.getContent().size());
        jsonParser.printValue(vagas);
    }


    @Test
    void getAllPending() {
        var id = "397";
        var vagas = jobDAOImpl.getAllToBeModeratedBy(id);
        System.out.println(vagas.getContent().size());
        jsonParser.printValue(vagas);
    }

    @Test
    void getAllCreated() {
        var id = "198";
        var vagas = jobDAOImpl.getAllCreatedBy(id);
        System.out.println(vagas.getNumberOfElements());
        jsonParser.printValue(vagas);
    }

    @Test
    void getAllAvailable() {
        var id = "415";
        var available = jobDAOImpl.getAllCreatedOrApprovedBy(id);
        System.out.println(available.getContent().size());
        System.out.println(available.getContent().get(0).getOwner().getId());
        jsonParser.printValue(available);
    }


    @Test
    void isJobOfferedToOrg() {
        var orgId = "397";
        var jobId = "8";
        var response = jobDAOImpl.isJobOfferedToOrg(jobId, orgId);
        System.out.println("Is Job Offered To Org? " + response);
    }



}
