package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.domain.dto.OrganizationImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class GetAllOrganizationsUnitTest {

    IOrganizationRepository organizationRepository = mock();
    GetAllOrganizations service = new GetAllOrganizations(organizationRepository);

    @Test
    void getAllCallsPublicProfileAndReturnsList() {
        when(organizationRepository.getAllPublicProfile()).thenReturn(List.of(new OrganizationImpl("1", true), new OrganizationImpl("2", false)));
        assertInstanceOf(List.class, service.getAll());
    }

    @Test
    void getSchoolsCallsSchoolsPublicProfileAndReturnsList() {
        when(organizationRepository.getSchoolsPublicProfile()).thenReturn(List.of(new OrganizationImpl("1", true), new OrganizationImpl("2", true)));
        assertInstanceOf(List.class, service.getSchools());
    }



}
