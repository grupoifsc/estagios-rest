package com.github.projetoifsc.estagios.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.app.dto.shared.Contato;
import com.github.projetoifsc.estagios.app.dto.shared.Localizacao;
import com.github.projetoifsc.estagios.app.utils.hateoas.VagaHateoasHelper;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonPropertyOrder(value = {"id", "titulo", "entidade", "idExternoAutor",
		"destinatarios", "descricao", "linksExternos", "requisitos", "periodos",
		"cargaHoraria", "remuneracao", "niveis", "areas", "inicio",
		"final", "contato", "endereco", "expiraEmDias",
		"renovaEmDias", "criadoEm", "modificadoEm", "links"})
public class VagaPrivateProfileDTO extends VagaPublicProfileDTO {

	@JsonProperty("idExternoAutor")
	@Schema(example="InternalUser0123", description = "Id externo do criador da vaga. Útil para organizações em que o sistema será acessado por vários usuários internos e é preciso manter autorização de acesso às ofertas de vaga. Caso este valor seja fornecido, a vaga só poderá ser alterada ou excluída pelo mesmo usuário que a criou.", nullable = true, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String creatorInternalId;

	@JsonProperty("destinatarios")
	@Schema(example="[\"5\", \"6\"]", description = "Ids das Instituições de Ensino para as quais a vaga se destina. Se deixado em branco, a vaga será disponibilizada para todas as instituições de ensino com acesso ao sistema", nullable = true, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<@NotNull String> ies;

	@JsonProperty("expiraEmDias")
	@Schema(example="50", description = "Define por quantos dias a vaga ficará ativa no sistema. Se o valor não for definido, a vaga ficará disponível por 90 dias", requiredMode = Schema.RequiredMode.NOT_REQUIRED, minimum = "0")
	@NotNull
	private Integer expiresInDays = 90;

	@JsonProperty("renovaEmDias")
	@Schema(example="180", description = "Caso esta oferta de vaga de estágio seja recorrente na organização, é possível programar o intervalo de dias em que a vaga deve ser republicada no sistema" )
	@Min(0)
	private int renovateInDays;

	public VagaPrivateProfileDTO() {}

	public VagaPrivateProfileDTO(String key, UserDTO owner, String title, String description, List<@NotBlank String> requirements, List<@NotBlank String> periods, long workloadInHours, long payment, List<@NotBlank String> levels, List<@NotBlank String> areas, LocalDate startsAt, LocalDate endsAt, Contato contact, Localizacao address, List<@NotBlank String> externalLinks, LocalDateTime createdAt, LocalDateTime updatedAt, Integer expiresInDays, List<@NotNull String> ies, int renovateInDays, String creatorInternalId) {
		super(key, owner, title, description, requirements, periods, workloadInHours, payment, levels, areas, startsAt, endsAt, contact, address, externalLinks, createdAt, updatedAt);
		this.expiresInDays = expiresInDays;
		this.ies = ies;
		this.renovateInDays = renovateInDays;
		this.creatorInternalId = creatorInternalId;
	}

	public Integer getExpiresInDays() {
		return expiresInDays;
	}

	public void setExpiresInDays(Integer expiresInDays) {
		this.expiresInDays = expiresInDays;
	}

	public List<String> getIes() {
		return ies;
	}

	public void setIes(List<String> ies) {
		this.ies = ies;
	}

	public int getRenovateInDays() {
		return renovateInDays;
	}

	public void setRenovateInDays(int renovateInDays) {
		this.renovateInDays = renovateInDays;
	}

	public String getCreatorInternalId() {
		return creatorInternalId;
	}

	public void setCreatorInternalId(String creatorInternalId) {
		this.creatorInternalId = creatorInternalId;
	}

	@Override
	public void addHypermediaLinks() {
		VagaHateoasHelper.addPrivateProfileLinks(this);
	}

}
