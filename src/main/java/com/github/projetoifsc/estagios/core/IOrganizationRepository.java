package com.github.projetoifsc.estagios.core;


import java.util.List;

public interface IOrganizationRepository {

    IOrganization findById(String id);
    List<IOrganization> findAllById(List<String> receiversIds);
    IOrganization findByUsername(String username);
    IOrganization save(IOrganization organization);
    void delete(String organization);
    IOrganization getPublicProfile(String organization);
    IOrganization getPrivateProfile(String organization);
    List<IJob> getCreatedJobs(String organization);
    List<IJob> getExclusiveReceivedJobs(String organization);
    List<IOrganization> getAllPublicProfile();
    List<IOrganization> getSchoolsPublicProfile();

}
