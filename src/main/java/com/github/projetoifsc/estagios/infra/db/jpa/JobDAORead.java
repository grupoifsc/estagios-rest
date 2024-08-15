package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import com.github.projetoifsc.estagios.core.models.projections.JobPrivateDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.JobPublicDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.JobSummaryProjection;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
class JobDAORead {

    private final JobRepository jobRepository;
    private final Mapper mapper;
    private final JsonParser jsonParser;

    JobDAORead(JobRepository jobRepository, Mapper mapper, JsonParser jsonParser) {
        this.jobRepository = jobRepository;
        this.mapper = mapper;
        this.jsonParser = jsonParser;
    }


    private <T, R> R getOptionalOrThrow(T input, Function<T, Optional<R>> getOptional) {
        Function<T, R> pipeline = getOptional
                .andThen(opt -> opt.orElseThrow(EntityNotFoundException::new));
        return pipeline.apply(input);
    }


    public JobSummaryProjection getJobSummary(String id) {
        var job = getOptionalOrThrow(
                Long.parseLong(id),
                jobRepository::findByIdBasicInfo);

        return mapper.map(job, JobSummaryProjectionDTO.class);
    }


    public List<JobSummaryProjection> getJobsSummary(List<String> traineeshipIds) {
        var longIds = traineeshipIds.stream().map(Long::parseLong).toList();
        var entities = jobRepository.findByIdsBasicInfo(longIds);
        var jobs = new ArrayList<JobSummaryProjection>();
        entities.forEach(job ->
                jobs.add(mapper.map(job, JobSummaryProjectionDTO.class))
        );
        return jobs;
    }


    public JobPublicDetailsProjection getJobPublicDetails(String id) {
        var job = getOptionalOrThrow(
                Long.parseLong(id),
                jobRepository::findByIdPublicDetails
        );
        return mapper.map(job, JobPublicDetailsDTO.class);
    }


    @Transactional
    public JobPrivateDetailsProjection getJobPrivateDetails(String id) {
        var jobId = Long.parseLong(id);
        var job = getOptionalOrThrow(
                jobId,
                jobRepository::findByIdPublicDetails
        );
        job = jobRepository.findByIdWithReceivers(jobId).orElseThrow();
        return mapper.map(job, JobPrivateDetailsDTO.class);
    }

    @Transactional
    public JobEntity fetchPrivateDetails(long jobId) {
        var job = getOptionalOrThrow(
                jobId,
                jobRepository::findByIdPublicDetails
        );
        return jobRepository.findByIdWithReceivers(jobId).orElse(job);
    }

    @Transactional
    public Page<JobPrivateDetailsProjection> getAllCreatedBy(String orgId) {
        var parsedId = Long.parseLong(orgId);
        var jobs = new ArrayList<JobPrivateDetailsProjection>();
        var entities = this.fetchCreatedBy(parsedId);
        entities.forEach(job -> {
                jobs.add(mapper.map(job, JobPrivateDetailsDTO.class));
        });
        return new PageImpl<JobPrivateDetailsProjection>(jobs, Pageable.ofSize(10), 10);
    }

    @Transactional
    public List<JobEntity> fetchCreatedBy (long orgId) {
        var entities = jobRepository.findAllByOwnerId(orgId);
        return !entities.isEmpty() ?
                jobRepository.findAllByOwnerIdWithReceivers(orgId) :
                entities;
    }

    public Page<JobPublicDetailsProjection> getAllRejectedBy(String orgId) {
        var entities = jobRepository.findAllModeratedByOrgAndStatus(
                Long.parseLong(orgId),
                ModerationStatusEnum.REJECTED.getId());
        var publicDetailsList = this.mapToPublicDetailsList(entities);
        return new PageImpl<JobPublicDetailsProjection>(publicDetailsList, Pageable.ofSize(10), 10);
    }


    private List<JobPublicDetailsProjection> mapToPublicDetailsList(List<JobEntity> entities) {
        var jobs = new ArrayList<JobPublicDetailsProjection>();
        entities.forEach(job ->
                jobs.add(mapper.map(job, JobPublicDetailsDTO.class))
        );
        return jobs;
    }


    public Page<JobPublicDetailsProjection> getAllToBeModeratedBy(String orgId) {
        var parsedId = Long.parseLong(orgId);
        var entities = jobRepository.findAllPending(parsedId);
        var publicDetailsList = this.mapToPublicDetailsList(entities);
        return new PageImpl<JobPublicDetailsProjection>(publicDetailsList, Pageable.ofSize(10), 10);
    }


    public Page<JobPublicDetailsProjection> getAllCreatedOrApprovedBy(String orgId) {
        var organizationId = Long.parseLong(orgId);
        var entities = jobRepository.findAllCreatedOrModeratedByOrg(organizationId, ModerationStatusEnum.APPROVED.getId());
        var publicDetailsList = this.mapToPublicDetailsList(entities);
        return new PageImpl<JobPublicDetailsProjection>(publicDetailsList, Pageable.ofSize(10), 10);
    }


    public boolean isJobOfferedToOrg(String jobId, String orgId) {
        var jobLongId = Long.parseLong(jobId);
        var orgLongId = Long.parseLong(orgId);
        return jobRepository
                .existsByIdAndExclusiveReceiversEmptyOrExclusiveReceiversId(
                        jobLongId, orgLongId);
    }


}
