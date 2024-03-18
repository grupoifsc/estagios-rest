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

    List<ITraineeship> getCreatedJobs(String organization);
    List<ITraineeship> getExclusiveReceivedJobs(String organization);

}
