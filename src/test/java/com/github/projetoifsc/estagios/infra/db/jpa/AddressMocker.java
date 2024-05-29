package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;

class AddressMocker {

    Faker faker;

    public AddressMocker(Faker faker) {
        this.faker = faker;
    }

    public AddressEntity generate() {

        var addr = faker.address();
        AddressEntity addressEntity = new AddressMainEntity();

        addressEntity.rua = addr.streetAddress(true);
        addressEntity.bairro = addr.cityPrefix();
        addressEntity.cidade = addr.cityName();
        addressEntity.estado = addr.stateAbbr();
        addressEntity.pais = addr.country();

        return addressEntity;
    }



}
