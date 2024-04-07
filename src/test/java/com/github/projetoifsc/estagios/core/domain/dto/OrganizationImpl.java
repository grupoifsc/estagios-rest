package com.github.projetoifsc.estagios.core.domain.dto;

import com.github.projetoifsc.estagios.core.domain.iOrganization;

public class OrganizationImpl implements iOrganization {

    private String id;
    boolean isSchool;

    public OrganizationImpl(String id, boolean isSchool) {
        this.id = id;
        this.isSchool = isSchool;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean isSchool() {
        return isSchool;
    }

}
