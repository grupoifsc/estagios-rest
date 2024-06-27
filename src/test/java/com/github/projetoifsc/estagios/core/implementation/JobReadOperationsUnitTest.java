package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.app.model.request.JobEntryData;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import com.github.projetoifsc.estagios.core.dto.JobPublicDetailsProjectionImpl;
import com.github.projetoifsc.estagios.core.models.IOrg;
import com.github.projetoifsc.estagios.core.IOrganizationDAO;
import com.github.projetoifsc.estagios.core.models.IJob;
import com.github.projetoifsc.estagios.core.IJobDAO;
import com.github.projetoifsc.estagios.core.dto.OrgImpl;
import com.github.projetoifsc.estagios.core.models.projections.JobPrivateDetailsProjection;
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

    IOrg school;
    IOrg enterprise;
    IJob traineeship;
    IOrg organization;

    @BeforeEach
    void setUp() {
        school = new OrgImpl("1", true);
        enterprise = new OrgImpl("2", false);
        organization = new OrgImpl("1", false);

        traineeship = new JobEntryData();
        traineeship.setId("1");
        traineeship.setOwner(organization);

    }


    @Test
    void givenOwnerButNotReceiver_whenSeeJobPublicDetails_thenSucceeds() {
        traineeship.setOwner(organization);
        var publicDetails =mapper.map(traineeship, JobPublicDetailsProjectionImpl.class);

        when(organizationDB.findById(organization.getId())).thenReturn(organization);
        when(jobDB.getJobPublicDetails(traineeship.getId())).thenReturn(publicDetails);

        assertDoesNotThrow(() -> service.getOnePublicDetails(organization.getId(), traineeship.getId()));
    }


    @Test
    void givenReceiverButNotOwner_whenSeeJobPublicDetails_thenSucceeds() {
        traineeship.setOwner(organization);
        var school = new OrgImpl("3", true);


        when(organizationDB.findById(school.getId()))
                .thenReturn(school);
        when(jobDB.getJobBasicInfo(traineeship.getId()))
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

        when(organizationDB.findById(organization.getId()))
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
        when(organizationDB.findById(organization.getId())).thenReturn(organization);
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



    @Test
    void givenNotIE_whenGetAllReceivedJobs_thenThrowsException() {

        when(organizationDB.findById(enterprise.getId())).thenReturn(enterprise);

        when(organizationDB.findById(school.getId())).thenReturn(school);
        when(jobDB.getExclusiveReceivedJobsSummaryForOrg(school.getId()))
                .thenReturn(List.of(new JobPublicDetailsProjectionImpl(), new JobPublicDetailsProjectionImpl()));
        when(jobDB.findAllPublicJobsSummary())
                .thenReturn(List.of(new JobPublicDetailsProjectionImpl()));

//        when(jobDB.getAllCreatedJobsSummaryFromOrg(school.getId()))
//                .thenReturn(new PageImpl<>(List.of(new JobImpl(), new JobImpl())));

        assertDoesNotThrow(() -> service.getAllReceivedSummary(school));
        assertThrows(Exception.class, () -> service.getAllReceivedSummary(enterprise));

    }


    @Test
    void givenNotSelf_whenGetModeratedJobs_thenThrowException() {

        when(organizationDB.findById(school.getId())).thenReturn(school);
        when(organizationDB.findById(enterprise.getId())).thenReturn(enterprise);

        assertDoesNotThrow(()->service.getAllAvailableSummary(school.getId(), school.getId()));
        assertDoesNotThrow(()->service.getAllApprovedSummary(school.getId(), school.getId()));
        assertDoesNotThrow(()->service.getAllRejectedSummary(school.getId(), school.getId()));
        assertDoesNotThrow(()->service.getAllPendingSummary(school.getId(), school.getId()));

        assertThrows(UnauthorizedAccessException.class,
                ()->service.getAllAvailableSummary(school.getId(), enterprise.getId()));
        assertThrows(UnauthorizedAccessException.class,
                ()->service.getAllApprovedSummary(school.getId(), enterprise.getId()));
        assertThrows(UnauthorizedAccessException.class,
                ()->service.getAllRejectedSummary(school.getId(), enterprise.getId()));
        assertThrows(UnauthorizedAccessException.class,
                ()->service.getAllPendingSummary(school.getId(), enterprise.getId()));
    }

    @Test
    void givenNotIE_whenGetModeratedJobs_thenThrowException() {

        when(organizationDB.findById(school.getId())).thenReturn(school);
        when(organizationDB.findById(enterprise.getId())).thenReturn(enterprise);

        assertDoesNotThrow(()->service.getAllApprovedSummary(school.getId(), school.getId()));
        assertDoesNotThrow(()->service.getAllRejectedSummary(school.getId(), school.getId()));
        assertDoesNotThrow(()->service.getAllPendingSummary(school.getId(), school.getId()));

        assertThrows(UnauthorizedAccessException.class,
                ()->service.getAllApprovedSummary(enterprise.getId(), enterprise.getId()));
        assertThrows(UnauthorizedAccessException.class,
                ()->service.getAllRejectedSummary(enterprise.getId(), enterprise.getId()));
        assertThrows(UnauthorizedAccessException.class,
                ()->service.getAllPendingSummary(enterprise.getId(), enterprise.getId()));
    }




}
