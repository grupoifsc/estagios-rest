package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.core.models.projections.OrgPrivateProfileProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDateTime;

@JsonPropertyOrder(value = {"id", "nome", "credenciais", "cnpj",
		"instituicao_de_ensino", "info", "contato_principal", "contato_candidaturas",
		"endereco", "website", "redes_sociais", "criado_em", "atualizado_em", "_links"})
public class OrgPrivateProfile extends OrgPublicProfile implements OrgPrivateProfileProjection {

	@Schema(description = "CNPJ válido da organização", requiredMode = Schema.RequiredMode.REQUIRED, example="18009962000177")
	@CNPJ
	private String cnpj;

	@JsonProperty("credenciais")
	private UserCredentialsProjectionResponse userCredentials;

	@JsonProperty(value = "criado_em", access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;

	@JsonProperty(value = "atualizado_em", access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedAt;

	@Override
	public @CNPJ String getCnpj() {
		return cnpj;
	}

	public void setCnpj(@CNPJ String cnpj) {
		this.cnpj = cnpj;
	}

	//@Override
	public UserCredentialsProjectionResponse getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(UserCredentialsProjectionResponse userCredentials) {
		this.userCredentials = userCredentials;
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


	@JsonPropertyOrder({"email", "senha"})
	public static class UserCredentialsProjectionResponse implements UserCredentialsProjection {

		@Schema(description = "Identificador para autenticação", requiredMode = Schema.RequiredMode.REQUIRED, example = "nobanks")
		@Email
		private String email;

		@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
		private String senha;

		@JsonIgnore
		private String pwd;

		@Override
		public @Email String getEmail() {
			return email;
		}

		public void setEmail(@Email String email) {
			this.email = email;
		}

		@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		@Override
		@JsonIgnore
		public String getPwd() {
			return pwd;
		}

		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
	}

}
