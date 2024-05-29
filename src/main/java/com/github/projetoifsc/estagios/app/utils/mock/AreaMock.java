package com.github.projetoifsc.estagios.app.utils.mock;

import com.github.projetoifsc.estagios.app.model.response.AreaView;

import java.util.ArrayList;
import java.util.List;

public class AreaMock {

    public static AreaView getOne() {
        return AreaMock.getList().get(0);
    }

    public static List<AreaView> getList() {
        List<AreaView> list = new ArrayList<>();
        list.add(new AreaView("1", "Engenharia Elétrica"));
        list.add(new AreaView("1", "Educação"));
        list.add(new AreaView("2", "Letras"));
        list.add(new AreaView("3", "Tecnologia da Informação"));
        list.add(new AreaView("5", "Ciências Humanas"));
        return list;
    }

}
