package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.IJobDAO;
import com.github.projetoifsc.estagios.core.models.IJobEntryData;
import com.github.projetoifsc.estagios.core.models.projections.JobPrivateDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.JobPublicDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.JobSummaryProjection;
import com.github.projetoifsc.estagios.core.models.projections.ModerationDetailsProjection;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class JobDAOImpl implements IJobDAO {

    private final JobDAOWrite jobDAOWrite;
    private final JobDAORead jobDAORead;
    private final JobDAOMod jobDAOMod;

    JobDAOImpl(JobDAOWrite jobDAOWrite, JobDAORead jobDAORead, JobDAOMod jobDAOMod) {
        this.jobDAOWrite = jobDAOWrite;
        this.jobDAORead = jobDAORead;
        this.jobDAOMod = jobDAOMod;
    }


    @Override
    public String saveAndGetId(IJobEntryData newJob) {
        return jobDAOWrite.saveAndGetId(newJob);
    }

    @Override
    public void delete(String id) {
        jobDAOWrite.delete(id);
    }

    @Override
    public JobSummaryProjection getJobSummary(String id) {
        return jobDAORead.getJobSummary(id);
    }

    @Override
    public List<JobSummaryProjection> getJobsSummary(List<String> traineeshipIds) {
        return jobDAORead.getJobsSummary(traineeshipIds);
    }

    @Override
    public JobPublicDetailsProjection getJobPublicDetails(String id) {
        return jobDAORead.getJobPublicDetails(id);
    }

    @Override
    public JobPrivateDetailsProjection getJobPrivateDetails(String id) {
        return jobDAORead.getJobPrivateDetails(id);
    }

    @Override
    public Page<JobPublicDetailsProjection> getAllRejectedBy(String orgId) {
        return jobDAORead.getAllRejectedBy(orgId);
    }

    @Override
    public Page<JobPublicDetailsProjection> getAllToBeModeratedBy(String orgId) {
        return jobDAORead.getAllToBeModeratedBy(orgId);
    }

    @Override
    public Page<JobPrivateDetailsProjection> getAllCreatedBy(String orgId) {
        return jobDAORead.getAllCreatedBy(orgId);
    }

    @Override
    public Page<JobPrivateDetailsProjection> getAllCreatedByWithPagination(String orgId, int page, int limit) {
        return jobDAORead.getAllCreatedByWithPagination(orgId, page, limit);
    }

    @Override
    public Page<JobPublicDetailsProjection> getAllCreatedOrApprovedBy(String orgId) {
        return jobDAORead.getAllCreatedOrApprovedBy(orgId);
    }

    @Override
    public boolean isJobOfferedToOrg(String jobId, String orgId) {
        return jobDAORead.isJobOfferedToOrg(jobId, orgId);
    }

    @Override
    public ModerationDetailsProjection getModerationInfo(String orgId, String jobId) {
        return jobDAOMod.getModerationInfo(orgId, jobId);
    }

    @Override
    public void setJobsApprovedByOrg(List<String> jobsIds, String organizationId) {
        jobDAOMod.setJobsApprovedByOrg(jobsIds, organizationId);
    }

    @Override
    public void setJobsRejectedByOrg(List<String> jobsIds, String organizationId) {
        jobDAOMod.setJobsRejectedByOrg(jobsIds, organizationId);
    }

}
