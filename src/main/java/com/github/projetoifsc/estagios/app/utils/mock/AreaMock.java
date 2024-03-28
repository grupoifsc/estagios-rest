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
        list.add(new AreaDTO("1", OrgMock.getOne(),"Engenharia Elétrica"));
        list.add(new AreaDTO("1", OrgMock.getOne(),"Educação"));
        list.add(new AreaDTO("2", OrgMock.getOne(),"Letras"));
        list.add(new AreaDTO("3", OrgMock.getOne(),"Tecnologia da Informação"));
        list.add(new AreaDTO("5", OrgMock.getOne(),"Ciências Humanas"));
        return list;
    }

}
