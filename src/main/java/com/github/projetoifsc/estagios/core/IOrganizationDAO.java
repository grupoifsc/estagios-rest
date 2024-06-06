package com.github.projetoifsc.estagios.core;

import com.github.projetoifsc.estagios.app.model.interfaces.INewUser;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrganizationDAO {

    IOrganization findById(String id);

    List<IOrganization> findAllById(List<String> receiversIds);

    IOrganization findByUsername(String username);
    IOrganization save(INewUser organization);

    void delete(String organization);

    IOrganization getOnePublicProfile(String organization);
    IOrganization getOnePrivateProfile(String organization);

    Page<IOrganization> getAllSchoolsPublicProfile();

    IAddress getMainAddress(String orgId);
    IContact getMainContact(String orgId);

    
    //  TODO Refactor: Mover queries relacionadas com vagas para outro DB
    Page<IJob> getAllCreatedJobsSummaryFromOrg(String organization);
    List<IJob> getExclusiveReceivedJobsSummaryForOrg(String organization);


}
