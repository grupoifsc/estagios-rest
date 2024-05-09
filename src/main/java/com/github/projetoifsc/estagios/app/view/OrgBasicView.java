package com.github.projetoifsc.estagios.app.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.projetoifsc.estagios.core.IOrganization;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;


@Schema(name = "Organização", description = "Sumário da Organização")
@Validated
public abstract class OrgBasicView extends SerializableView implements IOrganization {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Id", example="123", accessMode = Schema.AccessMode.READ_ONLY, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String key;

    @JsonProperty(value = "nome", required = true)
    @Schema(description = "Nome da Organização", requiredMode = Schema.RequiredMode.REQUIRED, example = "Nobanks")
    @NotBlank
    private String name;

    @JsonProperty(value = "instituicao_de_ensino", required = true, defaultValue = "false")
    @Schema(description = "É uma Instituição de Ensino?", requiredMode = Schema.RequiredMode.REQUIRED,type = "boolean", allowableValues = {"true", "false"}, example = "false")
    private boolean ie = false;


    @Override
    public String getId() {
        return key;
    }

    @Override
    public void setId(String id) {
        this.key = id;
    }

    @NotBlank public String getNome() {
        return name;
    }

    public void setNome(@NotBlank String name) {
        this.name = name;
    }

    @Override
    public Boolean getIe() {
        return ie;
    }

    public void setIe(boolean ie) {
        this.ie = ie;
    }


    @Override
    public void addHypermediaLinks() {
        // TODO: implement Logic To HyperMedia Links here?
    }
}
