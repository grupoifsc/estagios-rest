package com.github.projetoifsc.estagios.core;


public interface IAddress {

    boolean isMain();
    String getId();
    IOrganization getOwner();

}
