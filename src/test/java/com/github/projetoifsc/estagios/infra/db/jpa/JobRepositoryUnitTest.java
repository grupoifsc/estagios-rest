package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
//@DataJpaTest
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
    @Transactional
    // TODO Sobre transactional context ver: https://stackoverflow.com/questions/53836776/lazyinitializationexception-spring-boot
    void createTest() {

        var organization = organizationRepository.findById (1L).get();
        var area = areaRepository.findAreaById(1L).get();

        job = jobMocker.generate();
        job.owner = organization;
        job.areas = List.of(area);

        var savedJob = jobRepository.save(job);
        System.out.println(savedJob);
        System.out.println(savedJob.getOwner());
        System.out.println(savedJob.getAreas());

    }


    @Test
    void testandoGambiarraParaLidarComTabelasAuxiliares() {
        var organization = organizationRepository.findById (1L).get();
        var area = areaRepository.findAreaById(1L).get();

        job = jobMocker.generate();
        job.owner = organization;
        job.areas = List.of(area);
        job.periodId = 10;

        var savedJob = jobRepository.save(job);
        System.out.println(savedJob);

    }


    @Test
    @Transactional
    void retrievePeriodInGambiarraMode() {
        var job = jobRepository.findById(4L).get();
        System.out.println(job);
        System.out.println(job.getPeriodo());
    }

}
