package com.github.projetoifsc.estagios.app.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import com.github.projetoifsc.estagios.core.models.IJobEntryData;
import com.github.projetoifsc.estagios.core.models.IOrg;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonPropertyOrder({"titulo", "descricao", "requisitos",
        "areas_ids", "carga_horaria_semanal", "remuneracao", "periodo", "nivel",
        "formato", "duracao_meses", "data_inicio", "data_fim", "imagem_url", "endereco_id",
        "contato_id", "destinatarios_ids"
})
public class JobEntryData implements IJobEntryData {

    public JobEntryData() {
        System.out.println("Entrei aqui!!!");
    }

    // TODO: Tirar esse mapper daqui!
    private Mapper mapper = new Mapper();

    @JsonIgnore
    private String id;

    @JsonIgnore
    private IOrg owner;

    @JsonProperty("titulo")
    @NotBlank
    private String titulo;

    @JsonProperty(value = "descricao", required = true)
    @NotBlank
    private String descricao;

    @JsonProperty(value = "areas_ids")
    private List<String> areasIds = new ArrayList<>();

    @JsonIgnore
    private List<String> requisitosList = new ArrayList<>();

    @JsonIgnore
    private String requisitos;


    @JsonProperty(value = "carga_horaria_semanal", required = true)
    @Schema(description = "Carga horária semanal, em horas", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Min(1)
    private int cargaHorariaSemanal;

    @JsonProperty(value = "remuneracao", required = true)
    @NotNull
    @Min(1)
    private float remuneracao;

    @JsonProperty(value = "periodo")
//    @Schema(example="matutino", allowableValues = {"matutino", "vespertino", "noturno"}, description = "1-Matutino, 2-Vespertino, 3-Noturno", requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(1)
    private EPeriod period;

    @JsonProperty(value = "nivel")
//    @Schema(example="medio", allowableValues = {"1", "2", "3", "4", "5"}, description = "1-Fundamental, 2-Médio, 3-Técnico, 4-Graduação, 5-Pós", requiredMode = Schema.RequiredMode.REQUIRED)
    private ELevel level;

    @JsonProperty(value = "formato")
//    @Schema(example="remoto", description = "1-Presencial, 2-Remoto, 3-Híbrido", allowableValues = {"1", "2", "3"}, requiredMode = Schema.RequiredMode.REQUIRED)
    private EFormat format;

    @JsonProperty("duracao_meses")
    @Min(0)
    private int duracaoMeses;

    @JsonProperty("data_inicio")
    @JsonFormat(pattern="yyyy-MM-dd")
    @Schema(example="2024-01-22", description = "Data de início do estágio", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
    private LocalDate dataInicio;

    @JsonProperty("data_fim")
    @JsonFormat(pattern="yyyy-MM-dd")
    @Schema(example="2024-06-30", description = "Data do final do estágio", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
    private LocalDate dataFinal;

    @URL
    @JsonProperty("imagem_url")
    @Schema(example="http://imagens.com/img01.png", description = "URL da imagem", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
    private String imagem;

    @JsonProperty(value = "contato_id")
    @Schema(description = "Id do Contato para candidatura", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String contactId;

    @JsonProperty(value = "endereco_id")
    @Schema(description = "Id do endereço da vaga.", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String addressId;

    @JsonProperty(value = "destinatarios_ids")
    @Schema(example="[\"5\", \"6\"]", description = "Ids das Instituições de Ensino para as quais a vaga se destina. Se deixado em branco, a vaga será disponibilizada para todas as instituições de ensino com acesso ao sistema", nullable = true, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<@NotNull String> receiversIds = new ArrayList<>();


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public IOrg getOwner() {
        return owner;
    }

    @Override
    public void setOwner(IOrg owner) {
        this.owner = owner;
    }

    public @NotBlank String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    public @NotBlank String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank String descricao) {
        this.descricao = descricao;
    }

    @Override
    public List<String> getAreasIds() {
        return areasIds;
    }

    public void setAreasIds(List<String> areasIds) {
        this.areasIds = areasIds;
    }


    @JsonProperty("requisitos")
    public List<String> getRequisitosList() {
        return requisitosList;
    }

    public void setRequisitosList(List<String> requisitosList) {
        this.requisitosList = requisitosList;
        this.requisitos = String.join(";", this.requisitosList);
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
        this.requisitosList = Arrays.asList(this.requisitos.split(";"));
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

    public @Min(1) EPeriod getPeriod() {
        return period;
    }

    public ELevel getLevel() {
        return level;
    }

    public EFormat getFormat() {
        return format;
    }

    @Override
    @JsonIgnore
    public short getPeriodId() {
        return (short) period.getId();
    }

    @Override
    @JsonIgnore
    public short getLevelId() {
        return (short) level.getId();
    }

    @Override
    @JsonIgnore
    public short getFormatId() {
        return (short) format.getId();
    }

    public void setPeriod(@Min(1) EPeriod period) {
        this.period = period;
    }

    public void setLevel(ELevel level) {
        this.level = level;
    }

    public void setFormat(EFormat format) {
        this.format = format;
    }

    @Min(0)
    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setDuracaoMeses(@Min(0) int duracaoMeses) {
        this.duracaoMeses = duracaoMeses;
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

    @Override
    public @NotBlank String getContactId() {
        return contactId;
    }

    public void setContactId(@NotBlank String contactId) {
        this.contactId = contactId;
    }

    @Override
    public @NotBlank String getAddressId() {
        return addressId;
    }

    public void setAddressId(@NotBlank String addressId) {
        this.addressId = addressId;
    }

    @Override
    public List<@NotNull String> getReceiversIds() {
        return receiversIds;
    }

    @Override
    public void setReceiversIds(List<@NotNull String> receiversIds) {
        this.receiversIds = receiversIds;
    }


}
