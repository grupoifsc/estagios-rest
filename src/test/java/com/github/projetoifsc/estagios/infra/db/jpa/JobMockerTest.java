package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Locale;

class JobMockerTest {

    Faker faker = new Faker(new Locale("pt-BR", "BR"));
    JobMocker jobMocker = new JobMocker(faker);

    @Test
    void generateRandom() {
        for (int i = 0; i < 10; i++) {
            Job job = jobMocker.generate();
            System.out.println(job);
        }
    }


}
