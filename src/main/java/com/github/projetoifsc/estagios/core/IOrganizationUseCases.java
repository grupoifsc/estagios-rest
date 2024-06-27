package com.github.projetoifsc.estagios.core;

import com.github.projetoifsc.estagios.core.models.IOrgEntryData;
import com.github.projetoifsc.estagios.core.models.projections.AddressDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.ContactDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.OrgPrivateProfileProjection;
import com.github.projetoifsc.estagios.core.models.projections.OrgPublicProfileProjection;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrganizationUseCases {

    OrgPrivateProfileProjection createProfile(IOrgEntryData organization);
    OrgPrivateProfileProjection updateProfile(String loggedId, String targetId, IOrgEntryData organization);
    void deleteProfile(String loggedId, String targetId);

    OrgPublicProfileProjection getPublicProfile(String loggedId, String targetId);
    OrgPrivateProfileProjection getPrivateProfile(String loggedId, String targetId);

    Page<OrgPublicProfileProjection> getAllSchools();

    List<AddressDetailsProjection> getAddresses(String loggedId, String targetId);
    List<ContactDetailsProjection> getContacts(String loggedId, String targetId);

}
