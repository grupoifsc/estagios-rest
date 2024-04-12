package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import com.github.projetoifsc.estagios.infra.db.jpa.OrgMocker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrganizationRepositoryUnitTest {

    OrganizationRepository organizationRepository;

    Faker faker = new Faker();
    GeradorCnpj geradorCnpj = new GeradorCnpj();
    OrgMocker orgMocker = new OrgMocker(faker, geradorCnpj);

    @Autowired
    public OrganizationRepositoryUnitTest(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    Organization organization;

    @BeforeEach
    void setUp() {
        organization = orgMocker.generate();
    }

    @Test
    void saveOrganization() {
        for (int i = 0; i < 3; i++) {
            organization = orgMocker.generate();
            var saved = organizationRepository.save(organization);
            System.out.println(saved);
        }
    }


    @Test
    void updateOrganization() {
        var org = organizationRepository.findById(1L).get();
        org.ie = false;
        var saved = organizationRepository.save(org);
        var retrieved = organizationRepository.findById(1L).get();
        System.out.println(retrieved);
    }

}
