package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;

class ContactMocker {

    Faker faker;

    ContactMocker(Faker faker) {
        this.faker = faker;
    }

    public Contact random() {
        Contact contact = new ContactAppliance();
        contact.email = faker.internet().emailAddress();
        contact.telefone = faker.phoneNumber().phoneNumber();
        return contact;
    }

    public Contact fromName(String name) {
        Contact contact = new ContactAppliance();
        contact.email = faker.internet().emailAddress(name);
        contact.telefone = faker.phoneNumber().phoneNumber();
        return contact;
    }

}
