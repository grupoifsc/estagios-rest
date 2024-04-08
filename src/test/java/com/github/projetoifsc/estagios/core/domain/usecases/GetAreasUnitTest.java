package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IAreaRepository;
import com.github.projetoifsc.estagios.core.domain.dto.AreaImpl;
import com.github.projetoifsc.estagios.core.domain.iArea;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

public class GetAreasUnitTest {

    IAreaRepository areaRepository = mock();
    GetAreas service = new GetAreas(areaRepository);

    @Test
    void getAllAreasReturnList() {
        when(areaRepository.getAll()).thenReturn(List.of(new AreaImpl("1", "Educação"), new AreaImpl("2", "Engenharia")));
        assertInstanceOf(List.class, service.getAll());
    }

    @Test
    void getOneReturnArea() {
        when(areaRepository.getById("1")).thenReturn(new AreaImpl("1", "Educação"));

        assertInstanceOf(iArea.class, service.getById("1"));
    }

}
