package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.app.utils.JsonParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ModeratedJobRepositoryUnitTest {

    @Autowired
    ModeratedJobRepository repository;

    @Autowired
    JsonParser jsonParser;


    @Test
    void findByjobAndorg() {
        var response = repository.findByJobIdAndOrganizationId(8L, 427L).orElseThrow();
        System.out.println(response.getJobId());
        System.out.println(response.getStatusId());
        System.out.println(response.getOrgId());
        System.out.println(response.getModifiedAt());
    }

}
