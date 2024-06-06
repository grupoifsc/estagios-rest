package com.github.projetoifsc.estagios.core;

import com.github.projetoifsc.estagios.app.model.interfaces.INewUser;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrganizationUseCases {

    IOrganization createProfile(INewUser organization);
    IOrganization updateProfile(String loggedId, String targetId, INewUser organization);
    void deleteProfile(String loggedId, String targetId);

    IOrganization getPublicProfile(String loggedId, String targetId);
    IOrganization getPrivateProfile(String loggedId, String targetId);

    Page<IOrganization> getAllSchools();

    List<IAddress> getAddresses(String loggedId, String targetId);
    List<IContact> getContacts(String loggedId, String targetId);

}
