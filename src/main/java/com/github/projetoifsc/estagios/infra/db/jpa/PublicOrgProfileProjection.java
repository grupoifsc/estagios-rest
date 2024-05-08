package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.IOrganization;

public interface PublicOrgProfileProjection extends OrgBasicInfoProjection {
    String getId();
    String getNome();
    String getInfo();
    String getWebsite();
    String getRedesSociais();
    Boolean getIe();
}
