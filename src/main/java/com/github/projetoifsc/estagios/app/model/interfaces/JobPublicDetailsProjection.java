package com.github.projetoifsc.estagios.app.model.interfaces;

import com.github.projetoifsc.estagios.core.IArea;
import com.github.projetoifsc.estagios.core.IJob;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface JobPublicDetailsProjection extends IJob {

    @Override
    String getId();

    @Override
    OrgBasicInfoProjection getOwner();

    //@Override
    List<IArea> getAreas();

    short getPeriodId();
    short getLevelId();
    short getFormatId();
    String getTitulo();
    String getDescricao();
    String getImagem();
    String getRequisitos();
    LocalDate getDataInicio();
    LocalDate getDataFinal();
    int getDuracaoMeses();
    float getRemuneracao();
    int getCargaHorariaSemanal();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    AddressProjection getAddress();
    ContactProjection getContact();

}