package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Locale;

class ContactEntityMockerTest {

    Faker faker = new Faker(new Locale("pt-BR", "BR"));
    ContactMocker contactMocker = new ContactMocker(faker);

    @Test
    void generateRandom() {
        for(int i = 0; i < 10; i++) {
            ContactEntity contactEntity = contactMocker.random();
            System.out.println(contactEntity);
        }
    }

    @Test
    void generateWithName() {
        for(int i = 0; i < 10; i++) {
            ContactEntity contactEntity = contactMocker.fromName("juliana");
            System.out.println(contactEntity);
        }
    }


}
