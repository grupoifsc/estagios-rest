package com.github.projetoifsc.estagios.app.interfaces;

import com.github.projetoifsc.estagios.core.IOrganization;

public interface OrgBasicInfoProjection extends IOrganization {
    String getId();
    String getNome();
    Boolean getIe();
}
