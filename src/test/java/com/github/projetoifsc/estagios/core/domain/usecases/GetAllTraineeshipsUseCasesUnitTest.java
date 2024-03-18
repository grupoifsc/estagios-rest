package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IOrganization;
import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.domain.ITraineeship;
import com.github.projetoifsc.estagios.core.domain.ITraineeshipRepository;
import com.github.projetoifsc.estagios.core.domain.dto.OrganizationImpl;
import com.github.projetoifsc.estagios.core.domain.dto.TraineeshipImpl;
import com.github.projetoifsc.estagios.core.exceptions.UnauthorizedAccessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.org.checkerframework.framework.qual.DefaultQualifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GetAllTraineeshipsUseCasesUnitTest {

    ITraineeshipRepository traineeshipRepository = mock();
    IOrganizationRepository organizationRepository = mock();

    GetAllTraineeshipsUseCases service = new GetAllTraineeshipsUseCases(traineeshipRepository, organizationRepository);

    IOrganization school;
    IOrganization enterprise;

    @BeforeEach
    void setUp() {
        school = new OrganizationImpl("1", true);
        enterprise = new OrganizationImpl("2", false);
    }

    @Test
    void getAllCreatedReturnsList() {
        when(organizationRepository.getCreatedJobs(school.getId()))
                .thenReturn(List.of(new TraineeshipImpl(), new TraineeshipImpl()));

        assertInstanceOf(List.class, service.getAllCreated(school.getId(), school.getId()));
        assertInstanceOf(ITraineeship.class, service.getAllCreated(school.getId(), school.getId()).get(0));

    }


    @Test
    void tryToGetOtherOrganizationCreatedTraineeshipsThrowsUnauthorized() {
        assertThrows(UnauthorizedAccessException.class,
                ()->service.getAllCreated(school.getId(), enterprise.getId()));
    }


    // Pegar todas as vagas recebidas
    @Test
    void getAllReceivedReturnsList() {
        when(organizationRepository.getExclusiveReceivedJobs(school.getId()))
                .thenReturn(List.of(new TraineeshipImpl(), new TraineeshipImpl()));

        when(traineeshipRepository.findAllWithoutReceivers())
                .thenReturn(List.of(new TraineeshipImpl()));

        assertInstanceOf(List.class, service.getAllReceived(school.getId(), school.getId()));
        assertInstanceOf(ITraineeship.class, service.getAllReceived(school.getId(), school.getId()).get(0));

    }

    @Test
    void tryToGetOtherOrganizationReceivedTraineeshipsThrowsUnauthorized() {
        assertThrows(UnauthorizedAccessException.class,
                ()->service.getAllReceived(school.getId(), enterprise.getId()));
    }

}
