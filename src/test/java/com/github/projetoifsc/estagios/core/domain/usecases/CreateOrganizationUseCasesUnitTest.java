package com.github.projetoifsc.estagios.core.domain.usecases;
import com.github.projetoifsc.estagios.core.domain.IOrganization;
import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.domain.dto.OrganizationImpl;
import com.github.projetoifsc.estagios.core.exceptions.UnauthorizedAccessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import static org.junit.jupiter.api.Assertions.*;

public class CreateOrganizationUseCasesUnitTest {

    IOrganizationRepository organizationRepository = mock();

    CreateOrganizationUseCases service = new CreateOrganizationUseCases(organizationRepository);

    IOrganization organizationA;
    IOrganization organizationB;

    @BeforeEach
    void setUp() {
        organizationA = new OrganizationImpl("1", false);
        organizationB = new OrganizationImpl("2", true);
    }


    @Test
    void createProfileReturnsInterface() {
        when(organizationRepository.save(organizationA)).thenReturn(organizationA);

        assertInstanceOf(IOrganization.class, service.createProfile(organizationA));
    }


    @Test
    void updateProfileReturnsInterface() {
        when(organizationRepository.save(organizationA)).thenReturn(organizationA);

        assertInstanceOf(IOrganization.class, service.updateProfile(
                        organizationA.getId(),
                        organizationA.getId(),
                        organizationA)
        );
    }


    @Test
    void updatedProfileHasSameIdAsOrganization() {
        when(organizationRepository.save(organizationB)).thenReturn(organizationB);

        assertEquals(
                organizationA.getId(),
                service.updateProfile(
                        organizationA.getId(),
                        organizationA.getId(),
                        organizationB
                ).getId()
        );
    }


    @Test
    void tryToUpdateOtherOrganizationProfileThrowsUnauthorized() {
        assertThrows(UnauthorizedAccessException.class,
            ()->service.updateProfile(
                    organizationA.getId(),
                    organizationB.getId(),
                    organizationB)
        );
    }


    @Test
    void deleteProfileReturnsNothing() {
        assertDoesNotThrow(()->service.deleteProfile(
                organizationA.getId(),
                organizationA.getId()
        ));
    }


    @Test
    void tryToDeleteOtherOrganizationsProfileThrowsUnauthorized() {
        assertThrows(UnauthorizedAccessException.class,()->service.deleteProfile(
                organizationA.getId(),
                organizationB.getId()
        ));
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
