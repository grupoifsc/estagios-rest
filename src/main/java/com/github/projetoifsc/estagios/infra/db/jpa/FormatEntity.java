package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.models.IFormat;
import jakarta.persistence.*;

// Enums: https://dev.to/noelopez/spring-rest-working-with-enums-ma
// Isso é o que eu quero no controller
// Mas sincronização automática, pelo que eu entendi, não é possível =/
//https://www.baeldung.com/jpa-persisting-enums-in-jpa

@Entity
@Table(name = "formats")
class FormatEntity implements IFormat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    private String name;

    @Override
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
