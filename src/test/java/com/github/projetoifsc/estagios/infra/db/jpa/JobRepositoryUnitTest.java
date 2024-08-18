package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import com.github.projetoifsc.estagios.core.models.IJob;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.core.models.projections.JobSummaryProjection;
import com.github.projetoifsc.estagios.core.models.projections.JobPublicDetailsProjection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import java.util.Locale;

@SpringBootTest
class JobRepositoryUnitTest {

    @Autowired
    Mapper mapper;

    @Autowired
    JobRepository repository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    JsonParser jsonParser;

    @Autowired
    ModeratedJobRepository moderatedJobRepository;

//    @Autowired
//    ApprovedJobRepository approvedJobRepository;

    JobMocker jobMocker = new JobMocker(new Faker(new Locale("pt-BR")));

    IJob job;


    @Test
    @Transactional
    @Rollback(false)
    void save() {
        var owner = organizationRepository.findById(352L, OrgEntity.class);
        var job = jobMocker.generate();
        job.setOwner(owner.get());
        var saved = repository.save(job);
        jsonParser.printValue (saved);
    }

    @Test
    void delete() {
        var opt = repository.findById(19L, JobEntity.class);
        var job = opt.orElse(null);
        jsonParser.printValue(job);
        repository.delete(job);
    }

    @Test
    void findById() {}

    @Test
    void findAllByOwnerIdTestingProjections () {
        var jobs = repository.findAllByOwnerId(195L, PageRequest.of(0, 50), JobPublicDetailsProjection.class);
        System.out.println(jobs.getNumberOfElements());
        jsonParser.printValue(jobs.getContent());
    }

    @Test
    void findAllByExclusiveReceiversEmpty() {
        var jobs = repository.findAllByExclusiveReceiversEmpty(IJob.class);
        System.out.println(jobs.size());
        jsonParser.printValue(jobs);
    }

    @Test
    void findAllByExclusiveReceiversId() {
        var jobs = repository.findAllByExclusiveReceiversId(272L, JobSummaryProjection.class);
        System.out.println(jobs.size());
        jsonParser.printValue(jobs);
    }

    @Test
    void saveApproved() {
//        var approved = new ApprovedJobEntity();
//        approved.setJobId(34);
//        approved.setOrgId(378);
//        var saved = approvedJobRepository.save(approved);
//        jsonParser.printValue(saved);
    }

    @Test
    void saveRejected() {
        var rejected = new ModeratedJobsEntity();
        rejected.setJobId(36);
        rejected.setOrgId(378);
        var saved = moderatedJobRepository.save(rejected);
        jsonParser.printValue(saved);
    }


    @Test
    void deleteApproved() {
        //approvedJobRepository.deleteById(1L);
    }

    @Test
    void deleteRejected() {
        moderatedJobRepository.deleteById(1L);
    }


    @Test
    void findByApprovals() {
        var orgId = 379;
//        var approved = repository.findAllByApprovalsOrganizationId(orgId, JobPublicSummaryProjection.class);
//        jsonParser.printValue(approved);
    }

    @Test
    void findByRejection() {
        var orgId = 378;
//        var rejected = repository.findAllByRejectionsOrganizationId(orgId, JobPublicSummaryProjection.class);
//        jsonParser.printValue(rejected);
    }

    @Test
    void getWithModerationInfo() {
        var orgId = 195L;
        var jobId = 37;
        var result = repository.findByIdAndModeratedJobsOrgId(
                jobId, orgId, JobPublicDetailsProjection.class
        );
        jsonParser.printValue(result.orElse(null));
    }

    @Test
    void getSummaryWithoutModInput() {
        var orgId = 195L;
        var jobId = 37;
        var result = repository
                .findById(jobId, JobPublicDetailsProjection.class);
        jsonParser.printValue(result.orElse(null));
    }

    @Test
    void getAvailableForOrg() {
        var orgId = 415L;
        var status = ModerationStatusEnum.APPROVED;
        var result = repository.findAllByOwnerIdOrModeratedJobsOrgIdAndModeratedJobsStatusId(
                orgId, orgId, status.getId(), JobPublicDetailsProjection.class
        );
        jsonParser.printValue(result);
    }

