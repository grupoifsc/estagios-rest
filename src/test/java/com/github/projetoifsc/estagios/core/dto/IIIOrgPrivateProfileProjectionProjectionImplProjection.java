package com.github.projetoifsc.estagios.core.dto;

import com.github.projetoifsc.estagios.core.models.projections.ContactDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.OrgPrivateProfileProjection;

import java.time.LocalDateTime;

public class IIIOrgPrivateProfileProjectionProjectionImplProjection extends IIOrgPublicProfileProjectionImplProjection implements OrgPrivateProfileProjection {

    public IIIOrgPrivateProfileProjectionProjectionImplProjection(String id, boolean isSchool) {
        super(id, isSchool);
    }

    @Override
    public UserCredentialsProjection getUserCredentials() {
        return null;
    }

    @Override
    public String getCnpj() {
        return "";
    }

    @Override
    public ContactDetailsProjection getApplianceContact() {
        return null;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return null;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return null;
    }
}
