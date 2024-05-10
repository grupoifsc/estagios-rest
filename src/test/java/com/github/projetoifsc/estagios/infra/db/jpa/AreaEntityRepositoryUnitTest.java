package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AreaEntityRepositoryUnitTest {

    AreaRepository areaRepository;
    OrganizationRepository organizationRepository;

    OrganizationEntity organizationEntity;
    AreaEntity areaEntity;

    Faker faker = new Faker();
    AreaMocker areaMocker = new AreaMocker(faker);

    @Autowired
    public AreaEntityRepositoryUnitTest(AreaRepository areaRepository, OrganizationRepository organizationRepository) {
        this.areaRepository = areaRepository;
        this.organizationRepository = organizationRepository;
    }


    // Repensar todos estes testes...
    // Repensar a questão da camada.. para não ter que fazer duas vezes a consulta ao banco de dados...
    @Test
    void createMockedArea() {
//        var orgDBResponse = organizationRepository.findById(1L);
//
//        orgDBResponse.ifPresent(value -> (OrganizationEntity) organizationEntity = value);
//        area = (Area) areaMocker.generate();
//        area.owner = organizationEntity;
//        var savedArea = areaRepository.save(area);
//        System.out.println(savedArea);
    }


}
