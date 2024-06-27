package com.github.projetoifsc.estagios.app.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.core.models.projections.JobPrivateDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.OrgSummaryProjection;
import jakarta.validation.Valid;

@JsonPropertyOrder({"id", "titulo", "criado_por", "descricao", "requisitos",
	"areas", "carga_horaria_semanal", "remuneracao", "periodo", "nivel", "formato",
		"duracao_meses", "data_inicio", "data_fim", "imagem_url", "endereco", "contato",
		"destinatarios",
		"criado_em", "atualizado_em", "_links"
})
public class JobPrivateDetails extends JobPublicDetails implements JobPrivateDetailsProjection {

	@JsonProperty(value = "destinatarios")
	private List<@Valid OrgSummaryProjection> exclusiveReceivers;

	@Override
	public List<OrgSummaryProjection> getExclusiveReceivers() {
		return exclusiveReceivers;
	}

	public void setExclusiveReceivers(List<@Valid OrgSummaryProjection> exclusiveReceivers) {
		this.exclusiveReceivers = exclusiveReceivers;
	}

	@Override
	@JsonProperty("contato")
	public Contact getContact() {
		return super.getContact();
	}

	@JsonIgnore
	public ContactWithoutType getContactWithoutType() {
		return null;
	}

	@Override
	@JsonProperty("endereco")
	public Address getAddress() {
		return super.getAddress();
	}

	@JsonIgnore
	public AddressWithoutType getAddressWithoutType() {
		return null;
	}

}
