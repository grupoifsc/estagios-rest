package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.IOrganization;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

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

    @OneToMany(mappedBy = "owner", fetch = LAZY, cascade = CascadeType.ALL)
    private List<Contact> contatos;

    @OneToMany(mappedBy = "owner", fetch = LAZY)
    private List<ContactMain> mainContact;

    @OneToMany(mappedBy = "owner", fetch = LAZY)
    private List<ContactAppliance> applianceContact;

    @OneToMany(mappedBy = "owner", fetch = LAZY, cascade = CascadeType.ALL)
    List<Address> enderecos;

    @OneToMany(mappedBy = "owner", fetch = LAZY)
    private List<AddressMain> mainAddress;

    private String website;

    // TODO Fazer como em requisitos, forçando uma má prática em
    @Column(name = "redes_sociais")
    private String redesSociais;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public boolean isIe() {
        return ie;
    }

    public void setIe(boolean ie) {
        this.ie = ie;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Contact> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contact> contatos) {
        this.contatos = contatos;
    }

    public List<ContactMain> getMainContact() {
        return mainContact;
    }

    public void setMainContact(List<ContactMain> mainContact) {
        this.mainContact = mainContact;
    }

    public List<ContactAppliance> getApplianceContact() {
        return applianceContact;
    }

    public void setApplianceContact(List<ContactAppliance> applianceContact) {
        this.applianceContact = applianceContact;
    }

    public List<Address> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Address> enderecos) {
        this.enderecos = enderecos;
    }

    public List<AddressMain> getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(List<AddressMain> mainAddress) {
        this.mainAddress = mainAddress;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getRedesSociais() {
        return redesSociais;
    }

    public void setRedesSociais(String redesSociais) {
        this.redesSociais = redesSociais;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
