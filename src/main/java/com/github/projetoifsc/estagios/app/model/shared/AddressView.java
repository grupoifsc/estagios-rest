package com.github.projetoifsc.estagios.app.model.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.projetoifsc.estagios.app.interfaces.AddressProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
@Schema(name = "Endereço", description = "Endereço")
public class AddressView implements AddressProjection {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JsonProperty(value = "rua", required = true)
    @Schema(example = "Rua Hercílio Luz, nº 570, Centro", description = "Endereço principal das vagas que serão oferecidas no sistema. Caso necessário, no momento de cadastro de novas vagas, é possível definir endereços específicos", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String rua;

    private String bairro;

    @NotBlank
    @JsonProperty(value = "cidade", required = true)
    @Schema(description = "Cidade", example = "Florianópolis", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cidade;

    @NotBlank
    @JsonProperty(value = "estado", required = true)
    @Schema(description = "Nome completo do estado", example = "Santa Catarina", requiredMode = Schema.RequiredMode.REQUIRED)
    private String estado;

    @NotBlank
    @JsonProperty(value = "pais", required = true)
    @Schema(description = "País", example = "Brasil", requiredMode = Schema.RequiredMode.REQUIRED)
    private String pais;


    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public @NotBlank String getRua() {
        return rua;
    }

    public void setRua(@NotBlank String rua) {
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
