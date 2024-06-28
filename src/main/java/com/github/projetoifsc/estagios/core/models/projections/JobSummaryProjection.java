package com.github.projetoifsc.estagios.core.models.projections;

import com.github.projetoifsc.estagios.core.models.IJob;

import java.util.List;

public interface JobSummaryProjection extends IJob {
    String getId();
    OrgSummaryProjection getOwner();
    List<OrgSummaryProjection> getExclusiveReceivers();
}
