package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;

class AddressMocker {

    Faker faker;

    public AddressMocker(Faker faker) {
        this.faker = faker;
    }

    public Address generate() {

        var addr = faker.address();
        Address address = new Address();

        address.main = faker.bool().bool();
        address.rua = addr.streetAddress(true);
        address.bairro = addr.cityPrefix();
        address.cidade = addr.cityName();
        address.estado = addr.stateAbbr();
        address.pais = addr.country();

        return address;
    }



}
