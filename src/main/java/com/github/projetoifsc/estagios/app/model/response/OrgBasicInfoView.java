package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.app.interfaces.OrgBasicInfoProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;


@Schema(name = "Organização", description = "Sumário da Organização")
@Validated
@JsonPropertyOrder(value = {"id", "nome", "instituicao_de_ensino"})
public class OrgBasicInfoView extends View implements OrgBasicInfoProjection {

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

    @Override
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
