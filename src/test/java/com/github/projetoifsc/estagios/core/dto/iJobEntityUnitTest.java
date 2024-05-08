package com.github.projetoifsc.estagios.core.dto;

import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.core.IOrganization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class iJobEntityUnitTest {

    IJob traineeship;

    @BeforeEach
    void setUp() {
        traineeship = new JobImpl();
        traineeship.setId("1");
        traineeship.setOwner(new OrganizationImpl("1", false));
        traineeship.setReceiversIds(List.of("2", "3"));
    }

    @Test
    void idIsAString() {
        assertInstanceOf(String.class, traineeship.getId());
    }

    @Test
    void ownerIsOrganization() {
        assertInstanceOf(IOrganization.class, traineeship.getOwner());
    }

    @Test
    void receiversIsListOfOrganizationStringIds() {
        assertInstanceOf(List.class, traineeship.getReceiversIds());
        assertInstanceOf(String.class, traineeship.getReceiversIds().get(0));
    }

}
