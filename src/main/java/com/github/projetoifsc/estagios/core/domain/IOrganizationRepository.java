package com.github.projetoifsc.estagios.core.domain;


import java.util.List;

public interface IOrganizationRepository {

    iOrganization findById(String id);
    List<iOrganization> findAllById(List<String> receiversIds);
    iOrganization findByUsername(String username);

    iOrganization save(iOrganization organization);
    void delete(String organization);

    iOrganization getPublicProfile(String organization);
    iOrganization getPrivateProfile(String organization);

    List<iJob> getCreatedJobs(String organization);
    List<iJob> getExclusiveReceivedJobs(String organization);

}
