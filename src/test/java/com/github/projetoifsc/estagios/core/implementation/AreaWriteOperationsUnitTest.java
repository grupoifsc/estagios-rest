package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IAreaRepository;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.domain.dto.AreaImpl;
import com.github.projetoifsc.estagios.core.domain.dto.OrganizationImpl;
import com.github.projetoifsc.estagios.core.IArea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AreaWriteOperationsUnitTest {

    IAreaRepository areaRepository = mock();
    AreaWriteOperations service = new AreaWriteOperations(areaRepository);

    private IOrganization school = new OrganizationImpl("1", true);
    private IOrganization enterprise = new OrganizationImpl("2", false);

    private AreaImpl area;
    private AreaImpl created;

    @BeforeEach
    void setUp() {
        area = new AreaImpl("Educação");
    }

    @Test
    void onlySchoolsCanCreateArea() {
        when(areaRepository.create(area)).thenReturn(area);
        assertDoesNotThrow(() -> service.create(school, area));
        assertThrows(Exception.class, () -> service.create(enterprise, area));
    }

    @Test
    void createReturnsCreatedArea() {
        when(areaRepository.create(area)).thenReturn(area);
        assertInstanceOf(IArea.class, service.create(school, area));
    }


}
