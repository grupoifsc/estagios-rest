package com.github.projetoifsc.estagios.core.dto;

import com.github.projetoifsc.estagios.core.models.IOrg;
import com.github.projetoifsc.estagios.core.models.projections.OrgSummaryProjection;

import java.time.LocalDateTime;

public class IJobPrivateSummaryImpl {
    public String getId() {
        return "";
    }

    public void setId(String id) {

    }

    public OrgSummaryProjection getOwner() {
        return null;
    }

    public void setOwner(IOrg user) {

    }

    public LocalDateTime getCreatedAt() {
        return null;
    }

    public short getPeriodId() {
        return 0;
    }

    public short getLevelId() {
        return 0;
    }

    public short getFormatId() {
        return 0;
    }

    public String getTitulo() {
        return "";
    }

    public String getRequisitos() {
        return "";
    }

    public float getRemuneracao() {
        return 0;
    }

    public int getCargaHorariaSemanal() {
        return 0;
    }

    public LocalDateTime getUpdatedAt() {
        return null;
    }
}
