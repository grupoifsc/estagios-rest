package com.github.projetoifsc.estagios.app.model.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.*;
import com.github.projetoifsc.estagios.app.model.interfaces.OrgPrivateProfileProjection;
import com.github.projetoifsc.estagios.app.model.shared.ContactView;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.validation.annotation.Validated;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="Perfil Privado", description = "Perfil privado da instituição ou empresa")
@JsonPropertyOrder(value = {"id", "username", "nome", "cnpj",
		"instituicao_de_ensino", "info", "contato_principal", "contato_candidaturas",
		"endereco", "website", "redes_sociais", "criado_em", "atualizado_em", "_links"})
@Validated
public class OrgPrivateProfileResponse extends OrgPublicProfileBasicInfoView implements OrgPrivateProfileProjection {

	@JsonProperty(required = true)
	@Schema(description = "Identificador para autenticação", requiredMode = Schema.RequiredMode.REQUIRED, example = "nobanks")
	@NotBlank
	private String username;

	@JsonProperty(required = true)
	@Schema(description = "CNPJ válido da instituição ou empresa", requiredMode = Schema.RequiredMode.REQUIRED, example="18009962000177")
	@CNPJ
	private String cnpj;

	@JsonProperty(value = "contato_candidaturas")
	@Valid
	private ContactView applianceContact;

	@Schema(accessMode = Schema.AccessMode.READ_ONLY,requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty(value = "criado_em", access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;

	@Schema(accessMode = Schema.AccessMode.READ_ONLY,requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(value = "atualizado_em", access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime updatedAt;


//	@Override
	public @NotBlank String getUsername() {
		return username;
	}

	public void setUsername(@NotBlank String username) {
		this.username = username;
	}

	@Override
	public @CNPJ String getCnpj() {
		return cnpj;
	}

	public void setCnpj(@CNPJ String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public @Valid ContactView getApplianceContact() {
		return applianceContact;
	}

	public void setApplianceContact(@Valid ContactView applianceContact) {
		this.applianceContact = applianceContact;
	}

	@Override
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
