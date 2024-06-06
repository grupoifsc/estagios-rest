package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.projetoifsc.estagios.app.utils.validation.Validatable;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Validated
public abstract class Response extends RepresentationModel<Response> implements Serializable, Validatable {


    // TODO BugFix: Para algumas subclasses a propriedade _links não está aparecendo de forma padronizada
    @Override
    @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "_links")
    @Schema(hidden = true)
    public Links getLinks() {
        return super.getLinks();
    }

}
