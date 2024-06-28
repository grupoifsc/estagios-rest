package com.github.projetoifsc.estagios.core.models.projections;

import com.github.projetoifsc.estagios.core.models.IOrg;

// AGora esse ficou irrelevante!
public interface OrgSummaryProjection extends IOrg {
    String getId();
    String getNome();
    Boolean getIe();
}
