package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.*;
import com.github.projetoifsc.estagios.core.dto.OrganizationImpl;
import com.github.projetoifsc.estagios.core.dto.JobImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JobEntityWriteOperationsUnitTest {

    IJobDB jobRepository = mock();
    IOrganizationDB organizationRepository = mock();
    JobReadOperations jobReadOperations;

    JobWriteOperations service = new JobWriteOperations(jobReadOperations, jobRepository, organizationRepository);

    IJobEntryData job;
    IOrganization organization;

    @BeforeEach
    void setUp() {
        organization = new OrganizationImpl("1", false);

        job = new JobImpl();
        job.setId("1");
        job.setOwner(organization);

    }


    @Test
    void createsTraineeshipReturnsInterface() {
        when(organizationRepository.findById(organization.getId()))
                .thenReturn(organization);

        when(jobRepository.saveAndGetId(job))
                .thenReturn(job.getId());

        assertInstanceOf(IJob.class, service.create(organization.getId(), job));
    }


    @Test
    void createdTraineeshipHasOrganizationAsOwner() {
        when(organizationRepository.findById(organization.getId()))
                .thenReturn(organization);

        when(jobRepository.saveAndGetId(job))
                .thenReturn(job.getId());

        job.setOwner(null);
        assertEquals(organization,
                service.create(organization.getId(), job).getOwner());
    }


    @Test
    void schoolsCanBeReceivers() {

        var schoolA = new OrganizationImpl("2", true);
        var schoolB = new OrganizationImpl("3", true);

        when(organizationRepository.findAllById(List.of(schoolA.getId(), schoolB.getId())))
                .thenReturn(List.of(schoolA,schoolB));

        job.setReceiversIds(List.of(schoolA.getId(), schoolB.getId()));

        when(jobRepository.saveAndGetId(job))
                .thenReturn(job.getId());

        var created = service.create(organization.getId(), job);

        when(jobRepository.getExclusiveReceiversForJob(created.getId()))
                .thenReturn(List.of(schoolA, schoolB));

        var receivers = jobRepository.getExclusiveReceiversForJob(created.getId());

        assertTrue(receivers.contains(schoolA));
        assertTrue(receivers.contains(schoolB));
        assertEquals(2, receivers.size());

    }


    @Test
    void whenHasInvalidReceiverThrowsInvalidReceiverException() {

        var schoolA = new OrganizationImpl("2", true);
        var schoolB = new OrganizationImpl("3", true);
        var notSchool = new OrganizationImpl("4", false);

        when(organizationRepository.findAllById(List.of(schoolA.getId(), schoolB.getId(),notSchool.getId())))
                .thenReturn(List.of(schoolA,schoolB, notSchool));

        job.setReceiversIds(List.of(schoolA.getId(), schoolB.getId(),notSchool.getId()));

        assertThrows(InvalidReceiverException.class,
                ()->service.create(organization.getId(), job));
    }


    @Test
    void updatesTraineeshipReturnsInterface() {
        when(organizationRepository.findById(organization.getId()))
                .thenReturn(organization);
        when(jobRepository.getBasicInfoById(job.getId())).thenReturn(job);

        when(jobRepository.saveAndGetId(job))
                .thenReturn(job.getId());

        assertInstanceOf(IJob.class, service.update(organization.getId(), job.getId(), job));
    }


    @Test
    void updatedTraineeshipHasOrganizationAsOwner() {
        when(jobRepository.getBasicInfoById(job.getId()))
                .thenReturn(job);
        when(organizationRepository.findById(organization.getId()))
                .thenReturn(organization);
        when(jobRepository.saveAndGetId(job))
                .thenReturn(job.getId());

        assertEquals(organization,
                service.update(organization.getId(), job.getId(), job).getOwner());
    }


    @Test
    void updateTraineeshipSanitizesReceiversList() {
        when(organizationRepository.findById(organization.getId()))
                .thenReturn(organization);

        when(jobRepository.getBasicInfoById(job.getId()))
                .thenReturn(job);

        var schoolA = new OrganizationImpl("2", true);
        var schoolB = new OrganizationImpl("3", true);

        when(organizationRepository.findAllById(List.of(schoolA.getId(), schoolB.getId())))
                .thenReturn(List.of(schoolA,schoolB));

        job.setReceiversIds(List.of(schoolA.getId(), schoolB.getId()));

        when(jobRepository.saveAndGetId(job))
                .thenReturn(job.getId());

        var updated = service.update(organization.getId(), job.getId(), job);

        when(jobRepository.getExclusiveReceiversForJob(updated.getId()))
                .thenReturn(List.of(schoolA, schoolB));

        var receivers = jobRepository.getExclusiveReceiversForJob(updated.getId());

        assertTrue(receivers.contains(schoolA));
        assertTrue(receivers.contains(schoolB));
        assertEquals(2, receivers.size());

    }


    @Test
    void whenUpdatedHasInvalidReceiverThrowsInvalidReceiverException() {
        when(organizationRepository.findById(organization.getId()))
                .thenReturn(organization);

        when(jobRepository.getBasicInfoById(job.getId()))
                .thenReturn(job);

        var schoolA = new OrganizationImpl("2", true);
        var schoolB = new OrganizationImpl("3", true);
        var notSchool = new OrganizationImpl("4", false);

        when(organizationRepository.findAllById(List.of(schoolA.getId(), schoolB.getId(),notSchool.getId())))
                .thenReturn(List.of(schoolA,schoolB, notSchool));

        job.setReceiversIds(List.of(schoolA.getId(), schoolB.getId(),notSchool.getId()));

        assertThrows(InvalidReceiverException.class,
                ()->service.update(organization.getId(), job.getId(), job));
    }



    @Test
    void tryToUpdateTraineeshipFromOtherOrganizationThrowsUnauthorized () {
        var otherJob = new JobImpl();
        otherJob.setOwner(new OrganizationImpl("3", true));
        when(jobRepository.getBasicInfoById("2")).thenReturn(otherJob);
        when(organizationRepository.findById(organization.getId())).thenReturn(organization);

        assertThrows(UnauthorizedAccessException.class, ()->service.update(
                organization.getId(),
                "2",
                job
        ));
    }


    @Test
    void canDeleteTraineeship() {
        when(organizationRepository.findById(organization.getId()))
                .thenReturn(organization);
        when(jobRepository.getBasicInfoById(job.getId())).thenReturn(job);

        assertDoesNotThrow(()->service.delete(organization.getId(), job.getId()));
    }


    @Test
    void tryDeleteTraineeshipFromOtherOrgThrowsUnauthorized() {
        var otherTraineeship = new JobImpl();
        otherTraineeship.setOwner(new OrganizationImpl("3", true));
        when(jobRepository.getBasicInfoById("2")).thenReturn(otherTraineeship);
        when(organizationRepository.findById(organization.getId())).thenReturn(organization);

        assertThrows(UnauthorizedAccessException.class,
                ()->service.delete(organization.getId(), "2")
        );
    }



}
