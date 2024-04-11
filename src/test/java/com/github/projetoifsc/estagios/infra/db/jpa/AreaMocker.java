package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;

class AreaMocker {

    Faker faker;

    public AreaMocker(Faker faker) {
        this.faker = faker;
    }

    public Area generate() {
        var nomeCurso = faker.educator().course();
        var nomeArea = getLast(nomeCurso);
        return new Area(nomeArea);
    }

    private String getLast(String compostName) {
        var splittedList = compostName.split(" ");
        var lastIndex = splittedList.length - 1;
        return splittedList[lastIndex];
    }

}
