package com.github.projetoifsc.estagios.core;

import com.github.projetoifsc.estagios.core.models.IAddress;
import com.github.projetoifsc.estagios.core.models.IContact;
import com.github.projetoifsc.estagios.core.models.IOrg;
import com.github.projetoifsc.estagios.core.models.IOrgEntryData;
import com.github.projetoifsc.estagios.core.models.projections.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrganizationDAO {

    OrgPrivateProfileProjection save(IOrgEntryData organization);
    void delete(String organization);

    OrgSummaryProjection findByIdSummaryProjection(String id);

    List<OrgSummaryProjection> findAllByIdSummaryProjection(List<String> receiversIds);

    OrgPublicProfileProjection getOrgPublicProfile(String organization);
    OrgPrivateProfileProjection getOrgPrivateProfile(String organization);

    Page<OrgPublicProfileProjection> getAllSchoolsPublicProfile();

    IAddress getOrgMainAddress(String orgId);
    IContact getOrgMainContact(String orgId);

    List<AddressDetailsProjection> getAllAddressesFromOrg(String orgId);
    List<ContactDetailsProjection> getAllContactsFromOrg(String orgId);

    List<OrgSummaryProjection> getExclusiveReceiversForJob(String jobId);

}
