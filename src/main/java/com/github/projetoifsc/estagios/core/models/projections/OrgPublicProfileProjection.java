package com.github.projetoifsc.estagios.core.models.projections;

public interface OrgPublicProfileProjection {

    String getId();
    String getNome();
    String getInfo();
    Boolean getIe();
    String getWebsite();
    String getRedesSociais();
    ContactDetailsProjection getMainContact();
    AddressDetailsProjection getMainAddress();
    ContactDetailsProjection getApplianceContact();

}
