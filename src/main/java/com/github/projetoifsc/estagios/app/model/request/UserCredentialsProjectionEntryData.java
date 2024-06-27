package com.github.projetoifsc.estagios.app.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.core.models.projections.OrgPrivateProfileProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

@JsonPropertyOrder({"email", "senha"})
public class UserCredentialsProjectionEntryData implements OrgPrivateProfileProjection.UserCredentialsProjection {

    @Schema(description = "Identificador para autenticação", requiredMode = Schema.RequiredMode.REQUIRED, example = "nobanks")
    @Email
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @JsonIgnore
    private String pwd;

    @Override
    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
        this.pwd = senha;
    }

    @Override
    @JsonIgnore
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}

