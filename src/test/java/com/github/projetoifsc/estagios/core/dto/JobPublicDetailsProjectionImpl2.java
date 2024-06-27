package com.github.projetoifsc.estagios.core.dto;

import com.github.projetoifsc.estagios.core.models.*;
import com.github.projetoifsc.estagios.core.models.projections.AddressDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.ContactDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.JobPublicDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.OrgSummaryProjection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class JobPublicDetailsProjectionImpl2 implements JobPublicDetailsProjection {
    @Override
    public String getId() {
        return "";
    }

    //@Override
    public void setId(String id) {

    }

    @Override
    public OrgSummaryProjection getOwner() {
        return null;
    }

 //   @Override
    public void setOwner(IOrg user) {

    }

    @Override
    public List<IArea> getAreas() {
        return List.of();
    }

    @Override
    public IPeriod getPeriod() {
        return null;
    }

    @Override
    public ILevel getLevel() {
        return null;
    }

    @Override
    public IFormat getFormat() {
        return null;
    }

    @Override
    public String getTitulo() {
        return "";
    }

    @Override
    public String getDescricao() {
        return "";
    }

    @Override
    public String getImagem() {
        return "";
    }

    @Override
    public String getRequisitos() {
        return "";
    }

    @Override
    public LocalDate getDataInicio() {
        return null;
    }

    @Override
    public LocalDate getDataFinal() {
        return null;
    }

    @Override
    public int getDuracaoMeses() {
        return 0;
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
    public LocalDateTime getCreatedAt() {
        return null;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return null;
    }

    @Override
    public AddressDetailsProjection getAddress() {
        return null;
    }

    @Override
    public ContactDetailsProjection getContact() {
        return null;
    }
}
