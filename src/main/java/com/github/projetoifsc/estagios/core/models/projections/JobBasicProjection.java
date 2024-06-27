package com.github.projetoifsc.estagios.core.models.projections;

import java.util.List;

public interface JobBasicProjection {
    String getId();
    OrgSummaryProjection getOwner();
    List<OrgSummaryProjection> getExclusiveReceivers();
}
