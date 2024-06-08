package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import com.github.projetoifsc.estagios.core.models.IJob;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.core.models.JobPublicSummaryProjection;
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
        var owner = organizationRepository.findById(352L, OrganizationEntity.class);
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
    void findAllByOwnerId() {
        var jobs = repository.findAllByOwnerId(195L, PageRequest.of(0, 50), JobPublicSummaryProjection.class);
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
        var jobs = repository.findAllByExclusiveReceiversId(272L, IJob.class);
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
                jobId, orgId, JobPublicSummaryProjection.class
        );
        jsonParser.printValue(result.orElse(null));
    }

    @Test
    void getSummaryWithoutModInput() {
        var orgId = 195L;
        var jobId = 37;
        var result = repository
                .findById(jobId, JobPublicSummaryProjection.class);
        jsonParser.printValue(result.orElse(null));
    }
}
