package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Locale;

class AreaMockerTest {

    Faker faker = new Faker(new Locale("pt-BR"));
    AreaMocker areaMocker = new AreaMocker(faker);

    @Test
    void gerarArea() {
        for (int i = 0; i < 20; i++) {
            System.out.println(areaMocker.generate().nome);
        }
    }

}
