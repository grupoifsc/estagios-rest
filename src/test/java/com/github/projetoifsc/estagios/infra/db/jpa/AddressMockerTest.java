package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Locale;

class AddressMockerTest {

    Faker faker = new Faker(new Locale("pt-BR", "BR"));
    AddressMocker addressMocker = new AddressMocker(faker);

    @Test
    void gerarEndereco() {
        for (int i = 0; i < 10; i++) {
            Address address = addressMocker.generate();
            System.out.println(address);
        }
    }

}
