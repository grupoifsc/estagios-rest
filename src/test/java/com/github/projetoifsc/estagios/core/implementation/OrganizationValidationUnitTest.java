package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.app.model.request.JobEntryData;
import com.github.projetoifsc.estagios.core.models.IOrg;
import com.github.projetoifsc.estagios.core.dto.OrgImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrganizationValidationUnitTest {

    private OrgImpl enterprise;
    private OrgImpl school;
    private JobEntryData traineeship;

    @BeforeEach
    void setUp() {
        enterprise = new OrgImpl("1", false);
        school = new OrgImpl("2", true);
        traineeship = new JobEntryData();
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
        List<IOrg> receivers = new ArrayList<>();
        assertTrue(OrganizationValidation.isReceiver(enterprise, receivers));
        assertTrue(OrganizationValidation.isReceiver(school, receivers));

    }

    @Test
    void givenOrgAndReceiverList_ifOrgInList_thenOrgIsReceiver() {
        List<IOrg> receivers = new ArrayList<>(List.of(enterprise, school));
        assertTrue(OrganizationValidation.isReceiver(enterprise, receivers));
        assertTrue(OrganizationValidation.isReceiver(school, receivers));
    }

    @Test
    void givenOrgAndReceiverList_ifOrgNotInList_thenIsNotReceiver() {
        List<IOrg> receivers = new ArrayList<>(List.of(enterprise));
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
