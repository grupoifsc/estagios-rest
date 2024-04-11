package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@SpringBootTest
class JobRepositoryUnitTest {

    JobRepository jobRepository;
    AreaRepository areaRepository;
    OrganizationRepository organizationRepository;

    Organization organization;

    Job job;

    Faker faker = new Faker();
    JobMocker jobMocker = new JobMocker(faker);


    @Autowired
    public JobRepositoryUnitTest(JobRepository jobRepository, AreaRepository areaRepository, OrganizationRepository organizationRepository) {
        this.jobRepository = jobRepository;
        this.areaRepository = areaRepository;
        this.organizationRepository = organizationRepository;
    }


    @Test
    void createTest() {

        var organization = organizationRepository.findById (1L).get();
        var area = areaRepository.findAreaById(1L).get();

        job = jobMocker.generate();
        job.owner = organization;
        job.areas = List.of(area);

        var savedJob = jobRepository.save(job);
        System.out.println(savedJob);

    }

}
