package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IOrganization;
import com.github.projetoifsc.estagios.core.domain.dto.OrganizationImpl;
import com.github.projetoifsc.estagios.core.domain.dto.JobImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrganizationValidationUnitTest {

    private OrganizationImpl enterprise;
    private OrganizationImpl school;
    private JobImpl traineeship;

    @BeforeEach
    void setUp() {
        enterprise = new OrganizationImpl("1", false);
        school = new OrganizationImpl("2", true);
        traineeship = new JobImpl();
    }

    @Test
    void sameIdMeansSameEntity() {
        assertTrue(OrganizationValidation.isSelf(enterprise.getId(), enterprise.getId()));
        assertTrue(OrganizationValidation.isSelf(school.getId(), school.getId()));
        assertFalse(OrganizationValidation.isSelf(enterprise.getId(), school.getId()));
    }

    @Test
    void nullIdThrowsException() {

        enterprise.setId(null);
        assertThrows(RuntimeException.class, () -> OrganizationValidation.isSelf(enterprise.getId(), enterprise.getId()));
        assertThrows(RuntimeException.class,() -> OrganizationValidation.isSelf(enterprise.getId(), school.getId()));
        school.setId(null);
        assertThrows(RuntimeException.class, () -> OrganizationValidation.isSelf(enterprise.getId(), school.getId()));

    }

    @Test
    void givenOrgAndReceiverList_ifReceiverListIsEmpty_thenOrgIsReceiver() {
        List<IOrganization> receivers = new ArrayList<>();
        assertTrue(OrganizationValidation.isReceiver(enterprise, receivers));
        assertTrue(OrganizationValidation.isReceiver(school, receivers));

    }

    @Test
    void givenOrgAndReceiverList_ifOrgInList_thenOrgIsReceiver() {
        List<IOrganization> receivers = new ArrayList<>(List.of(enterprise, school));
        assertTrue(OrganizationValidation.isReceiver(enterprise, receivers));
        assertTrue(OrganizationValidation.isReceiver(school, receivers));
    }

    @Test
    void givenOrgAndReceiverList_ifOrgNotInList_thenIsNotReceiver() {
        List<IOrganization> receivers = new ArrayList<>(List.of(enterprise));
        assertTrue(OrganizationValidation.isReceiver(enterprise, receivers));
        assertFalse(OrganizationValidation.isReceiver(school, receivers));
    }

    @Test
    void givenOrgAndTraineeship_ifOrgIdIsSameAsOwnerId_thenIsOrgIsOwner() {
        traineeship.setOwner(enterprise);
        assertTrue(OrganizationValidation.isOwner(enterprise, traineeship));
        assertFalse(OrganizationValidation.isOwner(school, traineeship));
    }

    @Test
    void onlySchoolsAreValidReceivers() {
        assertTrue(OrganizationValidation.isValidReceiver(school));
        assertFalse(OrganizationValidation.isValidReceiver(enterprise));
    }


}
