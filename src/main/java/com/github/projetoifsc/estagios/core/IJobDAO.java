package com.github.projetoifsc.estagios.core;

import org.springframework.data.domain.Page;

import java.util.List;

public interface IJobDAO {

    String saveAndGetId(IJobEntryData newJob);
    void delete(String id);

    IJob getBasicInfo(String id);
    IJob getPublicDetails(String id);
    IJob getPrivateDetails(String id);

    List<IOrganization> getExclusiveReceiversForJob(String id);

    List<IJob> findAllPublicJobsSummary();

    IJob setJobApprovedByOrg(String jobId, String orgId);
    IJob setJobRejectedByOrg(String jobId, String orgId);

    Page<IJob> getAllCreatedJobsSummaryFromOrg(String orgId);
    List<IJob> getAllPendingSummaryFromOrg(String orgId);
    List<IJob> getAllApprovedSummaryFromOrg(String orgId);
    List<IJob> getAllRejectedSummaryFromOrg(String orgId);
    List<IJob> getExclusiveReceivedJobsSummaryForOrg(String orgId);

}