    @Test
    void findWithQueryAndFetch() {
        var value = repository.findByIdBasicInfo(15L).orElse(null);

        System.out.println(value.getOwner().getNome());
        System.out.println(value.getExclusiveReceivers());
        value.getExclusiveReceivers().forEach(r -> System.out.println(r.getId()));
        //System.out.println(value.getAddress());
        //jsonParser.printValue(value.orElse(null));
        var receivers = value.getExclusiveReceivers().stream()
                        .map(r -> mapper.map(r, OrgSummaryProjectionDTO.class))
                                .toList();
        var owner = mapper.map(value.getOwner(), OrgSummaryProjectionDTO.class);
        //var job = mapper.map(value, JobBasicProjectionDTO.class);
        var job = new JobSummaryProjectionDTO();
        job.setId(value.getId());
        job.setOwner(owner);
        job.getExclusiveReceivers().addAll(receivers);
        jsonParser.printValue(job);

    }


    @Test
    void findBasicProjectionById() {
        var entity = repository.findByIdBasicInfo(67L).orElseThrow();
        System.out.println(entity.getId());
    }


    @Test
    void findPublicProjectionById() {
        var entity = repository.findByIdPublicDetails(15L).orElseThrow();
        var job = mapper.map(entity, JobPublicDetailsDTO.class);
        job.setOwner(mapper.map(entity.getOwner(), OrgSummaryProjectionDTO.class));
        job.setAddress(entity.getAddress() != null ? mapper.map(entity.getAddress(), AddressDetailsDTO.class) : null);
        job.setContact(mapper.map(entity.getContact(), ContactDetailsDTO.class));
        jsonParser.printValue(job);
    }


    @Test
    @Transactional
    void findPrivateProjectionById() {
        var entity = repository.findByIdPublicDetails(15L).orElseThrow();
        var job = mapper.map(entity, JobPrivateDetailsDTO.class);
        jsonParser.printValue(job);
    }


    @Test
    void findAllCreatedByWithPublicProjectionQuery() {
        var entity = repository.findAllByOwnerId(195L);
        var jobs = entity.stream()
                .map(this::mapToPublicDetailsDTO)
                .toList();
        jsonParser.printValue(jobs);
    }

    @Test
    void findAllApprovedOrRejectedBy_UsingQueryMethod() {
        var entities = repository.findAllModeratedByOrgAndStatus(397L, (short) 1);
        var jobs = entities.stream()
                .map(this::mapToPublicDetailsDTO)
                .toList();
        jsonParser.printValue(jobs);
    }

    @Test
    void findAllAvailable_UsingQueryMethod() {
        var entities = repository.findAllCreatedOrModeratedByOrg(378L, ModerationStatusEnum.APPROVED.getId());
        var jobs = entities.stream()
                .map(this::mapToPublicDetailsDTO)
                .toList();
        System.out.println(jobs.size());
        jsonParser.printValue(jobs);
    }


    @Test
    void findAllPending_UsingQueryMethod() {
        var entities = repository.findAllPending(427L);
        var jobs = entities.stream()
                .map(this::mapToPublicDetailsDTO)
                .toList();
        System.out.println(jobs.size());
        jsonParser.printValue(jobs);
    }

    @Test
    void findOnePrivateDetailsFetchAll() {
        var job = repository.findByIdPrivateDetails(90L).orElse(null);
        var mapped = mapper.map(job, JobPrivateDetailsDTO.class);
        jsonParser.printValue(mapped);
    }

    private JobPublicDetailsDTO mapToPublicDetailsDTO(JobEntity entity) {
        var job = mapper.map(entity, JobPublicDetailsDTO.class);
        job.setOwner(entity.getOwner() != null ? mapper.map(entity.getOwner(), OrgSummaryProjectionDTO.class) : null);
        job.setAddress(entity.getAddress() != null ? mapper.map(entity.getAddress(), AddressDetailsDTO.class) : null);
        job.setContact(entity.getContact() != null ? mapper.map(entity.getContact(), ContactDetailsDTO.class) : null);
        return job;
    }

    @Test
    void findAllReceived() {
        var entities = this.repository.findAllReceived(397L);
        var jobs = entities.stream()
            .map(this::mapToPublicDetailsDTO)
            .toList();
        jsonParser.printValue(jobs);
        System.out.println(jobs.size());
    }


}
