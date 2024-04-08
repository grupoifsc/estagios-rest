package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IOrganization;
import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.domain.iJob;
import com.github.projetoifsc.estagios.core.domain.iJobRepository;
import com.github.projetoifsc.estagios.core.domain.dto.OrganizationImpl;
import com.github.projetoifsc.estagios.core.domain.dto.JobImpl;
import com.github.projetoifsc.estagios.core.exceptions.UnauthorizedAccessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetOneJobUnitTests {

    iJobRepository traineeshipRepository = mock();
    IOrganizationRepository organizationRepository = mock();

    GetOneJob service = new GetOneJob(traineeshipRepository, organizationRepository);

    iJob traineeship;
    IOrganization organization;

    @BeforeEach
    void setUp() {
        organization = new OrganizationImpl("1", false);

        traineeship = new JobImpl();
        traineeship.setId("1");
        traineeship.setOwner(organization);

    }



    @Test
    void canGetPublicDetailsFromTraineeshipIfOwner() {
        traineeship.setOwner(organization);
        when(organizationRepository.findById(organization.getId())).thenReturn(organization);
        when(traineeshipRepository.findById(traineeship.getId())).thenReturn(traineeship);
        when(traineeshipRepository.getPublicDetails(traineeship.getId())).thenReturn(traineeship);

        assertInstanceOf(iJob.class,
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

        assertInstanceOf(iJob.class,
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

        assertInstanceOf(iJob.class,
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
