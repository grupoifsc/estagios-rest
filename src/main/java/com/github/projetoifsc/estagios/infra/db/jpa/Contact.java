package com.github.projetoifsc.estagios.infra.db.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "contacts")
class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", nullable = false)
    Organization owner;

    boolean geral;

    @Column(name="main_candidatura")
    boolean mainCandidatura;

    String email;
    String telefone;

    @Override
    public String toString() {
        return "Contact{" +
                "email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }


}
