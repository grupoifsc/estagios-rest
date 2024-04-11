package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Company;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class OrgMocker {

    Faker faker;
    GeradorCnpj geradorCnpj;

    String nome;
    String cnpj;
    boolean ie;
    String info;
    String mainEmail;
    String mainPhone;
    String applyEmail;
    String applyPhone;
    String website;
    String redes_sociais;
    String criado_em;
    String atualizado_em;


    public OrgMocker(Faker faker, GeradorCnpj geradorCnpj) {
        this.faker = faker;
        this.geradorCnpj = geradorCnpj;
    }

    public Organization generate() {
        return randomOrganization();
    }


    private Organization randomOrganization() {

        Company fakeCompany = faker.company();
        nome = fakeCompany.name();

        var strippedLowerCaseName = stripName(nome).toLowerCase();

        cnpj = geradorCnpj.cnpj(false);
        ie = faker.bool().bool();
        info = fakeCompany.catchPhrase();
//        mainEmail = faker.internet().emailAddress(strippedLowerCaseName);
//        mainPhone = faker.phoneNumber().phoneNumber();
//        applyEmail = faker.internet().emailAddress(strippedLowerCaseName + "-apply");
//        applyPhone = faker.phoneNumber().cellPhone();
        website = "www." + strippedLowerCaseName + ".org";
        redes_sociais = website;
        criado_em = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        atualizado_em = criado_em;

        return new Organization(nome,cnpj,ie,info,website,redes_sociais,criado_em,atualizado_em);



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
