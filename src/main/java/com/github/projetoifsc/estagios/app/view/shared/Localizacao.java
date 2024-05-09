package com.github.projetoifsc.estagios.app.view.shared;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Validated

@Schema(name="Endereço", description = "Informações de endereço")
public class Localizacao implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "rua", required = true)
	@Schema(example = "Rua Hercílio Luz, nº 570, Centro", description = "Endereço principal das vagas que serão oferecidas no sistema. Caso necessário, no momento de cadastro de novas vagas, é possível definir endereços específicos", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank
	private String line;

	@JsonProperty(value = "cidade", required = true)
	@Schema(description = "Cidade", example = "Florianópolis", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank
	private String city;

	@JsonProperty(value = "estado")
	@Schema(description = "Nome completo do estado", example = "Santa Catarina", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank
	private String state;

	@JsonProperty(value = "pais")
	@Schema(description = "País", example = "Brasil", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank
	private String country;

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
