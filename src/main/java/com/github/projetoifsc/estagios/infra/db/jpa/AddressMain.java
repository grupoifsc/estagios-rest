package com.github.projetoifsc.estagios.infra.db.jpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("1")
class AddressMain extends Address {

    @Override
    public String toString() {
        return "AddressMain{" +
                "id=" + getId() +
                ", owner=" + getOwner().getId() +
                ", rua='" + getRua() + '\'' +
                ", bairro='" + getBairro() + '\'' +
                ", cidade='" + getCidade() + '\'' +
                ", estado='" + getEstado() + '\'' +
                ", pais='" + getPais() + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + getUpdatedAt() +
                '}';
    }
}

