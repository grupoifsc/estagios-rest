package com.github.projetoifsc.estagios.infra.db.jpa;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@EnableJpaAuditing
@Entity
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "priority", discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "contacts")
abstract class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    OrganizationEntity owner;

    String email;
    String telefone;

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

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getTelefone() {
        return telefone;
    }

    void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
