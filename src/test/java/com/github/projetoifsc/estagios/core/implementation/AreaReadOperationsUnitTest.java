package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IAreaDAO;
import com.github.projetoifsc.estagios.core.dto.IAreaImpl;
import com.github.projetoifsc.estagios.core.models.IArea;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

public class AreaReadOperationsUnitTest {

    IAreaDAO areaRepository = mock();
    AreaReadOperations service = new AreaReadOperations(areaRepository);

    @Test
    void getAllAreasReturnList() {
        when(areaRepository.getAll()).thenReturn(List.of(new IAreaImpl("1", "Educação"), new IAreaImpl("2", "Engenharia")));
        assertInstanceOf(List.class, service.getAll());
    }

    @Test
    void getOneReturnArea() {
        when(areaRepository.getById("1")).thenReturn(new IAreaImpl("1", "Educação"));

        assertInstanceOf(IArea.class, service.getById("1"));
    }

}
