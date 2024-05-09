package com.github.projetoifsc.estagios.app.utils.mock;

import com.github.projetoifsc.estagios.app.view.AreaSerializableView;

import java.util.ArrayList;
import java.util.List;

public class AreaMock {

    public static AreaSerializableView getOne() {
        return AreaMock.getList().get(0);
    }

    public static List<AreaSerializableView> getList() {
        List<AreaSerializableView> list = new ArrayList<>();
        list.add(new AreaSerializableView("1", OrgMock.getOne(),"Engenharia Elétrica"));
        list.add(new AreaSerializableView("1", OrgMock.getOne(),"Educação"));
        list.add(new AreaSerializableView("2", OrgMock.getOne(),"Letras"));
        list.add(new AreaSerializableView("3", OrgMock.getOne(),"Tecnologia da Informação"));
        list.add(new AreaSerializableView("5", OrgMock.getOne(),"Ciências Humanas"));
        return list;
    }

}
