package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.app.model.request.EFormat;
import com.github.projetoifsc.estagios.app.model.request.ELevel;
import com.github.projetoifsc.estagios.app.model.request.EPeriod;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import com.github.projetoifsc.estagios.core.models.*;
import com.github.projetoifsc.estagios.core.models.projections.JobPublicDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.ModerationDetailsProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonPropertyOrder({"id", "titulo", "criado_por", "descricao", "requisitos",
        "areas", "carga_horaria_semanal", "remuneracao", "periodo", "nivel", "formato",
        "duracao_meses", "data_inicio", "data_fim", "imagem_url", "endereco", "contato",
        "criado_em", "atualizado_em", "moderacao", "_links"
})
public class JobPublicDetails implements JobPublicDetailsProjection {

    private Mapper mapper = new Mapper();

    private String id;

    @JsonProperty("titulo")
    @NotBlank
    private String titulo;

    @JsonProperty(value = "criado_por")
    private OrgSummary owner;

    @JsonProperty(value = "descricao")
    @NotBlank
    private String descricao;

    @JsonProperty(value = "areas")
    private List<IArea> areas = new ArrayList<>();

    @JsonProperty(value = "requisitos")
    private List<String> requisitos;

    @JsonProperty(value = "carga_horaria_semanal")
    @Schema(description = "Carga horária semanal, em horas", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Min(1)
    private int cargaHorariaSemanal;

    @JsonProperty(value = "remuneracao")
    @NotNull
    @Min(1)
    private float remuneracao;

    @JsonIgnore
    private IPeriod period;

    @JsonIgnore
    private ILevel level;

    @JsonIgnore
    private IFormat format;

    @JsonProperty("duracao_meses")
    @Min(0)
    private int duracaoMeses;

    @JsonProperty("data_inicio")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataInicio;

    @JsonProperty("data_fim")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataFinal;

    @URL
    @JsonProperty(value = "imagem_url")
    private String imagem;

    @JsonIgnore
    private Contact contact;

    @JsonIgnore
    private Address address;

    @JsonProperty("criado_em")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonProperty("atualizado_em")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @JsonProperty("moderacao")
    private ModerationDetail moderationDetail;


    @Override
    public String getId() {
        return id;
    }

    //@Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public @NotBlank String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    @Override
    public OrgSummary getOwner() {
        return owner;
    }

    //@Override
    @JsonIgnore
    public void setOwner(IOrg user) {
        this.owner = mapper.map(user, OrgSummary.class);
    }

    public void setOwner(OrgSummary owner) {
        this.owner = owner;
    }

    @Override
    public @NotBlank String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank String descricao) {
        this.descricao = descricao;
    }

    @Override
    public List<IArea> getAreas() {
        return areas;
    }

    public void setAreas(List<IArea> areas) {
        this.areas = areas;
    }

    @Override
    @JsonIgnore
    public String getRequisitos() {
        return String.join(";", requisitos);
    }

    @JsonProperty("requisitos")
    public List<String> getRequisitosList() {
        return requisitos;
    }

    public void setRequisitosList(List<String> requisitos) {
        this.requisitos = requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = Arrays.asList(requisitos.split(";"));
    }

    @Override
    @NotNull
    @Min(1)
    public int getCargaHorariaSemanal() {
        return cargaHorariaSemanal;
    }

    public void setCargaHorariaSemanal(@NotNull @Min(1) int cargaHorariaSemanal) {
        this.cargaHorariaSemanal = cargaHorariaSemanal;
    }

    @Override
    @NotNull
    @Min(1)
    public float getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(@NotNull @Min(1) float remuneracao) {
        this.remuneracao = remuneracao;
    }

    @Override
    public IPeriod getPeriod() {
        return period;
    }

    public void setPeriod(IPeriod period) {
        this.period = period;
    }

    @JsonProperty("periodo")
    public EPeriod getPeriodName() {
        return EPeriod.findById(this.period.getId());
    }

    @Override
    public ILevel getLevel() {
        return level;
    }

    public void setLevel(ILevel level) {
        this.level = level;
    }

    @JsonProperty("nivel")
    public ELevel getLevelName() {
        return ELevel.findById(this.level.getId());
    }


    @Override
    public IFormat getFormat() {
        return format;
    }

    public void setFormat(IFormat format) {
        this.format = format;
    }

    @JsonProperty("formato")
    public EFormat getFormatName() {
        return EFormat.findById(this.format.getId());
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
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @JsonProperty("contato")
    public ContactWithoutType getContactWithoutType() {
        return contact != null
            ? mapper.map(contact, ContactWithoutType.class)
            : null;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonProperty("endereco")
    public AddressWithoutType getAddressWithoutType() {
        return address != null
            ? mapper.map(address, AddressWithoutType.class)
            : null;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public ModerationDetail getModerationDetail() {
        return moderationDetail;
    }

    @Override
    public void setModerationDetail(ModerationDetailsProjection moderationDetail) {
        var mapped = mapper.map(moderationDetail, ModerationDetail.class);
        this.moderationDetail = mapped;
    }

//    public void setModerationDetail(ModerationDetail moderationDetail) {
//        this.moderationDetail = moderationDetail;
//    }

}
