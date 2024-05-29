package com.github.projetoifsc.estagios.app.interfaces;


import com.github.projetoifsc.estagios.core.IOrganization;

import java.time.LocalDateTime;


public interface OrgPrivateProfileProjection extends IOrganization {

    String getId();
    String getNome();
    String getCnpj();
    Boolean getIe();
    String getInfo();
    String getWebsite();
    String getRedesSociais();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    ContactProjection getMainContact();
    ContactProjection getApplianceContact();
    AddressProjection getMainAddress();

}