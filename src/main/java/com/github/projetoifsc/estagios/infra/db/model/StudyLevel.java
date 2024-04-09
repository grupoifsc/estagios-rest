package com.github.projetoifsc.estagios.infra.db.model;

import jakarta.persistence.*;

@Entity
@Table(name = "studylevels")
public class StudyLevel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

}
