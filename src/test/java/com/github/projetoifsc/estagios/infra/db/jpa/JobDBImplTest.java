package com.github.projetoifsc.estagios.infra.db.jpa;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class JobDBImplTest {

    @Autowired
    JobDBImpl jobDB;

    JsonMapper mapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .build();

    private void print(Object o) {
        try {
            System.out.println(mapper.writeValueAsString(o));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    @Transactional
    void findById() {
        var dto = jobDB.findById("4");
        print(dto);
    }

    @Test
    void save() {

    }

    @Test
    void delete() {

    }

    @Test
    void getReceivers() {

    }

    @Test
    void getPublicDetails() {

    }

    @Test
    void getPrivateDetails() {

    }

    @Test
    void findAllWithoutReceivers() {

    }

}
