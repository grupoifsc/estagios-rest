package com.github.projetoifsc.estagios.infra.db.repository;

import com.github.projetoifsc.estagios.infra.db.model.Area;
import com.github.projetoifsc.estagios.infra.db.model.Job;
import com.github.projetoifsc.estagios.infra.db.model.Organization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@SpringBootTest
public class JobRepositoryUnitTest {

    JobRepository jobRepository;
    AreaRepository areaRepository;
    OrganizationRepository organizationRepository;

    @Autowired
    public JobRepositoryUnitTest(JobRepository jobRepository, AreaRepository areaRepository, OrganizationRepository organizationRepository) {
        this.jobRepository = jobRepository;
        this.areaRepository = areaRepository;
        this.organizationRepository = organizationRepository;
    }


    @Test
    void CRUDTest() {

        var org = new Organization("NoBanks");
        var savedOrg = organizationRepository.save(org);
        System.out.println(savedOrg);
        var allOrgs = organizationRepository.findAll(PageRequest.of(1, 20));
        allOrgs.forEach(System.out::println);
        var createdOrg = organizationRepository.findById (1L);
        System.out.println(createdOrg);

        var area = new Area("Educação");
        area.setOwner(createdOrg);
        var saved = areaRepository.save(area);
        System.out.println(saved);
        var allAreas = areaRepository.findAll(PageRequest.of(1, 20));
        allAreas.forEach(System.out::println);
        var createdArea = areaRepository.findAreaById(1L);
//        System.out.println(createdArea);
//        if(createdArea.isPresent()) {
//            System.out.println(createdArea.get().getNome());
//        } else {
//            System.out.println(createdArea);
//        }

        var job = new Job("Estágio para Desenvolvimento Java");
        job.setOwner(createdOrg);
        job.setAreas(List.of(createdArea.get()));
        var savedJob = jobRepository.save(job);
        System.out.println(savedJob);
        var allJobs = jobRepository.findAll(PageRequest.of(1, 20));
        allJobs.forEach(System.out::println);
        var createdJob = jobRepository.findById(1L);
        System.out.println(createdJob);
//
//
//        var org = new Organization("NoBanks");
//        var savedOrg = organizationRepository.save(org);
//        System.out.println(savedOrg);
//        var allOrgs = organizationRepository.findAll();
//        allOrgs.forEach(System.out::println);
//        var createdOrg = organizationRepository.findById(1L);
//        System.out.println(createdOrg);


    }

}
