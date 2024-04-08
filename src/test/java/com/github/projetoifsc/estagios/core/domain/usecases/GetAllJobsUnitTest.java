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

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GetAllJobsUnitTest {

    iJobRepository traineeshipRepository = mock();
    IOrganizationRepository organizationRepository = mock();

    GetAllJobs service = new GetAllJobs(traineeshipRepository, organizationRepository);

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
                .thenReturn(List.of(new JobImpl(), new JobImpl()));

        assertInstanceOf(List.class, service.getAllCreated(school.getId(), school.getId()));
        assertInstanceOf(iJob.class, service.getAllCreated(school.getId(), school.getId()).get(0));

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
                .thenReturn(List.of(new JobImpl(), new JobImpl()));

        when(traineeshipRepository.findAllWithoutReceivers())
                .thenReturn(List.of(new JobImpl()));

        assertInstanceOf(List.class, service.getAllReceived(school.getId(), school.getId()));
        assertInstanceOf(iJob.class, service.getAllReceived(school.getId(), school.getId()).get(0));

    }

    @Test
    void tryToGetOtherOrganizationReceivedTraineeshipsThrowsUnauthorized() {
        assertThrows(UnauthorizedAccessException.class,
                ()->service.getAllReceived(school.getId(), enterprise.getId()));
    }

}
