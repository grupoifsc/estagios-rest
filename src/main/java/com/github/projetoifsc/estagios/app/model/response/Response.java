package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.projetoifsc.estagios.app.utils.validation.Validatable;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Validated
public abstract class Response extends RepresentationModel<Response> implements Serializable, Validatable {

  //  @Override
    @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "_links")
    @Schema(hidden = true)
    public Links getLinks() {
        var link = Link.of("http://link.com", "self");
        return Links.of(link);
        //return super.getLinks();
    }

    @JsonProperty(value = "_teste")
    public String getTeste() { return "Estou vindo de View...";}

    public abstract void addHypermediaLinks();


}
