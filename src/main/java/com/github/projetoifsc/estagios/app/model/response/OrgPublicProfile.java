package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import com.github.projetoifsc.estagios.core.models.projections.OrgPublicProfileProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.List;

@JsonPropertyOrder(value = {"id", "nome",
        "instituicao_de_ensino", "info", "contato_principal", "contato_candidaturas",
        "endereco", "website", "redes_sociais", "_links"})
public class OrgPublicProfile extends OrgSummary implements OrgPublicProfileProjection {

    protected final Mapper mapper = new Mapper();

    @JsonProperty(value = "info")
    @Schema(description = "Informações sobre a organização", requiredMode = Schema.RequiredMode.REQUIRED, example = "Uma organização sem bancos", maxLength = 500)
    @NotBlank
    private String info;

    @JsonProperty(value = "contato_principal")
   // @NotNull
    @Valid
    private Contact mainContact;

    @JsonProperty(value = "contato_candidaturas")
    @Valid
    private Contact applianceContact;

    @JsonProperty(value = "endereco")
    private Address mainAddress;

    private String website;

    @JsonIgnore
    private String redesSociais;

    @JsonProperty(value = "redes_sociais")
    private List<String> redesSociaisList;

    @Override
    public @NotBlank String getInfo() {
        return info;
    }

    public void setInfo(@NotBlank String info) {
        this.info = info;
    }

    @Override
    @JsonIgnore
    public @NotNull @Valid Contact getMainContact() {
        return mainContact;
    }

    public void setMainContact(@NotNull @Valid Contact mainContact) {
        this.mainContact = mainContact;
    }

    @JsonProperty("contato_principal")
    public ContactWithoutType getMainContactWithoutType() {
        if(this.mainContact == null) return null;
        return mapper.map(mainContact, ContactWithoutType.class);
    }

    @Override
    @JsonIgnore
    public @Valid Contact getApplianceContact() {
        return applianceContact;
    }

    public void setApplianceContact(@Valid Contact applianceContact) {
        this.applianceContact = applianceContact;
    }

    @JsonProperty("contato_candidaturas")
    public ContactWithoutType getApplianceContactWithoutType() {
        if(this.applianceContact ==  null) return null;
        return mapper.map(applianceContact, ContactWithoutType.class);
    }


    @Override
    @JsonIgnore
    public Address getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(Address mainAddress) {
        this.mainAddress = mainAddress;
    }

    @JsonProperty("endereco")
    public AddressWithoutType getAddressWithoutType() {
        if(this.mainAddress == null) return  null;
        return mapper.map(mainAddress, AddressWithoutType.class);
    }


    @Override
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String getRedesSociais() {
        return redesSociais;
    }

    public void setRedesSociais(String redesSociais) {
        this.redesSociais = redesSociais;
        redesSociaisList = Arrays.asList(redesSociais.split(";"));
    }

    public List<String> getRedesSociaisList() {
        return redesSociaisList;
    }

    public void setRedesSociaisList(List<String> redesSociaisList) {
        this.redesSociaisList = redesSociaisList;
        redesSociais = String.join(";", redesSociaisList);
    }

}
