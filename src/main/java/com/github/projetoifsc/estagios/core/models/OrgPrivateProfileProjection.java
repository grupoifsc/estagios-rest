package com.github.projetoifsc.estagios.core.models;

import java.time.LocalDateTime;

public interface OrgPrivateProfileProjection extends IOrganization {

    String getId();
    UserCredentialsProjection getUserCredentials();
    String getNome();
    String getCnpj();
    Boolean getIe();
    String getInfo();
    String getWebsite();
    String getRedesSociais();
    ContactProjection getMainContact();
    ContactProjection getApplianceContact();
    AddressProjection getMainAddress();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();

    public interface UserCredentialsProjection {
        String getEmail();
        String getPwd();
    }

}