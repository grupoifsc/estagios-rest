package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.Locale;

class JobEntityMockerTest {

    Faker faker = new Faker(new Locale("pt-BR", "BR"));
    JobMocker jobMocker = new JobMocker(faker);
    JsonParser jsonParser = new JsonParser();

    @Test
    void generateRandom() {
        for (int i = 0; i < 10; i++) {
            JobEntity jobEntity = jobMocker.generate();
            jsonParser.printValue(jobEntity);
        }
    }


}
