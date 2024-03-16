package com.github.projetoifsc.estagios.app.dto.shared;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.validation.annotation.Validated;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;



/**
 * Simple POJO for inserting contact information
 * 
 * Minimal Required fields: email, phone
 * 
 */

@JsonPropertyOrder(value = "email, telefone")
@Validated
@Schema(name="Contato", description = "Informações de contato")
public class Contato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "Email válido", example = "rh@nobanks.com", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank
	@Email
	private String email;

	@JsonProperty(value = "telefone")
	@Schema(description = "Telefone válido", example = "48 3555-5500", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank
	private String phone;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
