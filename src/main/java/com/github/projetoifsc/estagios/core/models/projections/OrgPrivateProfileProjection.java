package com.github.projetoifsc.estagios.core.models.projections;

import java.time.LocalDateTime;

public interface OrgPrivateProfileProjection {

    String getId();
//    UserCredentialsProjection getUserCredentials();
    String getNome();
    String getCnpj();
    Boolean getIe();
    String getInfo();
    String getWebsite();
    String getRedesSociais();
    ContactDetailsProjection getMainContact();
    ContactDetailsProjection getApplianceContact();
    AddressDetailsProjection getMainAddress();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();

    interface UserCredentialsProjection {
        String getEmail();
        String getPwd();
    }

}