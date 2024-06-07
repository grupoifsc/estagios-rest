package com.github.projetoifsc.estagios.core.dto;

import com.github.projetoifsc.estagios.core.models.AddressProjection;
import com.github.projetoifsc.estagios.core.models.ContactProjection;
import com.github.projetoifsc.estagios.core.models.OrgPublicProfileProjection;

public class OrgPublicProfileImpl extends OrgBasicInfoImpl implements OrgPublicProfileProjection {


    public OrgPublicProfileImpl(String id, boolean isSchool) {
        super(id, isSchool);
    }

    @Override
    public String getInfo() {
        return "";
    }

    @Override
    public String getWebsite() {
        return "";
    }

    @Override
    public String getRedesSociais() {
        return "";
    }

    @Override
    public ContactProjection getMainContact() {
        return null;
    }

    @Override
    public AddressProjection getMainAddress() {
        return null;
    }

}
