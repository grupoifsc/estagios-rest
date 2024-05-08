package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Locale;

class JobEntityMockerTest {

    Faker faker = new Faker(new Locale("pt-BR", "BR"));
    JobMocker jobMocker = new JobMocker(faker);

    @Test
    void generateRandom() {
        for (int i = 0; i < 10; i++) {
            JobEntity jobEntity = jobMocker.generate();
            System.out.println(jobEntity);
        }
    }


}
