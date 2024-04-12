package com.github.projetoifsc.estagios.infra.db.jpa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

// TODO Aprender mais sobre Auditable
//  https://codersathi.com/auto-generate-created-and-modified-date-time-in-spring-boot/


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="orgs")
class Organization{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nome;

    String cnpj;

    @Column(name = "ie")
    boolean ie;

    String info;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    List<Contact> contatos;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    List<Address> enderecos;

    String website;

    // TODO Fazer como em requisitos, forçando uma má prática em
    @Column(name = "redes_sociais")
    String redesSociais;

    @CreatedDate
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    LocalDateTime updatedAt;


    Organization(String nome) {
        this.nome = nome;
    }

    public Organization() {

    }

    Organization(String nome, String cnpj, boolean instituicao_de_ensino, String info, String website, String redes_sociais) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.ie = instituicao_de_ensino;
        this.info = info;
        this.website = website;
        this.redesSociais = redes_sociais;
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
                ", redes_sociais='" + redesSociais + '\'' +
                ", criado_em='" + createdAt + '\'' +
                ", atualizado_em='" + updatedAt + '\'' +
                '}';
    }

}
