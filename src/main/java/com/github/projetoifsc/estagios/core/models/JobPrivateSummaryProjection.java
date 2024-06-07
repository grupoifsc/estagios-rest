package com.github.projetoifsc.estagios.core.models;

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
