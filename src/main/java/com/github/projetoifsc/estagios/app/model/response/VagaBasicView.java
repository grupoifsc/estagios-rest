package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(name="Vaga")
@Validated
@JsonPropertyOrder(value = {"id", "criado_por", "_links"})
public class VagaBasicView extends View implements IJob {

	private final Mapper mapper = new Mapper();

	@JsonProperty("id")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "1")
	private String id;

	@JsonProperty("criado_por")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	@NotNull
	@Valid
	protected OrgBasicInfoViewWithoutLinks owner;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public IOrganization getOwner() {
		return owner;
	}

	@Override
	public void setOwner(IOrganization owner) {
		this.owner = mapper.map(owner, OrgBasicInfoViewWithoutLinks.class);
	}


	@Override
	public void addHypermediaLinks() {
		//
	}

}
