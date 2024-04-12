package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

class FakerJavaAPITest {

    Faker faker = new Faker();

    @Test
    void testingServices() {
        for(int i = 0; i < 10; i++) {
            var company = faker.company().name();
            System.out.println(company);
            System.out.println(stripName(company) + "\n");
            System.out.println(faker.internet().emailAddress(stripName(company).toLowerCase()));
        }

    }

    @Test
    void relatedToEducation() {

        for(int i = 0; i < 20; i++) {
            String compost = faker.educator().course();
            System.out.println(getLast(compost));
        }
    }

    @Test
    void relatedToAddress() {

        var addr = faker.address();

        System.out.println(faker.address().streetAddress());
        System.out.println(faker.address().streetAddress(true));
        System.out.println(addr.cityName());
        System.out.println(addr.country());
        System.out.println(addr.countryCode());
        System.out.println(addr.city());
        System.out.println(addr.state());
        System.out.println(addr.stateAbbr());

    }


    @Test
    void relatedToContact() {
        System.out.println(faker.phoneNumber().phoneNumber());
        System.out.println(faker.internet().emailAddress("juliana"));
    }


    private String getLast(String compostName) {
        var splittedList = compostName.split(" ");
        var lastIndex = splittedList.length - 1;
        return splittedList[lastIndex];
    }

    private String stripName(String name) {
        if (name.contains("-")) {
            return name.split("-")[0];
        }
        if (name.contains(",")) {
            return name.split(",")[0];
        }
        if (name.contains(" ")) {
            return name.split(" ")[0];
        }
        return name;
    }


}
