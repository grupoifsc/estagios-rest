package com.github.projetoifsc.estagios.core.dto;

import com.github.projetoifsc.estagios.core.models.IOrg;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class IOrgEntityUnitTest {

    IOrg organization;

    @BeforeEach
    void setUp() {
        organization = new OrgImpl("1", false);
    }

    @Test
    void idIsAStringField() {
        assertInstanceOf(String.class, organization.getId());
    }

    @Test
    void isSchoolIsABooleanField() {
        assertInstanceOf(Boolean.class, organization.getIe());
    }

}
