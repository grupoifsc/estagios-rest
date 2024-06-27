package com.github.projetoifsc.estagios.core.models.projections;

import com.github.projetoifsc.estagios.core.models.IOrg;

public interface OrgSummaryProjection {
    String getId();
    String getNome();
    Boolean getIe();
}
