package com.github.projetoifsc.estagios.app.utils.mock;

import com.github.projetoifsc.estagios.app.model.response.PublicAreaResponse;

import java.util.ArrayList;
import java.util.List;

public class AreaMock {

    public static PublicAreaResponse getOne() {
        return AreaMock.getList().get(0);
    }

    public static List<PublicAreaResponse> getList() {
        List<PublicAreaResponse> list = new ArrayList<>();
        list.add(new PublicAreaResponse("1", "Engenharia Elétrica"));
        list.add(new PublicAreaResponse("1", "Educação"));
        list.add(new PublicAreaResponse("2", "Letras"));
        list.add(new PublicAreaResponse("3", "Tecnologia da Informação"));
        list.add(new PublicAreaResponse("5", "Ciências Humanas"));
        return list;
    }

}
