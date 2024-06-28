package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.core.models.IArea;
import com.github.projetoifsc.estagios.core.models.projections.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class ProjectionsTest {

    private final JsonParser jsonParser;

    private final OrganizationRepository organizationRepository;
    private final JobRepository jobRepository;
    private final AreaRepository areaRepository;
    private final UserCredentialsRepository userCredentialsRepository;
    private final ModeratedJobRepository moderatedJobRepository;
    private final ContactRepository contactRepository;
    private final AddressRepository addressRepository;

    private Object value;

    @Autowired
    ProjectionsTest(JsonParser jsonParser, OrganizationRepository organizationRepository, JobRepository jobRepository, AreaRepository areaRepository, UserCredentialsRepository userCredentialsRepository, ModeratedJobRepository moderatedJobRepository, ContactRepository contactRepository, AddressRepository addressRepository) {
        this.jsonParser = jsonParser;
        this.organizationRepository = organizationRepository;
        this.jobRepository = jobRepository;
        this.areaRepository = areaRepository;
        this.userCredentialsRepository = userCredentialsRepository;
        this.moderatedJobRepository = moderatedJobRepository;
        this.contactRepository = contactRepository;
        this.addressRepository = addressRepository;
    }

    @AfterEach
    void tearDown() {
        jsonParser.printValue(value);
    }

    @Test
    void getOneAreaProjection() {
        value = areaRepository.findFirstProjectedBy(IArea.class);
    }

    @Test
    void getAllAreaProjection() {
        value = areaRepository.findAllProjectedBy(IArea.class);
    }


    @Test
    void getOneAddressProjection() {
        value = addressRepository.findFirstProjectedBy(
                AddressDetailsProjection.class
        );
    }

    @Test
    void getAllAddressProjection() {
        value = addressRepository.findAllProjectedBy(AddressDetailsProjection.class);
    }

    @Test
    void getOneContactProjection() {
        value = contactRepository.findFirstProjectedBy(ContactDetailsProjection.class);
    }

    @Test
    void getAllContactProjection() {
        value = contactRepository.findAllProjectedBy(ContactDetailsProjection.class);
    }

//    @Test
//    void getOneUserCredentialsProjection() {
//        value = userCredentialsRepository.findFirstProjectedBy(OrgPrivateProfileProjection.UserCredentialsProjection.class);
//    }
//
//    @Test
//    void getAllUserCredentialsProjection() {
//        value = userCredentialsRepository.findAllProjectedBy(OrgPrivateProfileProjection.UserCredentialsProjection.class);
//    }

    @Test
    void getOneModeratedJobProjection() {
        value = moderatedJobRepository.findFirstProjectedBy(ModerationDetailsProjection.class);
    }

    @Test
    void getAllModeratedJobProjection() {
        value = moderatedJobRepository.findAllProjectedBy(ModerationDetailsProjection.class);
    }

//    @Test
//    void getOneOrgProjection() {
//        value = organizationRepository.findFirstProjectedBy(OrgSummaryProjection.class);
//        jsonParser.printValue(value);
//
//        value = organizationRepository.findFirstProjectedBy(OrgPublicProfileProjection.class);
//        jsonParser.printValue(value);
//
//        value = organizationRepository.findFirstProjectedBy(OrgPrivateProfileProjection.class);
//    }

//    @Test
//    void getAllOrgProjection() {
//        var pageable = PageRequest.of(0, 10);
//        value = organizationRepository.findAllProjectedBy(pageable,OrgSummaryProjection.class);
//        jsonParser.printValue(value);
//
//        value = organizationRepository.findAllProjectedBy(pageable, OrgPublicProfileProjection.class);
//        jsonParser.printValue(value);
//
//        value = organizationRepository.findAllProjectedBy(pageable, OrgPrivateProfileProjection.class);
//    }

    @Test
    void getOneJobProjection() {
        value = jobRepository.findFirstProjectedBy(JobSummaryProjection.class);
        jsonParser.printValue(value);

        value = jobRepository.findFirstProjectedBy(JobPublicDetailsProjection.class);
        jsonParser.printValue(value);

        value = jobRepository.findFirstProjectedBy(JobPrivateDetailsProjection.class);
    }

    @Test
    void getAllJobsProjection() {
        var pageable = PageRequest.of(0, 10);
        value = jobRepository.findAllProjectedBy(pageable, JobSummaryProjection.class);
        jsonParser.printValue(value);

        value = jobRepository.findAllProjectedBy(pageable, JobPublicDetailsProjection.class);
        jsonParser.printValue(value);

        value = jobRepository.findAllProjectedBy(pageable, JobPrivateDetailsProjection.class);
    }


    @Test
    void getJobTempProjection() {
        value = jobRepository.findFirstProjectedBy(JobWithReceiversTestProjection.class);
    }

}
