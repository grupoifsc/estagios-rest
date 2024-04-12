package com.github.projetoifsc.estagios.infra.db.jpa;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="areas")
class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    Organization owner;

    @CreatedDate
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    LocalDateTime updatedAt;


    public Area() {
    }

    public Area(String nome) {
        this.nome = nome;
    }


    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", owner=" + owner +
                '}';
    }

    Organization getOwner() {
        return owner;
    }
}
