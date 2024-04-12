package com.github.projetoifsc.estagios.core.domain;

import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.domain.dto.OrganizationImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class IOrganizationUnitTest {

    IOrganization organization;

    @BeforeEach
    void setUp() {
        organization = new OrganizationImpl("1", false);
    }

    @Test
    void idIsAStringField() {
        assertInstanceOf(String.class, organization.getId());
    }

    @Test
    void isSchoolIsABooleanField() {
        assertInstanceOf(Boolean.class, organization.isSchool());
    }

}
