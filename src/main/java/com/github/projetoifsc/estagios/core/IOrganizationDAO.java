package com.github.projetoifsc.estagios.core;

import com.github.projetoifsc.estagios.app.model.interfaces.INewUser;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrganizationDAO {

    IOrganization save(INewUser organization);
    void delete(String organization);

    IOrganization findById(String id);
    IOrganization findByUsername(String username);

    List<IOrganization> findAllById(List<String> receiversIds);

    IOrganization getOnePublicProfile(String organization);
    IOrganization getOnePrivateProfile(String organization);

    Page<IOrganization> getAllSchoolsPublicProfile();

    IAddress getMainAddress(String orgId);
    IContact getMainContact(String orgId);

    List<IAddress> getAllAddresses(String orgId);
    List<IContact> getAllContacts(String orgId);

}
