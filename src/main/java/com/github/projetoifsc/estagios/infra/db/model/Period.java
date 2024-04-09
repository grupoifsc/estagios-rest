package com.github.projetoifsc.estagios.infra.db.model;

import jakarta.persistence.*;

@Entity
@Table(name = "periods")
public class Period{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

}
