package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.IOrganization;

interface OrgBasicInfoProjection extends IOrganization {
    String getId();
    Boolean getIe();
}
