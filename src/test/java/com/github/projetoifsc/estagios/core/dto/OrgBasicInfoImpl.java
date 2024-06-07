package com.github.projetoifsc.estagios.core.dto;

import com.github.projetoifsc.estagios.core.models.OrgBasicInfoProjection;

public class OrgBasicInfoImpl extends OrganizationImpl implements OrgBasicInfoProjection {

    private String nome;

    public OrgBasicInfoImpl(String id, boolean isSchool) {
        super(id, isSchool);
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
