package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;

class ContactMocker {

    Faker faker;

    ContactMocker(Faker faker) {
        this.faker = faker;
    }

    public ContactEntity random() {
        ContactEntity contactEntity = new ContactApplianceEntity();
        contactEntity.email = faker.internet().emailAddress();
        contactEntity.telefone = faker.phoneNumber().phoneNumber();
        return contactEntity;
    }

    public ContactEntity fromName(String name) {
        ContactEntity contactEntity = new ContactApplianceEntity();
        contactEntity.email = faker.internet().emailAddress(name);
        contactEntity.telefone = faker.phoneNumber().phoneNumber();
        return contactEntity;
    }

}
