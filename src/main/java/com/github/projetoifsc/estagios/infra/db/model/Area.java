package com.github.projetoifsc.estagios.infra.db.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="areas")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

//    @NotEmpty
//    @Column(nullable = false)
    String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    Organization owner;

    public Organization getOwner() {
        return owner;
    }

    public void setOwner(Organization owner) {
        this.owner = owner;
    }

    public Area() {
    }

    public Area(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", owner=" + owner +
                '}';
    }
}
