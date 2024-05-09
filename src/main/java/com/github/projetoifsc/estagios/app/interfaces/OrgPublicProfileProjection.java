package com.github.projetoifsc.estagios.app.interfaces;


import com.github.projetoifsc.estagios.core.IOrganization;

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
