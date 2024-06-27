package com.github.projetoifsc.estagios.core.dto;

import com.github.projetoifsc.estagios.core.models.projections.OrgSummaryProjection;

public class OrgSummaryProjectionImpl extends OrgImpl implements OrgSummaryProjection {

    private String nome;

    public OrgSummaryProjectionImpl(String id, boolean isSchool) {
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
