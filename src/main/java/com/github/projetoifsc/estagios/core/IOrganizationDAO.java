package com.github.projetoifsc.estagios.core;

import com.github.projetoifsc.estagios.core.models.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrganizationDAO {

    OrgPrivateProfileProjection save(OrgPrivateProfileProjection organization);
    void delete(String organization);

    IOrganization findById(String id);
    IOrganization findByUsername(String username);

    List<IOrganization> findAllById(List<String> receiversIds);

    OrgPublicProfileProjection getOrgPublicProfile(String organization);
    OrgPrivateProfileProjection getOrgPrivateProfile(String organization);

    Page<OrgPublicProfileProjection> getAllSchoolsPublicProfile();

    IAddress getOrgMainAddress(String orgId);
    IContact getOrgMainContact(String orgId);

    List<AddressProjection> getAllAddressesFromOrg(String orgId);
    List<ContactProjection> getAllContactsFromOrg(String orgId);

    List<IOrganization> getExclusiveReceiversForJob(String jobId);

}
