package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.model.request.NewUserRequest;
import com.github.projetoifsc.estagios.app.model.response.OrgPrivateProfileResponse;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.app.model.response.OrgPublicProfileBasicInfoView;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IOrganizationUseCases;
import com.github.projetoifsc.estagios.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class OrgService {

    private final IOrganizationUseCases organizationUseCases;
    private final Mapper mapper;
    private final PasswordEncoder passwordEncoder;

    OrgService(IOrganizationUseCases organizationUseCases, Mapper mapper, PasswordEncoder passwordEncoder) {
        this.organizationUseCases = organizationUseCases;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }


    public IOrganization create(NewUserRequest newUserRequest) {
        newUserRequest.setPassword(passwordEncoder.encode(newUserRequest.getPassword()));
        var createdUser = organizationUseCases.createProfile(newUserRequest);
        return mapper.map(createdUser, OrgPrivateProfileResponse.class);
    }


    public IOrganization getAuthUserPerfil(UserPrincipal userPrincipal, String targetId) {
        var org = organizationUseCases.getPrivateProfile(userPrincipal.getId(), targetId);
        return mapper.map(org, OrgPrivateProfileResponse.class);
    }


    public IOrganization getUserPublicProfile(UserPrincipal userPrincipal, String targetId) {
        var org = organizationUseCases.getPublicProfile(userPrincipal.getId(), targetId);
        return mapper.map(org, OrgPublicProfileBasicInfoView.class);
    }


    public IOrganization updateAuthUserPerfil(UserPrincipal userPrincipal, String targetId, NewUserRequest updatedUserData) {
        updatedUserData.setPassword(passwordEncoder.encode(updatedUserData.getPassword()));
        var org = organizationUseCases.updateProfile(userPrincipal.getId(), targetId, updatedUserData);
        return mapper.map(org, OrgPrivateProfileResponse.class);
    }


    public void deleteAuthUserPerfil(UserPrincipal userPrincipal, String targetId) {
        organizationUseCases.deleteProfile(userPrincipal.getId(), targetId);
    }


    public Page<IOrganization> getAllSchools(UserPrincipal userPrincipal) {
        var orgs = organizationUseCases.getSchools();
        return orgs.map(
                org -> mapper.map(org, OrgPublicProfileBasicInfoView.class)
        );
    }


}
