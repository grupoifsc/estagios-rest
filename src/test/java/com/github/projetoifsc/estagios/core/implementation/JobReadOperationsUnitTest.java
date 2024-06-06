package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IOrganizationDAO;
import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.core.IJobDAO;
import com.github.projetoifsc.estagios.core.dto.OrganizationImpl;
import com.github.projetoifsc.estagios.core.dto.JobImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JobReadOperationsUnitTest {

    IJobDAO jobDB = mock();
    IOrganizationDAO organizationDB = mock();

    JobReadOperations service = new JobReadOperations(jobDB, organizationDB);

    IOrganization school;
    IOrganization enterprise;
    IJob traineeship;
    IOrganization organization;

    @BeforeEach
    void setUp() {
        school = new OrganizationImpl("1", true);
        enterprise = new OrganizationImpl("2", false);
        organization = new OrganizationImpl("1", false);

        traineeship = new JobImpl();
        traineeship.setId("1");
        traineeship.setOwner(organization);

    }


    @Test
    void givenOwnerButNotReceiver_whenSeeJobPublicDetails_thenSucceeds() {
        traineeship.setOwner(organization);

        when(organizationDB.findById(organization.getId())).thenReturn(organization);
        when(jobDB.getPublicDetails(traineeship.getId())).thenReturn(traineeship);

        assertDoesNotThrow(() -> service.getOnePublicDetails(organization.getId(), traineeship.getId()));
    }


    @Test
    void givenReceiverButNotOwner_whenSeeJobPublicDetails_thenSucceeds() {
        traineeship.setOwner(organization);
        var school = new OrganizationImpl("3", true);

        when(organizationDB.findById(school.getId()))
                .thenReturn(school);
        when(jobDB.getBasicInfo(traineeship.getId()))
                .thenReturn(traineeship);
        when(jobDB.getExclusiveReceiversForJob(traineeship.getId()))
                .thenReturn(List.of(school));
        when(jobDB.getPublicDetails(traineeship.getId()))
                .thenReturn(traineeship);

        assertDoesNotThrow(() -> service.getOnePublicDetails(school.getId(), traineeship.getId()));
    }


    @Test
    void givenNotOwnerOrReceiver_whenSeeJobPublicDetails_thenThrowsException() {

        traineeship.setOwner(new OrganizationImpl("5", true));
        var otherOrganization = new OrganizationImpl("3", true);

        when(organizationDB.findById(organization.getId()))
                .thenReturn(organization);
        when(jobDB.getPublicDetails(traineeship.getId()))
                .thenReturn(traineeship);
        when(jobDB.getExclusiveReceiversForJob(traineeship.getId()))
                .thenReturn(List.of(otherOrganization));

        assertThrows(UnauthorizedAccessException.class,
                ()-> service.getOnePublicDetails(
                        organization.getId(), traineeship.getId())
        );
    }



    @Test
    void givenNotOwner_whenSeeJobPrivateDetails_thenThrowsException() {
        when(organizationDB.findById(organization.getId())).thenReturn(organization);
        when(jobDB.getPrivateDetails(traineeship.getId())).thenReturn(traineeship);

        traineeship.setOwner(organization);
        assertDoesNotThrow(() -> service.getOnePrivateDetails(organization.getId(), traineeship.getId()));

        traineeship.setOwner(new OrganizationImpl("5", false));
        assertThrows(Exception.class,
                () -> service.getOnePrivateDetails(organization.getId(), traineeship.getId()));
    }


    @Test
    void givenNotSelf_whenGetCreatedJobs_thenThrowException() {
        assertThrows(UnauthorizedAccessException.class,
                ()->service.getAllCreatedSummary(school.getId(), enterprise.getId()));
        assertDoesNotThrow(()->service.getAllCreatedSummary(school.getId(), school.getId()));
    }



    @Test
    void givenNotIE_whenGetAllReceivedJobs_thenThrowsException() {

        when(organizationDB.findById(enterprise.getId())).thenReturn(enterprise);

        when(organizationDB.findById(school.getId())).thenReturn(school);
        when(jobDB.getExclusiveReceivedJobsSummaryForOrg(school.getId()))
                .thenReturn(List.of(new JobImpl(), new JobImpl()));
        when(jobDB.findAllPublicJobsSummary())
                .thenReturn(List.of(new JobImpl()));

//        when(jobDB.getAllCreatedJobsSummaryFromOrg(school.getId()))
//                .thenReturn(new PageImpl<>(List.of(new JobImpl(), new JobImpl())));

        assertDoesNotThrow(() -> service.getAllReceivedSummary(school));
        assertThrows(Exception.class, () -> service.getAllReceivedSummary(enterprise));

    }


    @Test
    void givenNotSelf_whenGetModeratedJobs_thenThrowException() {

        when(organizationDB.findById(school.getId())).thenReturn(school);
        when(organizationDB.findById(enterprise.getId())).thenReturn(enterprise);

        assertDoesNotThrow(()->service.getAllApprovedSummary(school.getId(), school.getId()));
        assertDoesNotThrow(()->service.getAllRejectedSummary(school.getId(), school.getId()));
        assertDoesNotThrow(()->service.getAllPendingSummary(school.getId(), school.getId()));

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
