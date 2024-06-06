package com.github.projetoifsc.estagios.infra.db.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "levels")
class LevelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    private String name;

}
