package com.github.projetoifsc.estagios.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.core.IOrganization;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Schema(name = "Organização", description = "Sumário da Organização")
@JsonPropertyOrder(value = {"id", "nome", "instituicaoDeEnsino", "links"})
@Validated
public class OrgDTO extends DTO implements IOrganization {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Id", example="123", accessMode = Schema.AccessMode.READ_ONLY, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String key;

    @JsonProperty(value = "nome", required = true)
    @Schema(description = "Nome da Organização", requiredMode = Schema.RequiredMode.REQUIRED, example = "Nobanks")
    @NotBlank
    private String name;

    @JsonProperty(value = "instituicaoDeEnsino", required = true, defaultValue = "false")
    @Schema(description = "É uma Instituição de Ensino?", requiredMode = Schema.RequiredMode.REQUIRED,type = "boolean", allowableValues = {"true", "false"}, example = "false")
    private boolean ie = false;


    public OrgDTO() {
    }

    public OrgDTO(String key, String name, boolean ie) {
        this.key = key;
        this.name = name;
        this.ie = ie;
    }

    public String getId() {
        return key;
    }

    public void setId(String key) {
        this.key = key;
    }

    @JsonIgnore
    @Override
    public Boolean getIe() {
        return ie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIe(boolean ie) {
        this.ie = ie;
    }


    @Override
    public String toString() {
        return "UserDTO{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", ie=" + ie +
                ", links=" + super.getLinks() + '\'' +
                '}';
    }

    @Override
    public void addHypermediaLinks() {
        //
    }
}
