package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.core.IJobRepository;
import com.github.projetoifsc.estagios.core.dto.OrganizationImpl;
import com.github.projetoifsc.estagios.core.dto.JobImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JobWriteOperationsUnitTest {

    IJobRepository jobRepository = mock();
    IOrganizationRepository organizationRepository = mock();

    JobWriteOperations service = new JobWriteOperations(jobRepository, organizationRepository);

    IJob job;
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

        when(jobRepository.save(job))
                .thenReturn(job);

        assertInstanceOf(IJob.class, service.create(organization.getId(), job));
    }


    @Test
    void createdTraineeshipHasOrganizationAsOwner() {
        when(organizationRepository.findById(organization.getId()))
                .thenReturn(organization);

        when(jobRepository.save(job))
                .thenReturn(job);

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

        when(jobRepository.save(job))
                .thenReturn(job);

        var created = service.create(organization.getId(), job);

        assertTrue(created.getReceiversIds().contains(schoolA.getId()));
        assertTrue(created.getReceiversIds().contains(schoolB.getId()));
        assertEquals(2, created.getReceiversIds().size());

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
        when(jobRepository.findById(job.getId())).thenReturn(job);

        when(jobRepository.save(job))
                .thenReturn(job);

        assertInstanceOf(IJob.class, service.update(organization.getId(), job.getId(), job));
    }


    @Test
    void updatedTraineeshipHasOrganizationAsOwner() {
        when(jobRepository.findById(job.getId()))
                .thenReturn(job);
        when(organizationRepository.findById(organization.getId()))
                .thenReturn(organization);
        when(jobRepository.save(job))
                .thenReturn(job);

        assertEquals(organization,
                service.update(organization.getId(), job.getId(), job).getOwner());
    }


    @Test
    void updateTraineeshipSanitizesReceiversList() {
        when(organizationRepository.findById(organization.getId()))
                .thenReturn(organization);

        when(jobRepository.findById(job.getId()))
                .thenReturn(job);

        var schoolA = new OrganizationImpl("2", true);
        var schoolB = new OrganizationImpl("3", true);

        when(organizationRepository.findAllById(List.of(schoolA.getId(), schoolB.getId())))
                .thenReturn(List.of(schoolA,schoolB));

        job.setReceiversIds(List.of(schoolA.getId(), schoolB.getId()));

        when(jobRepository.save(job))
                .thenReturn(job);

        var updated = service.update(organization.getId(), job.getId(), job);

        assertTrue(updated.getReceiversIds().contains(schoolA.getId()));
        assertTrue(updated.getReceiversIds().contains(schoolB.getId()));
        assertEquals(2, updated.getReceiversIds().size());

    }


    @Test
    void whenUpdatedHasInvalidReceiverThrowsInvalidReceiverException() {
        when(organizationRepository.findById(organization.getId()))
                .thenReturn(organization);

        when(jobRepository.findById(job.getId()))
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
        when(jobRepository.findById("2")).thenReturn(otherJob);
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
        when(jobRepository.findById(job.getId())).thenReturn(job);

        assertDoesNotThrow(()->service.delete(organization.getId(), job.getId()));
    }


    @Test
    void tryDeleteTraineeshipFromOtherOrgThrowsUnauthorized() {
        var otherTraineeship = new JobImpl();
        otherTraineeship.setOwner(new OrganizationImpl("3", true));
        when(jobRepository.findById("2")).thenReturn(otherTraineeship);
        when(organizationRepository.findById(organization.getId())).thenReturn(organization);

        assertThrows(UnauthorizedAccessException.class,
                ()->service.delete(organization.getId(), "2")
        );
    }



}
