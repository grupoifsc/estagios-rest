package com.github.projetoifsc.estagios.infra.db.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "periods")
class Period{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    private String name;


    @Override
    public String toString() {
        return "Period{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
