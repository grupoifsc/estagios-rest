package com.github.projetoifsc.estagios.core.domain;

import com.github.projetoifsc.estagios.core.domain.dto.TraineeshipImpl;
import com.github.projetoifsc.estagios.core.domain.dto.OrganizationImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ITraineeshipUnitTest {

    ITraineeship traineeship;

    @BeforeEach
    void setUp() {
        traineeship = new TraineeshipImpl();
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
