package com.github.projetoifsc.estagios.app.interfaces;

import com.github.projetoifsc.estagios.core.IJob;

import java.time.LocalDateTime;

public interface JobPrivateSummaryProjection extends IJob {

    @Override
    String getId();

    @Override
    OrgBasicInfoProjection getOwner();

    short getPeriodId();
    short getLevelId();
    short getFormatId();

    String getTitulo();
    String getRequisitos();
    float getRemuneracao();
    int getCargaHorariaSemanal();
    LocalDateTime getUpdatedAt();

}
