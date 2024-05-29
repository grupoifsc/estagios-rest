package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IOrganizationDB;
import com.github.projetoifsc.estagios.core.dto.OrganizationImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class OrganizationEntityReadOperationsUnitTest {

    IOrganizationDB organizationRepository = mock();
    OrganizationReadOperations service = new OrganizationReadOperations(organizationRepository);

    IOrganization organizationA;
    IOrganization organizationB;

    @BeforeEach
    void setUp() {
        organizationA = new OrganizationImpl("1", false);
        organizationB = new OrganizationImpl("2", true);
    }


    @Test
    void getSchoolsCallsSchoolsPublicProfileAndReturnsList() {
        when(organizationRepository.getAllSchoolsPublicProfile()).thenReturn(new PageImpl<>(List.of(new OrganizationImpl("1", true), new OrganizationImpl("2", true))));
        assertInstanceOf(PageImpl.class, service.getSchools());
    }


    @Test
    void seePrivateProfileReturnsInterface() {
        when(organizationRepository.getOnePrivateProfile(organizationA.getId())).thenReturn(organizationA);

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
        when(organizationRepository.getOnePublicProfile(organizationA.getId())).thenReturn(organizationA);

        assertDoesNotThrow(()->service.getPublicProfile(
                        organizationA.getId(),
                        organizationA.getId()
                )
        );
    }


    @Test
    void canSeeAnyOrganizationPublicProfile() {
        when(organizationRepository.getOnePublicProfile(organizationB.getId())).thenReturn(organizationB);

        assertDoesNotThrow(()->service.getPublicProfile(
                        organizationA.getId(),
                        organizationB.getId()
                )
        );
    }




}
