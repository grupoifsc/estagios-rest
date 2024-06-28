package com.github.projetoifsc.estagios.core.dto;

import com.github.projetoifsc.estagios.core.models.IOrg;
import com.github.projetoifsc.estagios.core.models.projections.OrgSummaryProjection;

public class OrgImpl implements IOrg, OrgSummaryProjection {

    private String id;
    boolean isSchool;
    private String nome;

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

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
