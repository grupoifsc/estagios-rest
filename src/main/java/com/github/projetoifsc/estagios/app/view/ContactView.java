package com.github.projetoifsc.estagios.app.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.projetoifsc.estagios.app.interfaces.ContactProjection;
import com.github.projetoifsc.estagios.app.interfaces.OrgPublicProfileProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Validated
@Schema(name = "Contato")
public class ContactView implements Serializable, ContactProjection {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @Email
    @JsonProperty(required = true)
    private String email;

    @NotBlank
    @JsonProperty(required = true)
    private String telefone;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public void setTelefone(@NotBlank String telefone) {
        this.telefone = telefone;
    }

}
