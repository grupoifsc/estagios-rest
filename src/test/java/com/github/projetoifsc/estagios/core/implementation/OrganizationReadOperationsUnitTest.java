package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.dto.IIOrgPublicProfileProjectionImplProjection;
import com.github.projetoifsc.estagios.core.models.IOrg;
import com.github.projetoifsc.estagios.core.IOrganizationDAO;
import com.github.projetoifsc.estagios.core.dto.OrgImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class OrganizationReadOperationsUnitTest {

    IOrganizationDAO organizationRepository = mock();
    OrganizationReadOperations service = new OrganizationReadOperations(organizationRepository);

    IOrg organizationA;
    IOrg organizationB;

    @BeforeEach
    void setUp() {
        organizationA = new OrgImpl("1", false);
        organizationB = new OrgImpl("2", true);
    }


    @Test
    void getSchoolsCallsSchoolsPublicProfileAndReturnsList() {
        when(organizationRepository.getAllSchoolsPublicProfile())
                .thenReturn(new PageImpl<>(List.of(new IIOrgPublicProfileProjectionImplProjection("1", true), new IIOrgPublicProfileProjectionImplProjection("2", true))));
        assertInstanceOf(PageImpl.class, service.getAllSchools());
    }


    @Test
    void givenNotSelf_whenSeePrivateDetails_thenThrowException() {
        assertThrows(UnauthorizedAccessException.class, ()->
                service.getPrivateProfile(
                        organizationA.getId(),
                        organizationB.getId()
                )
        );
    }


    @Test
    void givenNotSelf_whenSeeAddressesOrContacts_thenThrowException() {
        assertThrows(UnauthorizedAccessException.class, ()->
                service.getAddresses(
                        organizationA.getId(),
                        organizationB.getId()
                )
        );
        assertThrows(UnauthorizedAccessException.class, ()->
                service.getContacts(
                        organizationA.getId(),
                        organizationB.getId()
                )
        );
    }


}
