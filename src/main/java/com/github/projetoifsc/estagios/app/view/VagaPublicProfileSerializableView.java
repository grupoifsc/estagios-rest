package com.github.projetoifsc.estagios.app.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.app.view.shared.Contato;
import com.github.projetoifsc.estagios.app.view.shared.Localizacao;
import com.github.projetoifsc.estagios.app.utils.hateoas.VagaHateoasHelper;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@JsonPropertyOrder(value = {"id", "titulo", "entidade", "descricao",
        "linksExternos", "requisitos", "periodos", "cargaHoraria", "remuneracao",
        "niveis", "areas", "inicio", "final", "contato",
        "endereco", "criadoEm", "modificadoEm", "links"})
public class VagaPublicProfileSerializableView extends VagaSerializableView {

    @JsonProperty(value = "descricao", required = true)
    @Schema(example="Vaga para desenhista etc etc et etc etc etc", description = "Aceita HTML", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String description;

    @JsonProperty(value = "requisitos")
    @Schema(example="[\"Conhecimento em Adobe Photoshop\", \"Técnicas básicas de desenho digital\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
    private List<@NotBlank String> requirements;

    @JsonProperty(value = "periodos", required = true)
    @Schema(example="[\"matutino\",\"vespertino\"]", allowableValues = "matutino,vespertino,noturno", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty
    private List<@NotBlank String> periods;

    @JsonProperty(value = "cargaHoraria", required = true)
    @Schema(example="20",  description = "Carga horária semanal, em horas", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Min(1)
    private long workloadInHours;

    @JsonProperty(value = "remuneracao", required = true)
    @Schema(example="900", description = "Remuneração da vaga", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Min(1)
    private long payment;

    @JsonProperty(value = "niveis", required = true)
    @Schema(example="[\"medio\",\"tecnico\"]", allowableValues = "medio, tecnico, superior, pos", description = "Níveis de ensino que os/as candidatos/as à vaga podem estar cursando", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<@NotBlank String> levels;

    @JsonProperty("areas")
    @Schema(example="[\"Design\",\"Artes Visuais\"]", description = "Áreas de estudo dos/as candidatos/as à vaga", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
    private List<@NotBlank String> areas;

    @JsonProperty("inicio")
    @JsonFormat(pattern="yyyy-MM-dd")
    @Schema(example="2024-01-22", description = "Data de início do estágio", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
    private LocalDate startsAt;

    @JsonProperty("final")
    @JsonFormat(pattern="yyyy-MM-dd")
    @Schema(example="2024-06-30", description = "Data do final do estágio", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
    private LocalDate endsAt;

    @JsonProperty("contato")
    @Schema(description = "Contato para candidatura. Se deixado em branco, será considerado e exibido o contato definido no perfil da instituição ou empresa", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
    private Contato contact;

    @JsonProperty("endereco")
    @Schema(description = "Endereço da vaga. Se deixado em branco, será considerado e exibido o contato definido no perfil da instituição ou empresa", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
    private Localizacao address;

    @JsonProperty("linksExternos")
    @Schema(example="[\"linkedin.com/minhaEmpresa/vaga012\"]", description = "Links externos que se relacionem com a vaga", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
    private List<@NotBlank String> externalLinks;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY )
    @JsonProperty("criadoEm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("modificadoEm")
    private LocalDateTime updatedAt;

    public VagaPublicProfileSerializableView() {}

    public VagaPublicProfileSerializableView(String key, OrgBasicView
            owner, String title, String description, List<@NotBlank String> requirements, List<@NotBlank String> periods, long workloadInHours, long payment, List<@NotBlank String> levels, List<@NotBlank String> areas, LocalDate startsAt, LocalDate endsAt, Contato contact, Localizacao address, List<@NotBlank String> externalLinks, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(key, owner, title);
        this.description = description;
        this.requirements = requirements;
        this.periods = periods;
        this.workloadInHours = workloadInHours;
        this.payment = payment;
        this.levels = levels;
        this.areas = areas;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.contact = contact;
        this.address = address;
        this.externalLinks = externalLinks;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    public List<String> getPeriods() {
        return periods;
    }

    public void setPeriods(List<String> periods) {
        this.periods = periods;
    }

    public long getWorkloadInHours() {
        return workloadInHours;
    }

    public void setWorkloadInHours(long workloadInHours) {
        this.workloadInHours = workloadInHours;
    }

    public long getPayment() {
        return payment;
    }

    public void setPayment(long payment) {
        this.payment = payment;
    }

    public List<String> getLevels() {
        return levels;
    }

    public void setLevels(List<String> levels) {
        this.levels = levels;
    }

    public List<String> getAreas() {
        return areas;
    }

    public void setAreas(List<String> areas) {
        this.areas = areas;
    }

    public LocalDate getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(LocalDate startsAt) {
        this.startsAt = startsAt;
    }

    public LocalDate getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(LocalDate endsAt) {
        this.endsAt = endsAt;
    }

    public Contato getContact() {
        return contact;
    }

    public void setContact(Contato contact) {
        this.contact = contact;
    }

    public Localizacao getAddress() {
        return address;
    }

    public void setAddress(Localizacao address) {
        this.address = address;
    }

    public List<String> getExternalLinks() {
        return externalLinks;
    }

    public void setExternalLinks(List<String> externalLinks) {
        this.externalLinks = externalLinks;
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
        VagaHateoasHelper.addPublicProfileLinks(this);
    }

}
