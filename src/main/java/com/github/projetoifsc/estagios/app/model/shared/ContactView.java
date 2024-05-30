package com.github.projetoifsc.estagios.app.model.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.projetoifsc.estagios.app.model.interfaces.ContactProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
@Schema(name = "Contato")
public class ContactView implements ContactProjection {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @Email
    @JsonProperty(required = true)
    @Schema(description = "Email válido", example = "rh@nobanks.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String email;

    @NotBlank
    @JsonProperty(value = "telefone", required = true)
    @Schema(description = "Telefone válido", example = "48 3555-5500", requiredMode = Schema.RequiredMode.REQUIRED)
    private String telefone;


    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public @Email @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotBlank String email) {
        this.email = email;
    }

    @Override
    public @NotBlank String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotBlank String telefone) {
        this.telefone = telefone;
    }


}
