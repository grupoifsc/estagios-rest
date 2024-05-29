package com.github.projetoifsc.estagios.infra.db.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "user_credentials")
class UserCredentialsEntity {

    @Id
    @Column(name = "org_id", nullable = false)
    private Long id;

    private String email;

    private String pwd;

    private String role;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "org_id")
    private OrganizationEntity organization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getPassword() {
        return pwd;
    }

    public void setPassword(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

}
