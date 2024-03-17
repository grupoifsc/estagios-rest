package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IOrganization;
import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.domain.ITraineeship;
import com.github.projetoifsc.estagios.core.domain.ITraineeshipRepository;
import com.github.projetoifsc.estagios.core.domain.dto.OrganizationImpl;
import com.github.projetoifsc.estagios.core.domain.dto.TraineeshipImpl;
import com.github.projetoifsc.estagios.core.exceptions.InvalidReceiverException;
import com.github.projetoifsc.estagios.core.exceptions.UnauthorizedAccessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateTraineeshipsUseCasesUnitTest {

    ITraineeshipRepository traineeshipRepository = mock();
    IOrganizationRepository organizationRepository = mock();

    CreateTraineeshipsUseCases service = new CreateTraineeshipsUseCases(traineeshipRepository, organizationRepository);

    ITraineeship traineeship;
    IOrganization organization;

    @BeforeEach
    void setUp() {
        organization = new OrganizationImpl("1", false);

        traineeship = new TraineeshipImpl();
        traineeship.setId("1");
        traineeship.setOwner(organization);

    }


    @Test
    void createsTraineeshipReturnsInterface() {
        when(organizationRepository.findById(organization.getId()))
                .thenReturn(organization);

        when(traineeshipRepository.save(traineeship))
                .thenReturn(traineeship);

        assertInstanceOf(ITraineeship.class, service.create(organization.getId(), traineeship));
    }


    @Test
    void createdTraineeshipHasOrganizationAsOwner() {
        when(organizationRepository.findById(organization.getId()))
                .thenReturn(organization);

        when(traineeshipRepository.save(traineeship))
                .thenReturn(traineeship);

        traineeship.setOwner(null);
        assertEquals(organization,
                service.create(organization.getId(), traineeship).getOwner());
    }


    @Test
    void schoolsCanBeReceivers() {

        var schoolA = new OrganizationImpl("2", true);
        var schoolB = new OrganizationImpl("3", true);

        when(organizationRepository.findAllById(List.of(schoolA.getId(), schoolB.getId())))
                .thenReturn(List.of(schoolA,schoolB));

        traineeship.setReceiversIds(List.of(schoolA.getId(), schoolB.getId()));

        when(traineeshipRepository.save(traineeship))
                .thenReturn(traineeship);

        var created = service.create(organization.getId(), traineeship);

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

        traineeship.setReceiversIds(List.of(schoolA.getId(), schoolB.getId(),notSchool.getId()));

        assertThrows(InvalidReceiverException.class,
                ()->service.create(organization.getId(),traineeship));
    }


    @Test
    void updatesTraineeshipReturnsInterface() {
        when(organizationRepository.findById(organization.getId()))
                .thenReturn(organization);
        when(traineeshipRepository.findById(traineeship.getId())).thenReturn(traineeship);

        when(traineeshipRepository.save(traineeship))
                .thenReturn(traineeship);

        assertInstanceOf(ITraineeship.class, service.update(organization.getId(), traineeship.getId(), traineeship));
    }


    @Test
    void updatedTraineeshipHasOrganizationAsOwner() {
        when(traineeshipRepository.findById(traineeship.getId()))
                .thenReturn(traineeship);
        when(organizationRepository.findById(organization.getId()))
                .thenReturn(organization);
        when(traineeshipRepository.save(traineeship))
                .thenReturn(traineeship);

        assertEquals(organization,
                service.update(organization.getId(), traineeship.getId(), traineeship).getOwner());
    }


    @Test
    void updateTraineeshipSanitizesReceiversList() {
        when(organizationRepository.findById(organization.getId()))
                .thenReturn(organization);

        when(traineeshipRepository.findById(traineeship.getId()))
                .thenReturn(traineeship);

        var schoolA = new OrganizationImpl("2", true);
        var schoolB = new OrganizationImpl("3", true);

        when(organizationRepository.findAllById(List.of(schoolA.getId(), schoolB.getId())))
                .thenReturn(List.of(schoolA,schoolB));

        traineeship.setReceiversIds(List.of(schoolA.getId(), schoolB.getId()));

        when(traineeshipRepository.save(traineeship))
                .thenReturn(traineeship);

        var updated = service.update(organization.getId(), traineeship.getId(), traineeship);

        assertTrue(updated.getReceiversIds().contains(schoolA.getId()));
        assertTrue(updated.getReceiversIds().contains(schoolB.getId()));
        assertEquals(2, updated.getReceiversIds().size());

    }


    @Test
    void whenUpdatedHasInvalidReceiverThrowsInvalidReceiverException() {
        when(organizationRepository.findById(organization.getId()))
                .thenReturn(organization);

        when(traineeshipRepository.findById(traineeship.getId()))
                .thenReturn(traineeship);

        var schoolA = new OrganizationImpl("2", true);
        var schoolB = new OrganizationImpl("3", true);
        var notSchool = new OrganizationImpl("4", false);

        when(organizationRepository.findAllById(List.of(schoolA.getId(), schoolB.getId(),notSchool.getId())))
                .thenReturn(List.of(schoolA,schoolB, notSchool));

        traineeship.setReceiversIds(List.of(schoolA.getId(), schoolB.getId(),notSchool.getId()));

        assertThrows(InvalidReceiverException.class,
                ()->service.update(organization.getId(), traineeship.getId(), traineeship));
    }



    @Test
    void tryToUpdateTraineeshipFromOtherOrganizationThrowsUnauthorized () {
        var otherTraineeship = new TraineeshipImpl();
        otherTraineeship.setOwner(new OrganizationImpl("3", true));
        when(traineeshipRepository.findById("2")).thenReturn(otherTraineeship);

        assertThrows(UnauthorizedAccessException.class, ()->service.update(
                organization.getId(),
                "2",
                traineeship
        ));
    }


    @Test
    void canDeleteTraineeship() {
        when(organizationRepository.findById(organization.getId()))
                .thenReturn(organization);
        when(traineeshipRepository.findById(traineeship.getId())).thenReturn(traineeship);

        assertDoesNotThrow(()->service.delete(organization.getId(), traineeship.getId()));
    }


    @Test
    void tryDeleteTraineeshipFromOtherOrgThrowsUnauthorized() {
        var otherTraineeship = new TraineeshipImpl();
        otherTraineeship.setOwner(new OrganizationImpl("3", true));
        when(traineeshipRepository.findById("2")).thenReturn(otherTraineeship);

        assertThrows(UnauthorizedAccessException.class,
                ()->service.delete(organization.getId(), "2")
        );
    }


    @Test
    void canGetPublicDetailsFromTraineeshipIfOwner() {
        traineeship.setOwner(organization);
        when(organizationRepository.findById(organization.getId())).thenReturn(organization);
        when(traineeshipRepository.findById(traineeship.getId())).thenReturn(traineeship);
        when(traineeshipRepository.getPublicDetails(traineeship.getId())).thenReturn(traineeship);

        assertInstanceOf(ITraineeship.class,
                service.getPublicDetails(organization.getId(), traineeship.getId())
        );
    }


    @Test
    void canGetPublicDetailsFromTraineeshipIfReceiver() {
        var school = new OrganizationImpl("3", true);

        when(organizationRepository.findById(school.getId()))
                .thenReturn(school);
        when(traineeshipRepository.findById(traineeship.getId()))
                .thenReturn(traineeship);
        when(traineeshipRepository.getReceivers(traineeship.getId()))
                .thenReturn(List.of(school));
        when(traineeshipRepository.getPublicDetails(traineeship.getId()))
                .thenReturn(traineeship);

        assertInstanceOf(ITraineeship.class,
                service.getPublicDetails(
                        school.getId(), traineeship.getId())
        );

    }


    @Test
    void tryToSeePublicDetailsOfTraineeshipIfNotOwnerOrReceiverThrowsUnauthorized() {

        when(organizationRepository.findById(organization.getId()))
                .thenReturn(organization);

        traineeship.setOwner(new OrganizationImpl("5", true));
        when(traineeshipRepository.findById(traineeship.getId()))
                .thenReturn(traineeship);

        var school = new OrganizationImpl("3", true);
        when(traineeshipRepository.getReceivers(traineeship.getId()))
                .thenReturn(List.of(school));

        assertThrows(UnauthorizedAccessException.class,
                ()-> service.getPublicDetails(
                        organization.getId(), traineeship.getId())
        );
    }


    @Test
    void canGetPrivateDetailsFromTraineeshipIfOwner() {
        traineeship.setOwner(organization);
        when(organizationRepository.findById(organization.getId())).thenReturn(organization);
        when(traineeshipRepository.findById(traineeship.getId())).thenReturn(traineeship);
        when(traineeshipRepository.getPrivateDetails(traineeship.getId())).thenReturn(traineeship);

        assertInstanceOf(ITraineeship.class,
                service.getPrivateDetails(organization.getId(), traineeship.getId())
        );
    }


    @Test
    void tryGetPrivateDetailsFromTraineeshipIfNotOwnerThrowsUnauthorized() {
        traineeship.setOwner(new OrganizationImpl("5", false));
        when(organizationRepository.findById(organization.getId())).thenReturn(organization);
        when(traineeshipRepository.findById(traineeship.getId())).thenReturn(traineeship);

        assertThrows(UnauthorizedAccessException.class,
                () -> service.getPrivateDetails(organization.getId(), traineeship.getId())
        );
    }


}
