package com.github.projetoifsc.estagios.core.dto;

import com.github.projetoifsc.estagios.core.models.IArea;

public class IAreaImpl implements IArea {


    private String id;
    private String name;

    public IAreaImpl(String name) {
        this.name = name;
    }


    public IAreaImpl(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
