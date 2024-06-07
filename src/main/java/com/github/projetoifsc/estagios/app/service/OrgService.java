package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.model.request.NewOrgProfileRequest;
import com.github.projetoifsc.estagios.app.model.response.PrivateOrgProfileResponse;
import com.github.projetoifsc.estagios.app.model.shared.AddressModel;
import com.github.projetoifsc.estagios.app.model.shared.ContactModel;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.app.model.response.PublicOrgProfileResponse;
import com.github.projetoifsc.estagios.core.IOrganizationUseCases;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public PrivateOrgProfileResponse create(NewOrgProfileRequest newOrgProfileRequest) {
        newOrgProfileRequest.setPassword(passwordEncoder.encode(newOrgProfileRequest.getPassword()));
        var createdUser = organizationUseCases.createProfile(newOrgProfileRequest);
        return mapper.map(createdUser, PrivateOrgProfileResponse.class);
    }


    public PrivateOrgProfileResponse getAuthUserPerfil(UserPrincipal userPrincipal) {
        var org = organizationUseCases.getPrivateProfile(userPrincipal.getId(), userPrincipal.getId());
        return mapper.map(org, PrivateOrgProfileResponse.class);
    }


    public PublicOrgProfileResponse getUserPublicProfile(UserPrincipal userPrincipal, String targetId) {
        var org = organizationUseCases.getPublicProfile(userPrincipal.getId(), targetId);
        return mapper.map(org, PublicOrgProfileResponse.class);
    }


    public PrivateOrgProfileResponse updateAuthUserPerfil(UserPrincipal userPrincipal, NewOrgProfileRequest updatedUserData) {
        updatedUserData.setPassword(passwordEncoder.encode(updatedUserData.getPassword()));
        var org = organizationUseCases.updateProfile(userPrincipal.getId(), userPrincipal.getId(), updatedUserData);
        return mapper.map(org, PrivateOrgProfileResponse.class);
    }


    public void deleteAuthUserPerfil(UserPrincipal userPrincipal) {
        organizationUseCases.deleteProfile(userPrincipal.getId(), userPrincipal.getId());
    }


    public Page<PublicOrgProfileResponse> getAllSchools(UserPrincipal userPrincipal) {
        var orgs = organizationUseCases.getAllSchools();
        return orgs.map(
                org -> mapper.map(org, PublicOrgProfileResponse.class)
        );
    }


    public List<AddressModel> getAuthUserAddresses(UserPrincipal userPrincipal) {
        var addresses = organizationUseCases.getAddresses(userPrincipal.getId(), userPrincipal.getId());
        return addresses.stream().map(
                addr -> mapper.map(addr, AddressModel.class)
        ).toList();
    }


    public List<ContactModel> getAuthUserContacts(UserPrincipal userPrincipal) {
        var contacts = organizationUseCases.getContacts(userPrincipal.getId(), userPrincipal.getId());
        return contacts.stream().map(
                contact -> mapper.map(contact, ContactModel.class)
        ).toList();
    }


}
