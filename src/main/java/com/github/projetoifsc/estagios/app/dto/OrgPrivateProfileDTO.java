package com.github.projetoifsc.estagios.app.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.*;
import com.github.projetoifsc.estagios.app.utils.hateoas.UserHateoasHelper;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.validation.annotation.Validated;

import com.github.projetoifsc.estagios.app.dto.shared.Contato;
import com.github.projetoifsc.estagios.app.dto.shared.Localizacao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Schema(name="Perfil Privado", description = "Perfil privado da instituição ou empresa")
@JsonPropertyOrder(value = {"id", "username", "cnpj", "nome",
		"instituicaoDeEnsino", "info", "contatoPrincipal", "contatoCandidaturas",
		"endereco", "criadoEm", "atualizadoEm", "links"})
@Validated
public class OrgPrivateProfileDTO extends OrgPublicProfileDTO {

	@JsonProperty(required = true)
	@Schema(description = "Identificador para autenticação", requiredMode = Schema.RequiredMode.REQUIRED, example = "nobanks")
	@NotBlank
	private String username;

	@Schema(description = "CNPJ válido da instituição ou empresa", requiredMode = Schema.RequiredMode.REQUIRED, example="18009962000177")
	@NotNull
	@CNPJ
	private String cnpj;

	@JsonProperty(value = "endereco")
	@Schema(description = "Endereço principal da instituição ou empresa", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull
	@Valid
	private Localizacao address;

	@JsonProperty(value = "contatoCandidaturas")
	@Schema(description = "Contato para envio de currículos. Caso este campo não seja informado, iremos considerar o contato principal. Além disto, no cadastro de vagas é possível inserir contatos para as vagas em específico. Será mostrado apenas no momento de visualização das vagas que você publicar", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Valid
	private Contato applicationContact;

	@JsonPropertyDescription("Criado em ")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY,requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty(value = "criadoEm", access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;


	@Schema(accessMode = Schema.AccessMode.READ_ONLY,requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(value = "atualizadoEm", access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime updatedAt;

	public OrgPrivateProfileDTO(String key, String name, boolean ie, String info, Contato mainContact, String username, String cnpj, Localizacao address, Contato applicationContact, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super(key, name, ie, info, mainContact);
		this.username = username;
		this.cnpj = cnpj;
		this.address = address;
		this.applicationContact = applicationContact;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Localizacao getAddress() {
		return address;
	}

	public void setAddress(Localizacao address) {
		this.address = address;
	}

	public Contato getApplicationContact() {
		return applicationContact;
	}

	public void setApplicationContact(Contato applicationContact) {
		this.applicationContact = applicationContact;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public void addHypermediaLinks() {
		UserHateoasHelper.addUserPrivateProfileLinks(this);
	}

}
