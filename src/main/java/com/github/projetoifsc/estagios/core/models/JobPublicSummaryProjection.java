package com.github.projetoifsc.estagios.core.models;

import java.time.LocalDateTime;

public interface JobPublicSummaryProjection extends IJob {

    @Override
    String getId();

    @Override
    OrgBasicInfoProjection getOwner();

    String getTitulo();
    String getRequisitos();
    float getRemuneracao();
    int getCargaHorariaSemanal();
    LocalDateTime getUpdatedAt();

}
