package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.IArea;
import com.github.projetoifsc.estagios.core.IOrganization;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="areas")
class Area implements IArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    OrganizationEntity owner;

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

    OrganizationEntity getOwner() {
        return owner;
    }

    @Override
    public String getId() {
        return Long.toString(id);
    }

    // Tive que usar cast aqui pra encaixar com as restrições do JPA
    @Override
    public void setOwner(IOrganization owner) {
        this.owner = (OrganizationEntity) owner;
    }

}
