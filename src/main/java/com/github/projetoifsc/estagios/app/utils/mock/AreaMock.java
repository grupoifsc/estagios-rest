package com.github.projetoifsc.estagios.app.utils.mock;

import com.github.projetoifsc.estagios.app.dto.AreaDTO;

import java.util.ArrayList;
import java.util.List;

public class AreaMock {

    public static AreaDTO getOne() {
        return AreaMock.getList().get(0);
    }

    public static List<AreaDTO> getList() {
        List<AreaDTO> list = new ArrayList<>();
        list.add(new AreaDTO("1", UserMock.getOne(),"Engenharia Elétrica"));
        list.add(new AreaDTO("1", UserMock.getOne(),"Educação"));
        list.add(new AreaDTO("2", UserMock.getOne(),"Letras"));
        list.add(new AreaDTO("3", UserMock.getOne(),"Tecnologia da Informação"));
        list.add(new AreaDTO("5", UserMock.getOne(),"Ciências Humanas"));
        return list;
    }

}
