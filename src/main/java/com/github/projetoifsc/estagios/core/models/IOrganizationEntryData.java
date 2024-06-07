package com.github.projetoifsc.estagios.core.models;

public interface IOrganizationEntryData extends IOrganization {
    ContactProjection getMainContact();
    ContactProjection getApplianceContact();
    AddressProjection getMainAddress();
}
