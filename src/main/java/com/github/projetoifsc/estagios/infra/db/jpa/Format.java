package com.github.projetoifsc.estagios.infra.db.jpa;

import jakarta.persistence.*;

// TODO https://dev.to/noelopez/spring-rest-working-with-enums-ma
// Isso é o que eu quero no controller
// Mas sincronização automática, pelo que eu entendi, não é possível =/
//https://www.baeldung.com/jpa-persisting-enums-in-jpa


@Entity
@Table(name = "formats")
class Format{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    private String name;

    @Override
    public String toString() {
        return "Format{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
