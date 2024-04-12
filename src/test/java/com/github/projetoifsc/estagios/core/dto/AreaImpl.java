package com.github.projetoifsc.estagios.core.dto;

import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IArea;

public class AreaImpl implements IArea {


    private String id;
    private String name;
    private IOrganization owner;

    public AreaImpl(String name) {
        this.name = name;
    }


    public AreaImpl(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setOwner(IOrganization owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

}
