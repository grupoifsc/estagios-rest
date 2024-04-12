package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.dto.OrganizationImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class OrganizationReadOperationsUnitTest {

    IOrganizationRepository organizationRepository = mock();
    OrganizationReadOperations service = new OrganizationReadOperations(organizationRepository);

    IOrganization organizationA;
    IOrganization organizationB;

    @BeforeEach
    void setUp() {
        organizationA = new OrganizationImpl("1", false);
        organizationB = new OrganizationImpl("2", true);
    }

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


    @Test
    void seePrivateProfileReturnsInterface() {
        when(organizationRepository.getPrivateProfile(organizationA.getId())).thenReturn(organizationA);

        assertInstanceOf(IOrganization.class, service.getPrivateProfile(
                organizationA.getId(),
                organizationA.getId()
        ));
    }

    @Test
    void tryToSeeOtherOrganizationPrivateProfileThrowsUnauthorized() {
        assertThrows(UnauthorizedAccessException.class, ()->
                service.getPrivateProfile(
                        organizationA.getId(),
                        organizationB.getId()
                )
        );
    }


    @Test
    void seePublicProfileReturnsInterface() {
        when(organizationRepository.getPublicProfile(organizationA.getId())).thenReturn(organizationA);

        assertDoesNotThrow(()->service.getPublicProfile(
                        organizationA.getId(),
                        organizationA.getId()
                )
        );
    }


    @Test
    void canSeeAnyOrganizationPublicProfile() {
        when(organizationRepository.getPublicProfile(organizationB.getId())).thenReturn(organizationB);

        assertDoesNotThrow(()->service.getPublicProfile(
                        organizationA.getId(),
                        organizationB.getId()
                )
        );
    }




}
