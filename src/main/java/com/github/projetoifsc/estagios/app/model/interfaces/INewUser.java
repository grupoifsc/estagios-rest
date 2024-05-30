package com.github.projetoifsc.estagios.app.model.interfaces;

import com.github.projetoifsc.estagios.core.IOrganization;

public interface INewUser extends IOrganization {
    ContactProjection getMainContact();
    ContactProjection getApplianceContact();
    AddressProjection getMainAddress();
}
