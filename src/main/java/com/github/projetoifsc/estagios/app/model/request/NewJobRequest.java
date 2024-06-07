package com.github.projetoifsc.estagios.app.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.core.models.IJobEntryData;
import com.github.projetoifsc.estagios.core.models.IOrganization;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder(value = {"id", "titulo", "criador", "descricao",
        "requisitos", "periodo", "carga_horaria", "remuneracao",
        "nivel", "formato", "areas_ids", "imagem", "destinatarios_ids", "duracao_meses",
        "data_inicio", "data_final", "contato", "endereco",
        "criado_em", "modificado_em", "_links"})
public class NewJobRequest implements IJobEntryData {

    @JsonIgnore
    private IOrganization owner;

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

    // TODO: Pensar em contato!
    @JsonProperty("contato_id")
    @Schema(description = "Id do Contato para candidatura", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String contactId;

    @JsonProperty("endereco_id")
    @Schema(description = "Id do endereço da vaga.", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String addressId;


    // TODO: é pra ser somente o número da área!
    @JsonProperty("areas_ids")
    @Schema(example="[\"1\",\"2\"]", description = "Ids das Áreas de estudo dos/as candidatos/as à vaga", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
    private List<String> areasIds = new ArrayList<>();


    @JsonIgnore
    private String id = null;


    @JsonProperty("titulo")
    @Schema(example="Vaga de desenhista Junior")
    @NotBlank
    private String titulo;


    @JsonProperty(value = "requisitos")
    @Schema(example="Conhecimento em Adobe Photoshop, Técnicas básicas de desenho digital", description = "Separe os requisitos com víruglas", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
    private String requisitos;


    @JsonProperty(value = "carga_horaria", required = true)
    @Schema(example="20",  description = "Carga horária semanal, em horas", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Min(1)
    private int cargaHorariaSemanal;


    @JsonProperty(value = "remuneracao", required = true)
    @Schema(example="900", description = "Remuneração da vaga", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Min(1)
    private float remuneracao;


    @JsonProperty("destinatarios_ids")
    @Schema(example="[\"5\", \"6\"]", description = "Ids das Instituições de Ensino para as quais a vaga se destina. Se deixado em branco, a vaga será disponibilizada para todas as instituições de ensino com acesso ao sistema", nullable = true, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<@NotNull String> receiversIds = new ArrayList<>();



    public @NotBlank String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank String descricao) {
        this.descricao = descricao;
    }

    @Min(1)
    public short getPeriodId() {
        return periodId;
    }

    public void setPeriodId(@Min(1) short periodId) {
        this.periodId = periodId;
    }

    public short getLevelId() {
        return levelId;
    }

    public void setLevelId(short levelId) {
        this.levelId = levelId;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public @URL String getImagem() {
        return imagem;
    }

    public void setImagem(@URL String imagem) {
        this.imagem = imagem;
    }

    @Min(0)
    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setDuracaoMeses(@Min(0) int duracaoMeses) {
        this.duracaoMeses = duracaoMeses;
    }

    public short getFormatId() {
        return formatId;
    }

    public void setFormatId(short formatId) {
        this.formatId = formatId;
    }

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
        this.owner = owner;
    }

    public @NotBlank String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    @NotNull
    @Min(1)
    public int getCargaHorariaSemanal() {
        return cargaHorariaSemanal;
    }

    public void setCargaHorariaSemanal(@NotNull @Min(1) int cargaHorariaSemanal) {
        this.cargaHorariaSemanal = cargaHorariaSemanal;
    }

    @NotNull
    @Min(1)
    public float getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(@NotNull @Min(1) float remuneracao) {
        this.remuneracao = remuneracao;
    }

    @Override
    public List<@NotNull String> getReceiversIds() {
        return receiversIds;
    }

    @Override
    public void setReceiversIds(List<@NotNull String> receiversIds) {
        this.receiversIds = receiversIds;
    }

    @Override
    public List<String> getAreasIds() {
        return areasIds;
    }

    public void setAreasIds(List<String> areasIds) {
        this.areasIds = areasIds;
    }

    @Override
    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    @Override
    public @NotBlank String getAddressId() {
        return addressId;
    }

    public void setAddressId(@NotBlank String addressId) {
        this.addressId = addressId;
    }

}
