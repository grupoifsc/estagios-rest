package com.github.projetoifsc.estagios.core;

import com.github.projetoifsc.estagios.app.model.interfaces.INewUser;
import org.springframework.data.domain.Page;

public interface IOrganizationUseCases {

    IOrganization createProfile(INewUser organization);
    IOrganization updateProfile(String loggedId, String targetId, INewUser organization);
    void deleteProfile(String loggedId, String targetId);
    Page<IOrganization> getSchools();
    IOrganization getPrivateProfile(String loggedId, String targetId);
    IOrganization getPublicProfile(String loggedId, String targetId);

}
