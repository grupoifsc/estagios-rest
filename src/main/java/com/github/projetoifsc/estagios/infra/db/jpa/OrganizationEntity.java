package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.IAddress;
import com.github.projetoifsc.estagios.core.IContact;
import com.github.projetoifsc.estagios.core.IOrganization;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

//  Auditable:
//  https://codersathi.com/auto-generate-created-and-modified-date-time-in-spring-boot/


@Entity(name = "Organization")
@EntityListeners(AuditingEntityListener.class)
@Table(name="orgs")
class OrganizationEntity implements IOrganization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = EAGER)
    @JoinColumn(name = "user_id")
    private UserCredentialsEntity userCredentials;

    private String nome;

    private String cnpj;

    boolean ie;

    private String info;

    @OneToMany(mappedBy = "owner", fetch = LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobEntity> createdJobs = new ArrayList<>();

    @OneToMany(mappedBy = "owner", fetch = LAZY, cascade = CascadeType.ALL)
    private List<ContactEntity> contatos;

    @OneToMany(mappedBy = "owner", fetch = LAZY)
    private List<ContactMainEntity> mainContact = new ArrayList<>();

    @OneToMany(mappedBy = "owner", fetch = LAZY)
    private List<ContactApplianceEntity> applianceContact;

    @OneToMany(mappedBy = "owner", fetch = LAZY, cascade = CascadeType.ALL)
    List<AddressEntity> enderecos;

    @OneToMany(mappedBy = "owner", fetch = LAZY)
    private List<AddressMainEntity> mainAddress = new ArrayList<>();

    private String website;

    @Column(name = "redes_sociais")
    private String redesSociais;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "exclusiveReceivers", fetch = LAZY)
    List<JobEntity> exclusiveReceivedJobs;

    public OrganizationEntity() {
    }

    OrganizationEntity(String nome, String cnpj, boolean ie, String info, String website, String redesSociais) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.ie = ie;
        this.info = info;
        this.website = website;
        this.redesSociais = redesSociais;
    }

    // TODO: tenho que fazer direito esse negócio de id...
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

    public List<ContactEntity> getContatos() {
        return contatos;
    }

    public void setContatos(List<ContactEntity> contatos) {
        this.contatos = contatos;
    }

    public IContact getMainContact() {
        return !mainContact.isEmpty() ? mainContact.get(0) : null;
    }

    public void setMainContact(List<ContactMainEntity> mainContact) {
        this.mainContact = mainContact;
    }

    public List<ContactApplianceEntity> getApplianceContact() {
        return applianceContact;
    }

    public void setApplianceContact(List<ContactApplianceEntity> applianceContact) {
        this.applianceContact = applianceContact;
    }

    public List<AddressEntity> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<AddressEntity> enderecos) {
        this.enderecos = enderecos;
    }

    public IAddress getMainAddress() {
        return !mainAddress.isEmpty() ? mainAddress.get(0) : null ;
    }

    public void setMainAddress(List<AddressMainEntity> mainAddress) {
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

    public UserCredentialsEntity getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(UserCredentialsEntity userCredentials) {
        this.userCredentials = userCredentials;
    }
}
