package com.github.projetoifsc.estagios.app.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.app.model.response.*;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import com.github.projetoifsc.estagios.core.models.IOrgEntryData;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.Arrays;
import java.util.List;

@JsonPropertyOrder(value = {"nome", "credenciais", "cnpj",
        "instituicao_de_ensino", "info", "contato_principal", "contato_candidaturas",
        "endereco", "website", "redes_sociais"})
public class OrgEntryData implements IOrgEntryData {

    private final Mapper mapper = new Mapper();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JsonProperty(value = "nome")
    @Schema(description = "Nome da Organização", requiredMode = Schema.RequiredMode.REQUIRED, example = "Nobanks")
    @NotBlank
    private String nome;

    @Schema(description = "CNPJ válido da organização", requiredMode = Schema.RequiredMode.REQUIRED, example="18009962000177")
    @CNPJ
    private String cnpj;

    @JsonProperty(value = "instituicao_de_ensino")
    @Schema(description = "É uma Instituição de Ensino?", requiredMode = Schema.RequiredMode.REQUIRED,type = "boolean", allowableValues = {"true", "false"})
    @NotNull
    private Boolean ie;

    @JsonProperty(value = "info")
    @Schema(description = "Informações sobre a organização", requiredMode = Schema.RequiredMode.REQUIRED, example = "Uma organização sem bancos", maxLength = 500)
    @NotBlank
    private String info;

    @JsonIgnore
    @Valid
    private Contact mainContact;

    @JsonProperty("contato_principal")
    @NotNull
    private ContactWithoutType mainContactWithoutType;

    @JsonIgnore
    @Valid
    private Contact applianceContact;

    @JsonProperty("contato_candidaturas")
    private ContactWithoutType applianceContactWithoutType;

    @JsonIgnore
    private Address mainAddress;

    @JsonProperty("endereco")
    private AddressWithoutType mainAddressWithoutType;

    private String website;

    @JsonIgnore
    private String redesSociais;

    @JsonProperty(value = "redes_sociais")
    private List<String> redesSociaisList;

    @JsonProperty("credenciais")
    private UserCredentialsProjectionEntryData userCredentials;


    //@Override
    public String getId() {
        return id;
    }

    //@Override
    public void setId(String id) {
        this.id = id;
    }

    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    public @CNPJ String getCnpj() {
        return cnpj;
    }

    public void setCnpj(@CNPJ String cnpj) {
        this.cnpj = cnpj;
    }

    //@Override
    public @NotNull Boolean getIe() {
        return ie;
    }

    public void setIe(@NotNull Boolean ie) {
        this.ie = ie;
    }

    public @NotBlank String getInfo() {
        return info;
    }

    public void setInfo(@NotBlank String info) {
        this.info = info;
    }

    //@Override
    public Contact getMainContact() {
        return mainContact;
    }

    public void setMainContact(@NotNull @Valid Contact mainContact) {
        this.mainContact = mainContact;
        this.mainContactWithoutType = mapper.map(this.mainContact, ContactWithoutType.class);
    }

    public @NotNull ContactWithoutType getMainContactWithoutType() {
        return mainContactWithoutType;
    }

    public void setMainContactWithoutType(@NotNull ContactWithoutType mainContactWithoutType) {
        this.mainContactWithoutType = mainContactWithoutType;
        this.mainContact = mapper.map(this.mainContactWithoutType, Contact.class);
        this.mainContact.setType("main");
    }

//    @Override
    public @Valid Contact getApplianceContact() {
        return applianceContact;
    }

    public void setApplianceContact(@Valid Contact applianceContact) {
        this.applianceContact = applianceContact;
        this.applianceContactWithoutType = mapper.map(this.applianceContact, ContactWithoutType.class);
    }

    public ContactWithoutType getApplianceContactWithoutType() {
        return applianceContactWithoutType;
    }

    public void setApplianceContactWithoutType(ContactWithoutType applianceContactWithoutType) {
        this.applianceContactWithoutType = applianceContactWithoutType;
        this.applianceContact = mapper.map(this.applianceContactWithoutType, Contact.class);
        this.applianceContact.setType("appliance");
    }

    //@Override
    public Address getMainAddress() {
       return mainAddress;
    }

    public void setMainAddress(Address mainAddress) {
        this.mainAddress = mainAddress;
        this.mainAddressWithoutType = mapper.map(this.mainAddress, AddressWithoutType.class);
    }

    public AddressWithoutType getMainAddressWithoutType() {
        return mainAddressWithoutType;
    }

    public void setMainAddressWithoutType(AddressWithoutType mainAddressWithoutType) {
        this.mainAddressWithoutType = mainAddressWithoutType;
        this.mainAddress = mapper.map(this.mainAddressWithoutType, Address.class);
        this.mainAddress.setType("main");
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getRedesSociais() {
        return redesSociais;
    }

    public void setRedesSociais(String redesSociais) {
        this.redesSociais = redesSociais;
        this.redesSociaisList = Arrays.asList(this.redesSociais.split(";"));
    }

    public List<String> getRedesSociaisList() {
        return redesSociaisList;
    }

    public void setRedesSociaisList(List<String> redesSociaisList) {
        this.redesSociaisList = redesSociaisList;
        this.redesSociais = String.join(";", this.redesSociaisList);
    }

    public UserCredentialsProjectionEntryData getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(UserCredentialsProjectionEntryData userCredentials) {
        this.userCredentials = userCredentials;
    }

}
