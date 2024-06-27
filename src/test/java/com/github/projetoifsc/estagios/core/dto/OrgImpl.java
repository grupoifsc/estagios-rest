package com.github.projetoifsc.estagios.core.dto;

import com.github.projetoifsc.estagios.core.models.IOrg;

public class OrgImpl implements IOrg {

    private String id;
    boolean isSchool;

    public OrgImpl(String id, boolean isSchool) {
        this.id = id;
        this.isSchool = isSchool;
    }

    @Override
    public String getId() {
        return id;
    }

//    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Boolean getIe() {
        return isSchool;
    }

    public void setIe(boolean school) {
        isSchool = school;
    }

}
