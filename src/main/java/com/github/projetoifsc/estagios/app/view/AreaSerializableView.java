package com.github.projetoifsc.estagios.app.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@JsonPropertyOrder(value = {"id", "nome", "links"})
public class AreaSerializableView extends SerializableView {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    @Schema(example = "1")
    private String id;

    @JsonIgnore
    private OrgBasicView owner;

    @JsonProperty(value = "nome", required = true)
    @Schema(description = "Nome da área", example = "Educação",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String name;


    public AreaSerializableView(String id, OrgBasicView owner, String name) {
        this.id = id;
        this.owner = owner;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrgBasicView getOwner() {
        return owner;
    }

    public void setOwner(OrgBasicView owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void addHypermediaLinks() {
        // TODO
    }
}
