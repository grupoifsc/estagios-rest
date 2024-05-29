package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

public class JobMocker {

    Faker faker;

    public JobMocker(Faker faker) {
        this.faker = faker;
    }

    public JobEntity generate() {

        JobEntity jobEntity = new JobEntity();

        jobEntity.titulo = faker.job().title();
        jobEntity.cargaHorariaSemanal = faker.number().numberBetween(0, 50);
        jobEntity.descricao = jobEntity.titulo + " performing " + faker.job().field() + " " + faker.job().position() + " activities with our clients at " + faker.lordOfTheRings().location() ;
        jobEntity.duracaoMeses = faker.number().numberBetween(0, 24);
        jobEntity.imagem = faker.company().logo();
        jobEntity.remuneracao = faker.number().numberBetween(0, 5000);
        jobEntity.requisitos = faker.job().keySkills() + "; " + faker.job().keySkills() + "; " + faker.job().keySkills();

        jobEntity.formatId = (short) faker.number().numberBetween(1, 3);
        jobEntity.levelId = (short) faker.number().numberBetween(1, 5);
        jobEntity.periodId = (short) faker.number().numberBetween(1, 3);

        jobEntity.dataInicio = LocalDate.ofInstant(faker.date().future(5, TimeUnit.DAYS).toInstant(), ZoneId.systemDefault());

        return jobEntity;

    }


}
