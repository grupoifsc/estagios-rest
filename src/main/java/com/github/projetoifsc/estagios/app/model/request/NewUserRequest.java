package com.github.projetoifsc.estagios.app.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.app.interfaces.INewUser;
import com.github.projetoifsc.estagios.app.interfaces.OrgPrivateProfileProjection;
import com.github.projetoifsc.estagios.app.model.response.AdressPublicView;
import com.github.projetoifsc.estagios.app.model.response.ContactPublicView;
import com.github.projetoifsc.estagios.app.model.shared.ContactView;
import com.github.projetoifsc.estagios.core.IOrganization;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.validation.annotation.Validated;

@Schema(name="Perfil Privado", description = "Perfil privado da instituição ou empresa")
@JsonPropertyOrder(value = {"nome", "email", "senha", "cnpj",
        "instituicao_de_ensino", "info", "contato_principal", "contato_candidaturas",
        "endereco", "website", "redes_sociais"})
@Validated
public class NewUserRequest implements INewUser {

    @JsonIgnore
    private String id;

    @JsonProperty(value = "nome", required = true)
    @Schema(description = "Nome da Organização", requiredMode = Schema.RequiredMode.REQUIRED, example = "Nobanks")
    @NotBlank
    private String nome;

    @JsonProperty(value = "instituicao_de_ensino", required = true, defaultValue = "false")
    @Schema(description = "É uma Instituição de Ensino?", requiredMode = Schema.RequiredMode.REQUIRED,type = "boolean", allowableValues = {"true", "false"}, example = "false")
    private boolean ie = false;

    @JsonProperty(required = true)
    @Schema(description = "Email para autenticação", requiredMode = Schema.RequiredMode.REQUIRED, example = "nobanks@email.com")
    @Email
    private String email;

    @JsonProperty(value = "senha", required = true)
    @Schema(description = "Senha segura para autenticação", requiredMode = Schema.RequiredMode.REQUIRED, example = "senhaSegura159$%")
    private String password;

    @JsonProperty(required = true)
    @Schema(description = "CNPJ válido da organização", requiredMode = Schema.RequiredMode.REQUIRED, example="18009962000177")
    @CNPJ
    private String cnpj;

    @JsonProperty(value = "info", required = true)
    @Schema(description = "Informações sobre a organização", requiredMode = Schema.RequiredMode.REQUIRED, example = "Uma organização sem bancos", maxLength = 500)
    @NotBlank
    private String info;

    private String website;

    @JsonProperty(value = "redes_sociais")
    private String redesSociais;

    @JsonProperty(value = "contato_principal", required = true)
    @NotNull
    @Valid
    private ContactPublicView mainContact;

    @JsonProperty(value = "endereco", required = true)
    @NotNull
    @Valid
    private AdressPublicView mainAddress;

    @JsonProperty(value = "contato_candidaturas")
    @Valid
    private ContactView applianceContact;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    @Override
    public Boolean getIe() {
        return ie;
    }

    public void setIe(boolean ie) {
        this.ie = ie;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public @CNPJ String getCnpj() {
        return cnpj;
    }

    public void setCnpj(@CNPJ String cnpj) {
        this.cnpj = cnpj;
    }

    public @NotBlank String getInfo() {
        return info;
    }

    public void setInfo(@NotBlank String info) {
        this.info = info;
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
    }

    @Override
    public @NotNull @Valid ContactPublicView getMainContact() {
        return mainContact;
    }

    public void setMainContact(@NotNull @Valid ContactPublicView mainContact) {
        this.mainContact = mainContact;
    }

    @Override
    public @NotNull @Valid AdressPublicView getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(@NotNull @Valid AdressPublicView mainAddress) {
        this.mainAddress = mainAddress;
    }

    @Override
    public @Valid ContactView getApplianceContact() {
        return applianceContact;
    }

    public void setApplianceContact(@Valid ContactView applianceContact) {
        this.applianceContact = applianceContact;
    }
}
