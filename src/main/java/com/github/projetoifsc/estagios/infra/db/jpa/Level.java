package com.github.projetoifsc.estagios.infra.db.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "levels")
class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    private String name;

    @Override
    public String toString() {
        return "Level{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
