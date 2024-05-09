package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
//@DataJpaTest
class JobEntityRepositoryUnitTest {

    JobRepository jobRepository;
    AreaRepository areaRepository;
    OrganizationRepository organizationRepository;

    OrganizationEntity organizationEntity;

    JobEntity jobEntity;

    Faker faker = new Faker();
    JobMocker jobMocker = new JobMocker(faker);


    @Autowired
    public JobEntityRepositoryUnitTest(JobRepository jobRepository, AreaRepository areaRepository, OrganizationRepository organizationRepository) {
        this.jobRepository = jobRepository;
        this.areaRepository = areaRepository;
        this.organizationRepository = organizationRepository;
    }


    @Test
   // @Transactional
    // TODO Sobre transactional context ver: https://stackoverflow.com/questions/53836776/lazyinitializationexception-spring-boot
    void createTest() {

        var organization = organizationRepository.findById(1L).get();
        var area = areaRepository.findAreaById(1L).get();

       // area = new Area();

        jobEntity = jobMocker.generate();
        jobEntity.owner = organization;
        jobEntity.areas = List.of(area);

        var savedJob = jobRepository.save(jobEntity);
        System.out.println(savedJob);


    }


    @Test
    void testandoGambiarraParaLidarComTabelasAuxiliares() {
        var organization = organizationRepository.findById(1L).get();
        var area = areaRepository.findAreaById(1L).get();

        jobEntity = jobMocker.generate();
        //job.owner = organization;
        jobEntity.areas = List.of(area);
        jobEntity.periodId = 10;

        var savedJob = jobRepository.save(jobEntity);
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
