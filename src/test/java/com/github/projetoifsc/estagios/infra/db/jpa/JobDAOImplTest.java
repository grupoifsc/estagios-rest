package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import com.github.projetoifsc.estagios.app.model.interfaces.JobPrivateDetailsProjection;
import com.github.projetoifsc.estagios.app.model.interfaces.JobPublicDetailsProjection;
import com.github.projetoifsc.estagios.app.model.interfaces.JobPublicSummaryProjection;
import com.github.projetoifsc.estagios.app.model.request.NewJobRequest;
import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Rollback(value = false)
class JobDAOImplTest {

    JobMocker mocker =
            new JobMocker(new Faker(new Locale("pt-Br")));

    @Autowired
    JobDAOImpl jobDBImpl;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    OrganizationDAOImpl organizationDB;

    @Autowired
    AreaDAOImpl areaDB;

    @Autowired
    Mapper mapper;

    @Autowired
    JsonParser jsonParser;

    JobEntity jobEntity;


    @BeforeEach
    void setUp() {
        jobEntity = mocker.generate();
    }


    @Test
    void saveAndGetIdWithoutOtherEntities() {
        var entryData = mapper.map(jobEntity, NewJobRequest.class);
        var savedId = jobDBImpl.saveAndGetId(entryData);
        assertNotEquals(savedId, "0");
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void saveAndGetIdWithRelatedEntities() {

        var owner = organizationDB.findById("379");
        var mappedOwner = mapper.map(owner, OrganizationEntity.class);

//        var mainAddress = organizationDB.getMainAddress(owner.getId());
//        System.out.println(jsonParser.valueAsString(mainAddress));
//        var mappedAddress = mapper.map(mainAddress, AddressEntity.class);
//
//        var mainContact = organizationDB.getMainContact(owner.getId());
//        var mappedContact = mapper.map(mainContact, ContactEntity.class);

        var receivers = organizationDB.findAllById(List.of("272", "273", "274"));
        var mappedReceivers = receivers.stream().map(
                o -> mapper.map(o, OrganizationEntity.class)
        ).toList();

        var areaA = areaDB.getById("1");
        var areaB = areaDB.getById("2");
        var mappedAreaA = mapper.map(areaA, AreaEntity.class);
        var mappedAreaB = mapper.map(areaB, AreaEntity.class);

        jobEntity.setOwner(mappedOwner);
        jobEntity.setExclusiveReceivers(mappedReceivers);
        jobEntity.setAreas(List.of(mappedAreaA, mappedAreaB));
//        jobEntity.setAddress(mappedAddress);
//        jobEntity.setContact(mappedContact);

        var entryData = mapper.map(jobEntity, NewJobRequest.class);
        var savedId = jobDBImpl.saveAndGetId(entryData);
        assertNotEquals("0", savedId);

    }

    @Test
    void getBasicInfoByIdReturnsEntityOrThrowsException() {
        assertDoesNotThrow(() -> jobDBImpl.getBasicInfo("2"));
        assertThrows(Exception.class, () -> jobDBImpl.getBasicInfo("0"));
        assertInstanceOf(IJob.class, jobDBImpl.getBasicInfo("2"));
    }


    @Test
    void deleteOrThrowException() {
        assertDoesNotThrow(() -> jobDBImpl.delete("2"));
        assertThrows(Exception.class, () -> jobDBImpl.delete("1"));
    }


    @Test
    void getPublicDetails() {
        String id = "8";
        //var publicProjection = jobDBImpl.getPublicDetails(id);
        var publicProjection = jobDBImpl.getPublicDetails(id);
        var jsonString = jsonParser.valueAsString(publicProjection);
        assertFalse(jsonString.contains("receivers"));
        System.out.println(jsonString);
        assertInstanceOf(JobPublicDetailsProjection.class, publicProjection);
    }

    @Test
    void getPrivateDetails() {
        // É pra devolver tudo.. e pegar tbm as vagas...
        String id = "4";
        //var publicProjection = jobDBImpl.getPublicDetails(id);
        var privateProjection = jobDBImpl.getPrivateDetails(id);
        var jsonString = jsonParser.valueAsString(privateProjection);
        assertTrue(jsonString.contains("receivers"));
        System.out.println(jsonString);
        assertInstanceOf(JobPrivateDetailsProjection.class, privateProjection);
    }


    @Test
    void approveJob() {
        jobDBImpl.setJobApprovedByOrg("34", "378");
    }

    @Test
    void rejectJob() {
        jobDBImpl.setJobRejectedByOrg("36", "378");
    }


    @Test
    void getAllPublic() {
        var vagas = jobDBImpl.findAllPublicJobsSummary();
        System.out.println(vagas.size());
        jsonParser.printValue(vagas);
    }


    @Test
    void getAllApproved() {
        var id = "378";
        var approved = jobDBImpl.getAllApprovedSummaryFromOrg(id);
        System.out.println(approved.size());
        jsonParser.printValue(approved);
    }

    @Test
    void getAllRejected() {
        var id = "378";
        var vagas = jobDBImpl.getAllRejectedSummaryFromOrg(id);
        System.out.println(vagas.size());
        jsonParser.printValue(vagas);
    }


    @Test
    void getAllPending() {
        var id = "378";
        var vagas = jobDBImpl.getAllPendingSummaryFromOrg(id);
        System.out.println(vagas.size());
        jsonParser.printValue(vagas);
    }

    @Test
    void getAllCreated() {
        var id = "198";
        var vagas = jobDBImpl.getAllCreatedJobsSummaryFromOrg(id);
        System.out.println(vagas.getNumberOfElements());
        jsonParser.printValue(vagas);
    }

    @Test
    void getAllAvailable() {
        var id = "378";
        var available = jobDBImpl.getAllAvailableByOrg(id);
        System.out.println(available.size());
        jsonParser.printValue(available);
    }

    @Test
    void getExclusiveReceivedJobsReturnsJobsSummaryPublicInfo() {
        String id = "195";
        var received = jobDBImpl.getExclusiveReceivedJobsSummaryForOrg(id);
        System.out.println(received.size());
        jsonParser.printValue(received);
    }


}
