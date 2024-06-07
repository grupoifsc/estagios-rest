package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.app.model.shared.ContactModel;
import com.github.projetoifsc.estagios.core.models.OrgPrivateProfileProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Schema(name="Perfil Privado", description = "Perfil privado da instituição ou empresa")
@JsonPropertyOrder(value = {"id", "user_credentials", "nome", "cnpj",
		"instituicao_de_ensino", "info", "contato_principal", "contato_candidaturas",
		"endereco", "website", "redes_sociais", "criado_em", "atualizado_em", "_links"})
@Validated
public class PrivateOrgProfileResponse extends PublicOrgProfileResponse implements OrgPrivateProfileProjection {

	@JsonProperty("user_credentials")
	private UserCredentialsResponse userCredentials;

	@JsonProperty(required = true)
	@Schema(description = "CNPJ válido da instituição ou empresa", requiredMode = Schema.RequiredMode.REQUIRED, example="18009962000177")
	@CNPJ
	private String cnpj;

	@JsonProperty(value = "contato_candidaturas")
	@Valid
	private ContactModel applianceContact;

	@Schema(accessMode = Schema.AccessMode.READ_ONLY,requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty(value = "criado_em", access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;

	@Schema(accessMode = Schema.AccessMode.READ_ONLY,requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(value = "atualizado_em", access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime updatedAt;


	@Override
	public UserCredentialsProjection getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(UserCredentialsResponse userCredentials) {
		this.userCredentials = userCredentials;
	}

	@Override
	public @CNPJ String getCnpj() {
		return cnpj;
	}

	public void setCnpj(@CNPJ String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public @Valid ContactModel getApplianceContact() {
		return applianceContact;
	}

	public void setApplianceContact(@Valid ContactModel applianceContact) {
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


	public static class UserCredentialsResponse implements UserCredentialsProjection {

		@JsonProperty(required = true)
		@Schema(description = "Identificador para autenticação", requiredMode = Schema.RequiredMode.REQUIRED, example = "nobanks")
		@Email
		private String email;

		@JsonIgnore
		private String pwd;

		@Override
		public String getEmail() {
			return email;
		}

		@Override
		public String getPwd() {
			return pwd;
		}

		public void setEmail(@Email String email) {
			this.email = email;
		}

		public void setPwd(String pwd) {
			this.pwd = pwd;
		}

	}
}
