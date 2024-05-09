package com.github.projetoifsc.estagios.infra.db.jpa;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "priority", discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "adresses")
abstract class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    OrganizationEntity owner;

    String rua;
    String bairro;
    String cidade;
    String estado;
    String pais;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    OrganizationEntity getOwner() {
        return owner;
    }

    void setOwner(OrganizationEntity owner) {
        this.owner = owner;
    }

    String getRua() {
        return rua;
    }

    void setRua(String rua) {
        this.rua = rua;
    }

    String getBairro() {
        return bairro;
    }

    void setBairro(String bairro) {
        this.bairro = bairro;
    }

    String getCidade() {
        return cidade;
    }

    void setCidade(String cidade) {
        this.cidade = cidade;
    }

    String getEstado() {
        return estado;
    }

    void setEstado(String estado) {
        this.estado = estado;
    }

    String getPais() {
        return pais;
    }

    void setPais(String pais) {
        this.pais = pais;
    }

    LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
