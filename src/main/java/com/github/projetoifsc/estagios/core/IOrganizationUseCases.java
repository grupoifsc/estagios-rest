package com.github.projetoifsc.estagios.core;

import com.github.projetoifsc.estagios.core.models.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrganizationUseCases {

    OrgPrivateProfileProjection createProfile(OrgPrivateProfileProjection organization);
    OrgPrivateProfileProjection updateProfile(String loggedId, String targetId, OrgPrivateProfileProjection organization);
    void deleteProfile(String loggedId, String targetId);

    OrgPublicProfileProjection getPublicProfile(String loggedId, String targetId);
    OrgPrivateProfileProjection getPrivateProfile(String loggedId, String targetId);

    Page<OrgPublicProfileProjection> getAllSchools();

    List<AddressProjection> getAddresses(String loggedId, String targetId);
    List<ContactProjection> getContacts(String loggedId, String targetId);

}
