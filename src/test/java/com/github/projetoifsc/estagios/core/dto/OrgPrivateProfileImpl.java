package com.github.projetoifsc.estagios.core.dto;

import com.github.projetoifsc.estagios.core.models.ContactProjection;
import com.github.projetoifsc.estagios.core.models.OrgPrivateProfileProjection;

import java.time.LocalDateTime;

public class OrgPrivateProfileImpl extends OrgPublicProfileImpl implements OrgPrivateProfileProjection {

    public OrgPrivateProfileImpl(String id, boolean isSchool) {
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
    public ContactProjection getApplianceContact() {
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
