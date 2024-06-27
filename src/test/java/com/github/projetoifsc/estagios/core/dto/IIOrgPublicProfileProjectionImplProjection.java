package com.github.projetoifsc.estagios.core.dto;

import com.github.projetoifsc.estagios.core.models.projections.AddressDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.ContactDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.OrgPublicProfileProjection;

public class IIOrgPublicProfileProjectionImplProjection extends OrgSummaryProjectionImpl implements OrgPublicProfileProjection {


    public IIOrgPublicProfileProjectionImplProjection(String id, boolean isSchool) {
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
    public ContactDetailsProjection getMainContact() {
        return null;
    }

    @Override
    public AddressDetailsProjection getMainAddress() {
        return null;
    }

    @Override
    public ContactDetailsProjection getApplianceContact() {
        return null;
    }

}
