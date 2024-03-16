package com.github.projetoifsc.estagios.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.app.dto.shared.Contato;
import com.github.projetoifsc.estagios.app.utils.hateoas.UserHateoasHelper;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Schema(name = "Perfil Público da Organização", description = "Perfil Público da Organização")
@JsonPropertyOrder(value = {"id", "nome", "instituicaoDeEnsino",
        "info", "contatoPrincipal", "links"})
@Validated
public class UserPublicProfileDTO extends UserDTO {

    @JsonProperty(value = "info", required = true)
    @Schema(description = "Informações sobre a organização", requiredMode = Schema.RequiredMode.REQUIRED, example = "Uma organização sem bancos", maxLength = 500)
    @NotBlank
    private String info;

    @JsonProperty(value = "contatoPrincipal", required = true)
    @NotNull
    @Valid
    private Contato mainContact;


    public UserPublicProfileDTO(String key, String name, boolean ie, String info, Contato mainContact) {
        super(key, name, ie);
        this.info = info;
        this.mainContact = mainContact;
    }


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Contato getMainContact() {
        return mainContact;
    }

    public void setMainContact(Contato mainContact) {
        this.mainContact = mainContact;
    }

    @Override
    public void addHypermediaLinks() {
        UserHateoasHelper.addUserPublicProfileLinks(this);
    }

}
