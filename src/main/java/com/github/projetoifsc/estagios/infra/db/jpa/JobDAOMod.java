package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.app.utils.Mapper;
import com.github.projetoifsc.estagios.core.models.projections.ModerationDetailsProjection;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
class JobDAOMod {

    private final ModeratedJobRepository moderatedJobRepository;
    private final Mapper mapper;

    JobDAOMod(ModeratedJobRepository moderatedJobRepository, Mapper mapper) {
        this.moderatedJobRepository = moderatedJobRepository;
        this.mapper = mapper;
    }

    public ModerationDetailsProjection getModerationInfo(String orgId, String jobId) {
        return moderatedJobRepository
                .findByJobIdAndOrganizationId(
                        Long.parseLong(jobId),
                        Long.parseLong(orgId),
                        ModerationDetailsProjection.class )
                .orElseThrow(EntityNotFoundException::new);
    }


    private void approve(long orgId, long jobId) {
        var moderatedJobsEntity = moderatedJobRepository
                .findByJobIdAndOrganizationId(jobId, orgId)
                .orElse(new ModeratedJobsEntity());

        if(moderatedJobsEntity.getStatusId() == ModerationStatusEnum.APPROVED.getId()) {
            return;
        }

        if(moderatedJobsEntity.getId() == null) {
            moderatedJobsEntity.setOrgId(orgId);
            moderatedJobsEntity.setJobId(jobId);
        }
        moderatedJobsEntity.setStatusId(ModerationStatusEnum.APPROVED.getId());
        moderatedJobRepository.save(moderatedJobsEntity);
    }


    private void reject(long orgId, long jobId) {
        var moderatedJobsEntity = moderatedJobRepository
                .findByJobIdAndOrganizationId(jobId, orgId)
                .orElse(new ModeratedJobsEntity());

        if(moderatedJobsEntity.getStatusId() == ModerationStatusEnum.REJECTED.getId()) {
            return;
        }

        if(moderatedJobsEntity.getId() == null) {
            moderatedJobsEntity.setOrgId(orgId);
            moderatedJobsEntity.setJobId(jobId);
        }
        moderatedJobsEntity.setStatusId(ModerationStatusEnum.REJECTED.getId());
        moderatedJobRepository.save(moderatedJobsEntity);
    }


    @Transactional
    public void setJobsApprovedByOrg(List<String> jobsIds, String organizationId) {
        for(String jobId : jobsIds) {
            this.approve(Long.parseLong(organizationId), Long.parseLong(jobId));
        }
    }

    @Transactional
    public void setJobsRejectedByOrg(List<String> jobsIds, String organizationId) {
        for(String jobId : jobsIds) {
            this.reject(Long.parseLong(organizationId), Long.parseLong(jobId));
        }
    }


}
