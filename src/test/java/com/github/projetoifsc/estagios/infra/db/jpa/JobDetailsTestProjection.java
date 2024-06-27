package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.models.IArea;
import com.github.projetoifsc.estagios.core.models.IFormat;
import com.github.projetoifsc.estagios.core.models.ILevel;
import com.github.projetoifsc.estagios.core.models.IPeriod;
import com.github.projetoifsc.estagios.core.models.projections.AddressDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.ContactDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.OrgSummaryProjection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

interface JobDetailsTestProjection {

    String getId();
//    OrgSummaryProjection getOwner();
//
//    IPeriod getPeriod();
//    ILevel getLevel();
//    IFormat getFormat();
//
//    String getTitulo();
//    String getDescricao();
//    String getImagem();
//    String getRequisitos();
//    LocalDate getDataInicio();
//    LocalDate getDataFinal();
//    int getDuracaoMeses();
//    float getRemuneracao();
//    int getCargaHorariaSemanal();
//    LocalDateTime getCreatedAt();
//    LocalDateTime getUpdatedAt();
//
//    AddressDetailsProjection getAddress();
//    ContactDetailsProjection getContact();
//    List<OrgSummaryProjection> getExclusiveReceivers();
    List<IArea> getAreas();

}
