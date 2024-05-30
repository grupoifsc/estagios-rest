package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.app.model.interfaces.AddressProjection;
import com.github.projetoifsc.estagios.app.model.interfaces.ContactProjection;
import com.github.projetoifsc.estagios.app.model.interfaces.JobPublicDetailsProjection;
import com.github.projetoifsc.estagios.core.IArea;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.hateoas.Links;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder(value = {"id", "titulo", "criado_por", "descricao",
        "requisitos", "periodo", "carga_horaria", "remuneracao",
        "nivel", "formato", "areas", "imagem", "duracao_meses",
        "data_inicio", "data_final", "contato", "endereco",
        "criado_em", "modificado_em", "_links"})
public class VagaPublicDetailsView extends VagaPublicSummaryView implements JobPublicDetailsProjection {

    @JsonProperty(value = "descricao", required = true)
    @Schema(example="Vaga para desenhista etc etc et etc etc etc", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String descricao;

    @JsonProperty(value = "periodo", required = true)
    @Schema(example="1", allowableValues = {"1", "2", "3"}, description = "1-Matutino, 2-Vespertino, 3-Noturno", requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(1)
    private short periodId;

    @JsonProperty(value = "nivel", required = true)
    @Schema(example="1", allowableValues = {"1", "2", "3", "4", "5"}, description = "1-Fundamental, 2-Médio, 3-Técnico, 4-Graduação, 5-Pós", requiredMode = Schema.RequiredMode.REQUIRED)
    private short levelId;

    @JsonProperty("data_inicio")
    @JsonFormat(pattern="yyyy-MM-dd")
    @Schema(example="2024-01-22", description = "Data de início do estágio", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
    private LocalDate dataInicio;

    @JsonProperty("data_final")
    @JsonFormat(pattern="yyyy-MM-dd")
    @Schema(example="2024-06-30", description = "Data do final do estágio", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
    private LocalDate dataFinal;

    @URL
    @Schema(example="http://imagens.com/img01.png", description = "Link para imagem", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
    private String imagem;

    @JsonProperty("duracao_meses")
    @Min(0)
    @Schema(example="6", description = "Duração em meses da vaga de estágio", requiredMode = Schema.RequiredMode.REQUIRED)
    private int duracaoMeses;

    @JsonProperty("formato")
    @Schema(example="1", description = "1-Presencial, 2-Remoto, 3-Híbrido", allowableValues = {"1", "2", "3"}, requiredMode = Schema.RequiredMode.REQUIRED)
    private short formatId;

    @JsonProperty("contato")
    @Schema(description = "Contato para candidatura. Se deixado em branco, será considerado e exibido o contato definido no perfil da instituição ou empresa", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
    private ContactPublicView contact;

    @JsonProperty("endereco")
    @Schema(description = "Endereço da vaga. Se deixado em branco, será considerado e exibido o contato definido no perfil da instituição ou empresa", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
    private AdressPublicView address;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY )
    @JsonProperty("criado_em")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonProperty("areas")
    @Schema(example="[\"Design\",\"Artes Visuais\"]", description = "Áreas de estudo dos/as candidatos/as à vaga", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
    @Valid
    private List<IArea> areas = new ArrayList<>();

    @Override
    public List<IArea> getAreas() {
        return areas;
    }

    public void setAreas(List<IArea> areas) {
        this.areas = areas;
    }

    @Override
    public @NotBlank String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank String descricao) {
        this.descricao = descricao;
    }


    @Override
    @Min(1)
    public short getPeriodId() {
        return periodId;
    }

    public void setPeriodId(@Min(1) short periodId) {
        this.periodId = periodId;
    }

    @Override
    public short getLevelId() {
        return levelId;
    }

    public void setLevelId(short levelId) {
        this.levelId = levelId;
    }

    @Override
    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    @Override
    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    @Override
    public @URL String getImagem() {
        return imagem;
    }

    public void setImagem(@URL String imagem) {
        this.imagem = imagem;
    }

    @Override
    @Min(0)
    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setDuracaoMeses(@Min(0) int duracaoMeses) {
        this.duracaoMeses = duracaoMeses;
    }

    @Override
    public short getFormatId() {
        return formatId;
    }

    public void setFormatId(short formatId) {
        this.formatId = formatId;
    }

    @Override
    public ContactProjection getContact() {
        return contact;
    }

    public void setContact(ContactPublicView contact) {
        this.contact = contact;
    }

    @Override
    public AddressProjection getAddress() {
        return address;
    }

    public void setAddress(AdressPublicView address) {
        this.address = address;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "_links")
    @Schema(hidden = true)
    public Links getLinks() {
        return super.getLinks();
    }

}
