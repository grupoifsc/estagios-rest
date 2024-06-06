package com.github.projetoifsc.estagios.infra.db.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "periods")
class PeriodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    private String name;

}
