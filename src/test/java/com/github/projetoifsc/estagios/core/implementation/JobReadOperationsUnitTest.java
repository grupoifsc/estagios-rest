package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.app.utils.Mapper;
import com.github.projetoifsc.estagios.core.dto.JobImpl;
import com.github.projetoifsc.estagios.core.dto.JobPublicDetailsProjectionImpl;
import com.github.projetoifsc.estagios.core.IOrganizationDAO;
import com.github.projetoifsc.estagios.core.IJobDAO;
import com.github.projetoifsc.estagios.core.dto.OrgImpl;
import com.github.projetoifsc.estagios.core.models.projections.JobPrivateDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.JobSummaryProjection;
import com.github.projetoifsc.estagios.core.models.projections.OrgSummaryProjection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JobReadOperationsUnitTest {

    IJobDAO jobDB = mock();
    IOrganizationDAO organizationDB = mock();
    Mapper mapper = new Mapper();

    JobReadOperations service = new JobReadOperations(jobDB, organizationDB);

    OrgSummaryProjection school;
    OrgSummaryProjection enterprise;
    JobSummaryProjection traineeship;
    OrgSummaryProjection organization;

    @BeforeEach
    void setUp() {
        school = new OrgImpl("1", true);
        enterprise = new OrgImpl("2", false);
        organization = new OrgImpl("1", false);

        traineeship = new JobImpl();
        traineeship.setId("1");
        traineeship.setOwner(organization);

    }


    @Test
    void givenOwnerButNotReceiver_whenSeeJobPublicDetails_thenSucceeds() {
        traineeship.setOwner(organization);
        var publicDetails =mapper.map(traineeship, JobPublicDetailsProjectionImpl.class);

        when(organizationDB.findByIdSummaryProjection(organization.getId())).thenReturn(organization);
        when(jobDB.getJobPublicDetails(traineeship.getId())).thenReturn(publicDetails);

        assertDoesNotThrow(() -> service.getOnePublicDetails(organization.getId(), traineeship.getId()));
    }


    @Test
    void givenReceiverButNotOwner_whenSeeJobPublicDetails_thenSucceeds() {
        traineeship.setOwner(organization);
        var school = new OrgImpl("3", true);


        when(organizationDB.findByIdSummaryProjection(school.getId()))
                .thenReturn(school);
        when(jobDB.getJobSummary(traineeship.getId()))
                .thenReturn(traineeship);
        when(organizationDB.getExclusiveReceiversForJob(traineeship.getId()))
                .thenReturn(List.of(school));
        when(jobDB.getJobPublicDetails(traineeship.getId()))
                .thenReturn(mapper.map(traineeship, JobPublicDetailsProjectionImpl.class));

        assertDoesNotThrow(() -> service.getOnePublicDetails(school.getId(), traineeship.getId()));
    }


    @Test
    void givenNotOwnerOrReceiver_whenSeeJobPublicDetails_thenThrowsException() {

        traineeship.setOwner(new OrgImpl("5", true));
        var otherOrganization = new OrgImpl("3", true);

        when(organizationDB.findByIdSummaryProjection(organization.getId()))
                .thenReturn(organization);
        when(jobDB.getJobPublicDetails(traineeship.getId()))
                .thenReturn(mapper.map(traineeship, JobPublicDetailsProjectionImpl.class));
        when(organizationDB.getExclusiveReceiversForJob(traineeship.getId()))
                .thenReturn(List.of(otherOrganization));

        assertThrows(UnauthorizedAccessException.class,
                ()-> service.getOnePublicDetails(
                        organization.getId(), traineeship.getId())
        );
    }



    @Test
    void givenNotOwner_whenSeeJobPrivateDetails_thenThrowsException() {
        when(organizationDB.findByIdSummaryProjection(organization.getId())).thenReturn(organization);
        when(jobDB.getJobPrivateDetails(traineeship.getId())).thenReturn(mapper.map(traineeship, JobPrivateDetailsProjection.class));

        traineeship.setOwner(organization);
        assertDoesNotThrow(() -> service.getOnePrivateDetails(organization.getId(), traineeship.getId()));

        traineeship.setOwner(new OrgImpl("5", false));
        assertThrows(Exception.class,
                () -> service.getOnePrivateDetails(organization.getId(), traineeship.getId()));
    }


    @Test
    void givenNotSelf_whenGetCreatedJobs_thenThrowException() {
        assertThrows(UnauthorizedAccessException.class,
                ()->service.getAllCreatedDetails(school.getId(), enterprise.getId()));
        assertDoesNotThrow(()->service.getAllCreatedDetails(school.getId(), school.getId()));
    }



//    @Test
//    void givenNotIE_whenGetAllReceivedJobs_thenThrowsException() {
//
//        when(organizationDB.findByIdSummaryProjection(enterprise.getId())).thenReturn(enterprise);
//
//        when(organizationDB.findByIdSummaryProjection(school.getId())).thenReturn(school);
//        when(jobDB.getExclusiveReceivedJobsSummaryForOrg(school.getId()))
//                .thenReturn(List.of(new JobPublicDetailsProjectionImpl(), new JobPublicDetailsProjectionImpl()));
//        when(jobDB.findAllPublicJobsSummary())
//                .thenReturn(List.of(new JobPublicDetailsProjectionImpl()));
//
////        when(jobDB.getAllCreatedJobsSummaryFromOrg(school.getId()))
////                .thenReturn(new PageImpl<>(List.of(new JobImpl(), new JobImpl())));
//
//        assertDoesNotThrow(() -> service.getAllReceivedSummary(school));
//        assertThrows(Exception.class, () -> service.getAllReceivedSummary(enterprise));
//
//    }
//

    @Test
    void givenNotSelf_whenGetModeratedJobs_thenThrowException() {

        when(organizationDB.findByIdSummaryProjection(school.getId())).thenReturn(school);
        when(organizationDB.findByIdSummaryProjection(enterprise.getId())).thenReturn(enterprise);

        assertDoesNotThrow(()->service.getAllAvailable(school.getId(), school.getId(), "", 0, 10));
//        assertDoesNotThrow(()->service.getAllApprovedSummary(school.getId(), school.getId()));
        assertDoesNotThrow(()->service.getAllRejected(school.getId(), school.getId()));
        assertDoesNotThrow(()->service.getAllPending(school.getId(), school.getId()));

        assertThrows(UnauthorizedAccessException.class,
                ()->service.getAllAvailable(school.getId(), enterprise.getId(), "", 0, 10));
//        assertThrows(UnauthorizedAccessException.class,
//                ()->service.getAllApprovedSummary(school.getId(), enterprise.getId()));
        assertThrows(UnauthorizedAccessException.class,
                ()->service.getAllRejected(school.getId(), enterprise.getId()));
        assertThrows(UnauthorizedAccessException.class,
                ()->service.getAllPending(school.getId(), enterprise.getId()));
    }

    @Test
    void givenNotIE_whenGetModeratedJobs_thenThrowException() {

        when(organizationDB.findByIdSummaryProjection(school.getId())).thenReturn(school);
        when(organizationDB.findByIdSummaryProjection(enterprise.getId())).thenReturn(enterprise);

//        assertDoesNotThrow(()->service.getAllApprovedSummary(school.getId(), school.getId()));
        assertDoesNotThrow(()->service.getAllRejected(school.getId(), school.getId()));
        assertDoesNotThrow(()->service.getAllPending(school.getId(), school.getId()));

//        assertThrows(UnauthorizedAccessException.class,
//                ()->service.getAllApprovedSummary(enterprise.getId(), enterprise.getId()));
        assertThrows(UnauthorizedAccessException.class,
                ()->service.getAllRejected(enterprise.getId(), enterprise.getId()));
        assertThrows(UnauthorizedAccessException.class,
                ()->service.getAllPending(enterprise.getId(), enterprise.getId()));
    }




}
