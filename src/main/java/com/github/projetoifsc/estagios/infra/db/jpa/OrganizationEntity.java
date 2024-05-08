package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.IOrganization;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

// TODO Aprender mais sobre Auditable
//  https://codersathi.com/auto-generate-created-and-modified-date-time-in-spring-boot/


@Entity(name = "Organization")
@EntityListeners(AuditingEntityListener.class)
@Table(name="orgs")
class OrganizationEntity implements IOrganization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String nome;

    private String cnpj;

    boolean ie;

    private String info;

    @OneToMany(mappedBy = "owner")
    private List<Contact> contatos;

    @OneToMany(mappedBy = "owner")
    private List<ContactMain> mainContact;

    @OneToMany(mappedBy = "owner")
    private List<ContactAppliance> applianceContact;

    @OneToMany(mappedBy = "owner")
    private List<Address> enderecos;

    @OneToMany(mappedBy = "owner")
    private List<AddressMain> mainAddress;

    private String website;

    // TODO Fazer como em requisitos, forçando uma má prática em
    @Column(name = "redes_sociais")
    private String redesSociais;

    @CreatedDate
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    LocalDateTime updatedAt;


    public OrganizationEntity() {
    }

    OrganizationEntity(String username, String nome, String cnpj, boolean ie, String info, String website, String redesSociais) {
        this.username = username;
        this.nome = nome;
        this.cnpj = cnpj;
        this.ie = ie;
        this.info = info;
        this.website = website;
        this.redesSociais = redesSociais;
    }

    @Override
    public String getId() {
        return Long.toString(id);
    }

    @Override
    public void setId(String id) {
        this.id = Long.parseLong(id);
    }

    @Override
    public Boolean getIe() {
        return ie;
    }


    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
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
