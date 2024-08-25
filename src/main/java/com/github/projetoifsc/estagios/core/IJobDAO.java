package com.github.projetoifsc.estagios.core;

import com.github.projetoifsc.estagios.core.models.IJobEntryData;
import com.github.projetoifsc.estagios.core.models.projections.JobPrivateDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.JobPublicDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.JobSummaryProjection;
import com.github.projetoifsc.estagios.core.models.projections.ModerationDetailsProjection;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IJobDAO {

    // Substituir por save
    String saveAndGetId(IJobEntryData newJob);
    
    void delete(String id);

    JobSummaryProjection getJobSummary(String id);
    List<JobSummaryProjection> getJobsSummary(List<String> traineeshipIds);

    JobPublicDetailsProjection getJobPublicDetails(String id);
    JobPrivateDetailsProjection getJobPrivateDetails(String id);

    Page<JobPrivateDetailsProjection> getAllCreatedBy(String orgId);
    Page<JobPrivateDetailsProjection> getAllCreatedByWithPagination(String orgId, String search, int page, int limit);

    Page<JobPublicDetailsProjection> getAllReceivedByOrgPaginated(String loggedId, String search, int page, int limit);

    Page<JobPublicDetailsProjection> getAllCreatedOrApprovedBy(String orgId, String search, int page, int limit);

    Page<JobPublicDetailsProjection> getAllToBeModeratedBy(String orgId);
    Page<JobPublicDetailsProjection> getAllRejectedBy(String orgId);

    boolean isJobOfferedToOrg(String jobId, String orgId);

    void setJobsApprovedByOrg(List<String> jobs, String organizationId);
    void setJobsRejectedByOrg(List<String> jobs, String organizationId);

    ModerationDetailsProjection getModerationInfo(String orgId, String jobId);


}
