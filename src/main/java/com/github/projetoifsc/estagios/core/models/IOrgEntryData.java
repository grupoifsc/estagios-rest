package com.github.projetoifsc.estagios.core.models;

import com.github.projetoifsc.estagios.core.models.projections.AddressDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.ContactDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.OrgPrivateProfileProjection;

public interface IOrgEntryData extends IOrg {

    ContactDetailsProjection getMainContact();
    ContactDetailsProjection getApplianceContact();
    AddressDetailsProjection getMainAddress();
    OrgPrivateProfileProjection.UserCredentialsProjection getUserCredentials();

}
