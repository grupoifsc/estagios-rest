package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.models.projections.OrgSummaryProjection;

import java.util.List;

interface JobWithReceiversTestProjection {
    String getId();
    OrgSummaryProjection getOwner();
    List<OrgSummaryProjection> getExclusiveReceivers();
}
