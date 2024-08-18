package com.github.projetoifsc.estagios.core.models.projections;

import com.github.projetoifsc.estagios.core.models.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface JobPublicDetailsProjection extends IJob  {

    String getId();
    OrgSummaryProjection getOwner();

    List<IArea> getAreas();

    IPeriod getPeriod();
    ILevel getLevel();
    IFormat getFormat();
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
    AddressDetailsProjection getAddress();
    ContactDetailsProjection getContact();
    ModerationDetailsProjection getModerationDetail();
    void setModerationDetail(ModerationDetailsProjection moderationDetail);

}
