package com.github.projetoifsc.estagios.core.implementation;
import com.github.projetoifsc.estagios.app.model.request.OrgEntryData;
import com.github.projetoifsc.estagios.core.models.IOrg;
import com.github.projetoifsc.estagios.core.IOrganizationDAO;
import com.github.projetoifsc.estagios.core.dto.OrgImpl;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import com.github.projetoifsc.estagios.core.models.projections.OrgPrivateProfileProjection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import static org.junit.jupiter.api.Assertions.*;

public class OrganizationWriteOperationsUnitTest {

    Mapper mapper = new Mapper();

    IOrganizationDAO organizationRepository = mock();

    OrganizationWriteOperations service = new OrganizationWriteOperations(organizationRepository);

    IOrg organizationA;
    IOrg organizationB;
    OrgEntryData newUser;

    @BeforeEach
    void setUp() {
        organizationA = new OrgImpl("1", false);
        organizationB = new OrgImpl("2", true);
        newUser = mapper.map(organizationA, OrgEntryData.class);
    }


    @Test
    void updatedProfileHasSameIdAsOrganization() {
        newUser = mapper.map(organizationB, OrgEntryData.class);
        when(organizationRepository.save(newUser)).thenReturn((OrgPrivateProfileProjection) newUser);

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
