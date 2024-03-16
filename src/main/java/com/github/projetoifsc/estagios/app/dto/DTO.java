package com.github.projetoifsc.estagios.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.projetoifsc.estagios.app.utils.validation.Validatable;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Validated
public abstract class DTO extends RepresentationModel<DTO> implements Serializable, Validatable {

    @Override
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(hidden = true)
    public Links getLinks() {
        return super.getLinks();
    }

    public abstract void addHypermediaLinks();


}
