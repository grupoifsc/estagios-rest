package com.github.projetoifsc.estagios.core.implementation;
import com.github.projetoifsc.estagios.app.model.request.NewUserRequest;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IOrganizationDB;
import com.github.projetoifsc.estagios.core.dto.OrganizationImpl;
import com.github.projetoifsc.estagios.utils.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import static org.junit.jupiter.api.Assertions.*;

public class OrganizationEntityWriteOperationsUnitTest {

    Mapper mapper = new Mapper();

    IOrganizationDB organizationRepository = mock();

    OrganizationWriteOperations service = new OrganizationWriteOperations(organizationRepository);

    IOrganization organizationA;
    IOrganization organizationB;
    NewUserRequest newUser;

    @BeforeEach
    void setUp() {
        organizationA = new OrganizationImpl("1", false);
        organizationB = new OrganizationImpl("2", true);
        newUser = mapper.map(organizationA, NewUserRequest.class);
    }


    @Test
    void createProfileReturnsInterface() {
        when(organizationRepository.save(newUser)).thenReturn(organizationA);

        assertInstanceOf(IOrganization.class, service.createProfile(newUser));
    }


    @Test
    void updateProfileReturnsInterface() {
        when(organizationRepository.save(newUser)).thenReturn(organizationA);

        assertInstanceOf(IOrganization.class, service.updateProfile(
                        organizationA.getId(),
                        organizationA.getId(),
                        newUser)
        );
    }


    @Test
    void updatedProfileHasSameIdAsOrganization() {
        newUser = mapper.map(organizationB, NewUserRequest.class);
        when(organizationRepository.save(newUser)).thenReturn(organizationB);

        assertEquals(
                organizationA.getId(),
                service.updateProfile(
                        organizationA.getId(),
                        organizationA.getId(),
                        newUser
                ).getId()
        );
    }


    @Test
    void tryToUpdateOtherOrganizationProfileThrowsUnauthorized() {
        assertThrows(UnauthorizedAccessException.class,
            ()->service.updateProfile(
                    organizationA.getId(),
                    organizationB.getId(),
                    newUser)
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
