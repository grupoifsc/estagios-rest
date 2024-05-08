package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

class JobMocker {

    Faker faker;

    JobMocker(Faker faker) {
        this.faker = faker;
    }

    public JobEntity generate() {

        JobEntity jobEntity = new JobEntity();

        jobEntity.titulo = faker.job().title();
        jobEntity.carga_horaria_semanal = faker.number().numberBetween(0, 50);
       // jobEntity.data_final = faker.date().future(500, TimeUnit.DAYS).toInstant().toString();
       // jobEntity.data_inicio = LocalDateTime.parse(jobEntity.data_final).minusDays(50).toString();
        jobEntity.descricao = jobEntity.titulo + " performing " + faker.job().field() + " " + faker.job().position() + " activities with our clients at " + faker.lordOfTheRings().location() ;
        jobEntity.duracao_meses = faker.number().numberBetween(0, 24);
        jobEntity.id_externo_autor = faker.bothify("sdssfsdf");
        jobEntity.imagem = faker.company().logo();
        jobEntity.remuneracao = faker.number().numberBetween(0, 5000);
        jobEntity.requisitos = faker.job().keySkills() + ";" + faker.job().keySkills() + ";" + faker.job().keySkills();

        jobEntity.formatId = 1;
        jobEntity.levelId = 2;
        jobEntity.periodId = 1;

        return jobEntity;

    }


}
