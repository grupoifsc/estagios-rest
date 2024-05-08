package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Locale;

class OrgMockerTest {

    @Test
    void testMocker() {
        Faker faker = new Faker(new Locale("pt-BR", "BR"));
        GeradorCnpj geradorCnpj = new GeradorCnpj();
        OrgMocker orgMocker = new OrgMocker(faker, geradorCnpj);

        OrganizationEntity mockedOrg = orgMocker.generate();
        System.out.println(mockedOrg);
    }

}
