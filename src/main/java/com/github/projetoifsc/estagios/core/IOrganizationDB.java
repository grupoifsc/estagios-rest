package com.github.projetoifsc.estagios.core;


import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrganizationDB {

    IOrganization findById(String id);
    List<IOrganization> findAllById(List<String> receiversIds);
    IOrganization findByUsername(String username);
    IOrganization save(IOrganization organization);
    void delete(String organization);
    IOrganization getPublicProfile(String organization);
    IOrganization getPrivateProfile(String organization);
    Page<IJob> getCreatedJobs(String organization);
    List<IJob> getExclusiveReceivedJobs(String organization);
    Page<IOrganization> getAllPublicProfile();
    Page<IOrganization> getSchoolsPublicProfile();

}
