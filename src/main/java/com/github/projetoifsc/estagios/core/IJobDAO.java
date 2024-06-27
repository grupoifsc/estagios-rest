package com.github.projetoifsc.estagios.core;

import com.github.projetoifsc.estagios.core.models.IJob;
import com.github.projetoifsc.estagios.core.models.IJobEntryData;
import com.github.projetoifsc.estagios.core.models.projections.JobPrivateDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.JobPublicDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.ModerationDetailsProjection;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IJobDAO {

    // Substituir por save
    String saveAndGetId(IJobEntryData newJob);
    
    void delete(String id);

    IJob getJobBasicInfo(String id);
    List<IJob> getJobBasicInfo(List<String> traineeshipIds);

    JobPublicDetailsProjection getJobPublicDetails(String id);
    JobPrivateDetailsProjection getJobPrivateDetails(String id);

    // Find All Available (notCreatedAndApproved)
    // Find All Aproved
    // Find All Rejected
    // Find all created
    // Find all pending
    Page<JobPrivateDetailsProjection> getAllCreatedBy(String orgId);

    List<JobPublicDetailsProjection> getAllCreatedOrApprovedBy(String orgId);
    List<JobPublicDetailsProjection> getAllToBeModeratedBy(String orgId);
    List<JobPublicDetailsProjection> getAllRejectedBy(String orgId);

    List<JobPublicDetailsProjection> findAllPublicJobsSummary();

    JobPublicDetailsProjection setJobApprovedByOrg(String jobId, String orgId);
    JobPublicDetailsProjection setJobRejectedByOrg(String jobId, String orgId);
    void setJobApprovedByOrg(List<IJob> jobs, String organizationId);
    void setJobRejectedByOrg(List<IJob> jobs, String organizationId);

    List<JobPublicDetailsProjection> getExclusiveReceivedJobsSummaryForOrg(String orgId);
    List<JobPublicDetailsProjection> getAllApprovedSummaryFromOrg(String orgId);

    boolean isJobOfferedToOrg(String jobId, String orgId);

    ModerationDetailsProjection getModerationInfo(String orgId, String jobId);

}
