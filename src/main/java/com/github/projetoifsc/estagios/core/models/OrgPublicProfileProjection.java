package com.github.projetoifsc.estagios.core.models;


public interface OrgPublicProfileProjection extends IOrganization {

    String getId();
    String getNome();
    String getInfo();
    Boolean getIe();
    String getWebsite();
    String getRedesSociais();
    ContactProjection getMainContact();
    AddressProjection getMainAddress();

}
