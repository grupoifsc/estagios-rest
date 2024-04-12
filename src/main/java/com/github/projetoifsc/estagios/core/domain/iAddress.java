package com.github.projetoifsc.estagios.core.domain;

public interface iAddress {

    boolean isMain();
    String getId();
    IOrganization getOwner();

}
