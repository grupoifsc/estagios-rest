package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AreaRepositoryUnitTest {

    AreaRepository areaRepository;
    OrganizationRepository organizationRepository;

    Organization organization;
    Area area;

    Faker faker = new Faker();
    AreaMocker areaMocker = new AreaMocker(faker);

    @Autowired
    public AreaRepositoryUnitTest(AreaRepository areaRepository, OrganizationRepository organizationRepository) {
        this.areaRepository = areaRepository;
        this.organizationRepository = organizationRepository;
    }


    @Test
    void createMockedArea() {
        var orgDBResponse = organizationRepository.findById(1L);
        orgDBResponse.ifPresent(value -> organization = value);
        area = areaMocker.generate();
        area.owner = organization;
        var savedArea = areaRepository.save(area);
        System.out.println(savedArea);
    }


}
