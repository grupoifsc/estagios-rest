package com.github.projetoifsc.estagios.core.dto;

import com.github.projetoifsc.estagios.core.models.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class JobPrivateDetailsImpl implements JobPrivateDetailsProjection {
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
    public List<IArea> getAreas() {
        return List.of();
    }

    @Override
    public short getPeriodId() {
        return 0;
    }

    @Override
    public short getLevelId() {
        return 0;
    }

    @Override
    public short getFormatId() {
        return 0;
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
    public AddressProjection getAddress() {
        return null;
    }

    @Override
    public ContactProjection getContact() {
        return null;
    }

    @Override
    public List<OrgBasicInfoProjection> getExclusiveReceivers() {
        return List.of();
    }
}
