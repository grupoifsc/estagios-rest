package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.*;
import com.github.projetoifsc.estagios.core.models.projections.OrgSummaryProjection;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonPropertyOrder(value = {"id", "nome",
        "instituicao_de_ensino"
})
public class OrgSummary implements OrgSummaryProjection {

    private String id;

    @Schema(description = "Nome da Organização", requiredMode = Schema.RequiredMode.REQUIRED, example = "Nobanks")
    private String nome;

    @JsonProperty(value = "instituicao_de_ensino")
    @Schema(description = "É uma Instituição de Ensino?", requiredMode = Schema.RequiredMode.REQUIRED,type = "boolean", allowableValues = {"true", "false"})
    private Boolean ie;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public Boolean getIe() {
        return ie;
    }

    public void setIe(Boolean ie) {
        this.ie = ie;
    }

}
