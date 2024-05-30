package com.github.projetoifsc.estagios.app.model.interfaces;

import com.github.projetoifsc.estagios.core.IOrganization;

public interface OrgBasicInfoProjection extends IOrganization {
    String getId();
    String getNome();
    Boolean getIe();
}
