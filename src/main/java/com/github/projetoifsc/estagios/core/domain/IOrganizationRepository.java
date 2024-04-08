package com.github.projetoifsc.estagios.core.domain;


import java.util.List;

public interface IOrganizationRepository {

    IOrganization findById(String id);
    List<IOrganization> findAllById(List<String> receiversIds);
    IOrganization findByUsername(String username);

    IOrganization save(IOrganization organization);
    void delete(String organization);

    IOrganization getPublicProfile(String organization);
    IOrganization getPrivateProfile(String organization);

    List<iJob> getCreatedJobs(String organization);
    List<iJob> getExclusiveReceivedJobs(String organization);

    List<IOrganization> getAllPublicProfile();
    List<IOrganization> getSchoolsPublicProfile();

}
