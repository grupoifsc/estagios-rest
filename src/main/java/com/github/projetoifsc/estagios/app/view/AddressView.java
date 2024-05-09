package com.github.projetoifsc.estagios.app.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.app.interfaces.AddressProjection;
import com.github.projetoifsc.estagios.app.interfaces.OrgPublicProfileProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Validated
@Schema(name = "Endereço", description = "Endereço")
public class AddressView implements Serializable, AddressProjection {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    private String rua;
    private String bairro;

    @NotBlank
    @JsonProperty(required = true)
    private String cidade;

    @NotBlank
    @JsonProperty(required = true)
    private String estado;

    @NotBlank
    @JsonProperty(required = true)
    private String pais;

    @Override
    public String getId() {
        return id;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

   public String getPais() {
        return pais;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(@NotBlank String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(@NotBlank String estado) {
        this.estado = estado;
    }

    public void setPais(@NotBlank String pais) {
        this.pais = pais;
    }

}
