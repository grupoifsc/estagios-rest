package com.github.projetoifsc.estagios.core;

import com.github.projetoifsc.estagios.core.models.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IJobDAO {

    String saveAndGetId(IJobEntryData newJob);
    void delete(String id);

    IJob getBasicInfo(String id);
    List<IJob> getBasicInfo(List<String> traineeshipIds);

    JobPublicDetailsProjection getPublicDetails(String id);
    JobPrivateDetailsProjection getPrivateDetails(String id);

    List<JobPublicSummaryProjection> findAllPublicJobsSummary();

    JobPublicSummaryProjection setJobApprovedByOrg(String jobId, String orgId);
    JobPublicSummaryProjection setJobRejectedByOrg(String jobId, String orgId);
    void setJobApprovedByOrg(List<IJob> jobs, String organizationId);
    void setJobRejectedByOrg(List<IJob> jobs, String organizationId);

    Page<JobPrivateSummaryProjection> getAllCreatedJobsSummaryFromOrg(String orgId);
    List<JobPublicSummaryProjection> getAllAvailableSummaryFromOrg(String orgId);
    List<JobPublicSummaryProjection> getAllPendingSummaryFromOrg(String orgId);
    List<JobPublicSummaryProjection> getAllApprovedSummaryFromOrg(String orgId);
    List<JobPublicSummaryProjection> getAllRejectedSummaryFromOrg(String orgId);
    List<JobPublicSummaryProjection> getExclusiveReceivedJobsSummaryForOrg(String orgId);

    boolean isJobOfferedToOrg(String jobId, String orgId);

    ModerationProjection getModerationInfo(String orgId, String jobId);

}
