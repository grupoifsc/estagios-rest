package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.models.projections.ContactDetailsProjection;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link ContactEntity}
 */
class ContactDetailsDTO implements ContactDetailsProjection {

    private Long id;
    private String email;
    private String telefone;
    private String type;

    public String getId() {
        return String.valueOf(id);
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}