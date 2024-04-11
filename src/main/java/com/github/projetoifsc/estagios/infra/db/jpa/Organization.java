package com.github.projetoifsc.estagios.infra.db.jpa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name="orgs")
class Organization{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nome;

    String cnpj;

    @Column(name = "instituicao_de_ensino")
    boolean ie;

    String info;

    @OneToMany(mappedBy = "owner")
    List<Contact> contatos;

    @OneToMany(mappedBy = "owner")
    List<Address> enderecos;

    String website;

    // TODO Fazer como em requisitos
    String redes_sociais;

    String criado_em;
    String atualizado_em;


    Organization(String nome) {
        this.nome = nome;
    }

    public Organization() {

    }

    Organization(String nome, String cnpj, boolean instituicao_de_ensino, String info, String website, String redes_sociais, String criado_em, String atualizado_em) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.ie = instituicao_de_ensino;
        this.info = info;
        this.website = website;
        this.redes_sociais = redes_sociais;
        this.criado_em = criado_em;
        this.atualizado_em = atualizado_em;
    }

    @Override
    public String toString() {
        return "Organization{" +
                //"id=" + id +
                ", nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", ie=" + ie +
                ", info='" + info + '\'' +
                ", website='" + website + '\'' +
                ", redes_sociais='" + redes_sociais + '\'' +
                ", criado_em='" + criado_em + '\'' +
                ", atualizado_em='" + atualizado_em + '\'' +
                '}';
    }

}
