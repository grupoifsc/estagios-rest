package com.github.projetoifsc.estagios.core.dto;

import com.github.projetoifsc.estagios.core.models.IOrganization;
import com.github.projetoifsc.estagios.core.models.JobPublicSummaryProjection;
import com.github.projetoifsc.estagios.core.models.OrgBasicInfoProjection;

import java.time.LocalDateTime;

public class JobPublicSummaryImpl implements JobPublicSummaryProjection {
    @Override
    public String getId() {
        return "";
    }

    @Override
    public void setId(String id) {

    }

    @Override
    public OrgBasicInfoProjection getOwner() {
        return null;
    }

    @Override
    public void setOwner(IOrganization user) {

    }

    @Override
    public String getTitulo() {
        return "";
    }

    @Override
    public String getRequisitos() {
        return "";
    }

    @Override
    public float getRemuneracao() {
        return 0;
    }

    @Override
    public int getCargaHorariaSemanal() {
        return 0;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return null;
    }
}
