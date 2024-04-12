package com.github.projetoifsc.estagios.core.domain;

public interface iContact {

    IOrganization getOwner();
    String getId();
    boolean isGeneral();
    boolean isDefault();

}
