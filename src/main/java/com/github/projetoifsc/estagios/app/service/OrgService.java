package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.model.request.OrgEntryData;
import com.github.projetoifsc.estagios.app.model.response.Address;
import com.github.projetoifsc.estagios.app.model.response.OrgPrivateProfile;
import com.github.projetoifsc.estagios.app.model.response.Contact;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.app.model.response.OrgPublicProfile;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
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
    private final JsonParser jsonParser;

    OrgService(IOrganizationUseCases organizationUseCases, Mapper mapper, PasswordEncoder passwordEncoder, JsonParser jsonParser) {
        this.organizationUseCases = organizationUseCases;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.jsonParser = jsonParser;
    }


    public OrgPrivateProfile create(OrgEntryData newOrgProfileRequest) {
        newOrgProfileRequest.getUserCredentials().setPwd((passwordEncoder.encode(newOrgProfileRequest.getUserCredentials().getPwd())));
       // var createdUser = organizationUseCases.createProfile(newOrgProfileRequest);
       // return mapper.map(createdUser, OrgPrivateProfile.class);
        return null;
    }


    public OrgPrivateProfile getAuthUserPerfil(UserPrincipal userPrincipal) {
        var org = organizationUseCases.getPrivateProfile(userPrincipal.getId(), userPrincipal.getId());
        return mapper.map(org, OrgPrivateProfile.class);
    }


    public OrgPublicProfile getUserPublicProfile(UserPrincipal userPrincipal, String targetId) {
        var org = organizationUseCases.getPublicProfile(userPrincipal.getId(), targetId);
        return mapper.map(org, OrgPublicProfile.class);
    }


    public OrgPrivateProfile updateAuthUserPerfil(UserPrincipal userPrincipal, OrgEntryData updatedUserData) {
        updatedUserData.getUserCredentials().setPwd((passwordEncoder.encode(updatedUserData.getUserCredentials().getPwd())));
//        var org = organizationUseCases.updateProfile(userPrincipal.getId(), userPrincipal.getId(), updatedUserData);
//        return mapper.map(org, OrgPrivateProfile.class);
        return null;
    }


    public void deleteAuthUserPerfil(UserPrincipal userPrincipal) {
        organizationUseCases.deleteProfile(userPrincipal.getId(), userPrincipal.getId());
    }


    public Page<OrgPublicProfile> getAllSchools(UserPrincipal userPrincipal) {
        var orgs = organizationUseCases.getAllSchools();
        return orgs.map(
                org -> mapper.map(org, OrgPublicProfile.class)
        );
    }


    public List<Address> getAuthUserAddresses(UserPrincipal userPrincipal) {
        var addresses = organizationUseCases.getAddresses(userPrincipal.getId(), userPrincipal.getId());
        return addresses.stream().map(
                addr -> mapper.map(addr, Address.class)
        ).toList();
    }


    public List<Contact> getAuthUserContacts(UserPrincipal userPrincipal) {
        var contacts = organizationUseCases.getContacts(userPrincipal.getId(), userPrincipal.getId());
        return contacts.stream().map(
                contact -> mapper.map(contact, Contact.class)
        ).toList();
    }


}
