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
        list.add(new AreaSerializableView("1", "Engenharia Elétrica"));
        list.add(new AreaSerializableView("1", "Educação"));
        list.add(new AreaSerializableView("2", "Letras"));
        list.add(new AreaSerializableView("3", "Tecnologia da Informação"));
        list.add(new AreaSerializableView("5", "Ciências Humanas"));
        return list;
    }

}
