package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.core.models.projections.ContactDetailsProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@JsonPropertyOrder({"id", "tipo", "email", "telefone"})
public class Contact implements ContactDetailsProjection {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(example = "1")
    private String id;

    @JsonProperty(value = "tipo", access = JsonProperty.Access.READ_ONLY)
    @Schema(example = "main", allowableValues = {"principal", "candidatura", "outro"})
    private String type;

    @JsonProperty(required = true)
    @Schema(description = "Email válido", example = "rh@nobanks.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @Email
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
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
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
