package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@JsonPropertyOrder(value = {"id", "nome",
        "instituicao_de_ensino"
})
public class OrgSummary implements com.github.projetoifsc.estagios.core.models.projections.OrgSummaryProjection {

    private String id;

    @JsonProperty(value = "nome")
    @Schema(description = "Nome da Organização", requiredMode = Schema.RequiredMode.REQUIRED, example = "Nobanks")
    @NotBlank
    private String nome;

    @JsonProperty(value = "instituicao_de_ensino")
    @Schema(description = "É uma Instituição de Ensino?", requiredMode = Schema.RequiredMode.REQUIRED,type = "boolean", allowableValues = {"true", "false"})
    @NotNull
    private Boolean ie;

    @Override
    public String getId() {
        return id;
    }

    //@Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    @Override
    public @NotNull Boolean getIe() {
        return ie;
    }

    public void setIe(@NotNull Boolean ie) {
        this.ie = ie;
    }

}
