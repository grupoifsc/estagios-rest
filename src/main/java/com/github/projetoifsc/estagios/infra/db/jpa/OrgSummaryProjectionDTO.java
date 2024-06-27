package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.models.projections.OrgSummaryProjection;

class OrgSummaryProjectionDTO implements OrgSummaryProjection {

    private String id;
    private String nome;
    private boolean ie;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public Boolean getIe() {
        return ie;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIe(boolean ie) {
        this.ie = ie;
    }

}
