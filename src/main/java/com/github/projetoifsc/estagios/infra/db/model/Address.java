package com.github.projetoifsc.estagios.infra.db.model;

import jakarta.persistence.*;

@Entity
@Table(name = "adresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", nullable = false)
    Organization owner;

//    @Column(nullable = false)
    boolean main;

//    @Column(nullable = false)
    String rua;

//    @Column(nullable = false)
    String bairro;

//    @Column(nullable = false)
    String cidade;

//    @Column(nullable = false)
    String estado;

//    @Column(nullable = false)
    String pais;

    public Organization getOwner() {
        return owner;
    }

    public void setOwner(Organization owner) {
        this.owner = owner;
    }

    public void setMain(boolean main) {
        this.main = main;
    }

}
