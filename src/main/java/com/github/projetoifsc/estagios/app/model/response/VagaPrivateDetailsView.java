package com.github.projetoifsc.estagios.app.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.app.model.interfaces.JobPrivateDetailsProjection;
import com.github.projetoifsc.estagios.app.model.interfaces.OrgBasicInfoProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.hateoas.Links;


@JsonPropertyOrder(value = {"id", "titulo", "criador", "descricao",
		"requisitos", "periodo", "carga_horaria", "remuneracao",
		"nivel", "formato", "areas", "imagem", "destinatarios", "duracao_meses",
		"data_inicio", "data_final", "contato", "endereco",
		"criado_em", "modificado_em", "_links"})
public class VagaPrivateDetailsView extends VagaPublicDetailsView implements JobPrivateDetailsProjection {

	@JsonIgnore
	private OrgBasicInfoProjection owner;

	@JsonProperty(value = "destinatarios")
	private List<@Valid OrgBasicInfoProjection> exclusiveReceivers;

	@Override
	public List<OrgBasicInfoProjection> getExclusiveReceivers() {
		return exclusiveReceivers;
	}

	public void setExclusiveReceivers(List<@Valid OrgBasicInfoProjection> exclusiveReceivers) {
		this.exclusiveReceivers = exclusiveReceivers;
	}


	@Override
	@JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "_links")
	@Schema(hidden = true)
	public Links getLinks() {
		return super.getLinks();
	}


}
