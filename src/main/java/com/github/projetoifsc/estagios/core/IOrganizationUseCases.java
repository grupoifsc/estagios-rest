package com.github.projetoifsc.estagios.core;

import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrganizationUseCases {

    IOrganization createProfile(IOrganization organization);
    IOrganization updateProfile(String loggedId, String targetId, IOrganization organization);
    void deleteProfile(String loggedId, String targetId);
    Page<IOrganization> getAll();
    Page<IOrganization> getSchools();
    IOrganization getPrivateProfile(String loggedId, String targetId);
    IOrganization getPublicProfile(String loggedId, String targetId);

}
