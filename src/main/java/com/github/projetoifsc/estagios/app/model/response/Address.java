package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.core.models.projections.AddressDetailsProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@JsonPropertyOrder({"id", "tipo", "rua", "bairro", "cidade", "estado", "pais"})
public class Address implements AddressDetailsProjection {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(example = "1")
    private String id;

    @JsonProperty(value = "tipo", access = JsonProperty.Access.READ_ONLY)
    @Schema(example = "principal", allowableValues = {"principal", "outro"})
    private String type;

    @Schema(example = "Rua Manoel Florentino, 555")
    private String rua;

    @Schema(example = "Centro")
    private String bairro;

    @Schema(example = "São José")
    @NotBlank
    private String cidade;

    @Schema(example = "SC")
    @NotBlank
    private String estado;

    @Schema(example = "BRA")
    @NotBlank
    private String pais;

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
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    @Override
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Override
    public @NotBlank String getCidade() {
        return cidade;
    }

    public void setCidade(@NotBlank String cidade) {
        this.cidade = cidade;
    }

    @Override
    public @NotBlank String getEstado() {
        return estado;
    }

    public void setEstado(@NotBlank String estado) {
        this.estado = estado;
    }

    @Override
    public @NotBlank String getPais() {
        return pais;
    }

    public void setPais(@NotBlank String pais) {
        this.pais = pais;
    }
}
