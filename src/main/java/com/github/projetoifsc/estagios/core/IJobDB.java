package com.github.projetoifsc.estagios.core;

import java.util.List;

public interface IJobDB {

    IJob getBasicInfoById(String id);
    String saveAndGetId(IJobEntryData newJob);
    void delete(String ITraineeship);
    List<IOrganization> getExclusiveReceiversForJob(String ITraineeship);
    IJob getPublicDetails(String ITraineeship);
    IJob getPrivateDetails(String ITraineeship);
    List<IJob> findAllPublicJobs();

    void setJobApprovedByOrg(String traineeshipId, String organizationId);
    void setJobReprovedByOrg(String traineeshipId, String organizationId);

    List<IJob> getAllApprovedSummaryByOrg(String loggedId);
    List<IJob> getAllReprovedSummaryByOrg(String orgId);

}
