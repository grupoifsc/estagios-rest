package com.github.projetoifsc.estagios.infra.db.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name="orgs")
public class Organization{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty
    @Column(nullable = false, unique = true)
    String nome;

  //  @NotEmpty
  //  @CNPJ
 //   @Column(nullable = false, unique = true)
    String cnpj;

//    @Column(nullable = false)
    boolean instituicao_de_ensino;

    String info;

    // TODO Resolver estas relações, não são One To One
    @OneToOne
    @JoinColumn(name = "main_contact_id"
    //        , nullable = false
    )
    Contact main_contact;

    // TODO Outro Contact
    String email_candidaturas;
    String telefone_candidaturas;

    // TODO Resolver aqui essas relações, não são One To One
    @OneToOne
    @JoinColumn(name = "main_address_id"
    //        , nullable = false
    )
    Address mainAddress;

    String website;

    // TODO Fazer como em requisitos
    String redes_sociais;

    String criado_em;
    String atualizado_em;

    public Contact getMain_contact() {
        return main_contact;
    }

    public void setMain_contact(Contact main_contact) {
        this.main_contact = main_contact;
    }

    public Address getAddress() {
        return mainAddress;
    }

    public void setAddress(Address address) {
        this.mainAddress = address;
    }

    public Organization(String nome) {
        this.nome = nome;
    }

    public Organization() {

    }


}
