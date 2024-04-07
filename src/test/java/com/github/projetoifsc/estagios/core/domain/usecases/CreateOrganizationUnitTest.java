package com.github.projetoifsc.estagios.core.domain.usecases;
import com.github.projetoifsc.estagios.core.domain.iOrganization;
import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.domain.dto.OrganizationImpl;
import com.github.projetoifsc.estagios.core.exceptions.UnauthorizedAccessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import static org.junit.jupiter.api.Assertions.*;

public class CreateOrganizationUnitTest {

    IOrganizationRepository organizationRepository = mock();

    CreateOrganization service = new CreateOrganization(organizationRepository);

    iOrganization organizationA;
    iOrganization organizationB;

    @BeforeEach
    void setUp() {
        organizationA = new OrganizationImpl("1", false);
        organizationB = new OrganizationImpl("2", true);
    }


    @Test
    void createProfileReturnsInterface() {
        when(organizationRepository.save(organizationA)).thenReturn(organizationA);

        assertInstanceOf(iOrganization.class, service.createProfile(organizationA));
    }


    @Test
    void updateProfileReturnsInterface() {
        when(organizationRepository.save(organizationA)).thenReturn(organizationA);

        assertInstanceOf(iOrganization.class, service.updateProfile(
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


}
