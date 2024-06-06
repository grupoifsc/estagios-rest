package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.core.IOrganization;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


// TODO DB: Considerar a possibilidade de usar Views ao invés de projections muito elaboradas

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "jobs")
class JobEntity implements IJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    OrganizationEntity owner;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="areas_jobs",
        joinColumns = @JoinColumn(name="job_id"),
        inverseJoinColumns = @JoinColumn(name="area_id")
    )
    List<AreaEntity> areas = new ArrayList<>();

    // Ver outra solução para esta relação:
    // https://www.baeldung.com/jpa-many-to-many
    // Pode ser útil para ver as vagas aceitas!
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ies_exclusive_jobs",
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "org_id")
    )
    List<OrganizationEntity> exclusiveReceivers;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    AddressEntity address;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    ContactEntity contact;


    @OneToMany(mappedBy = "job")
    Set<ModeratedJobsEntity> moderatedJobs;


    // Solução: https://stackoverflow.com/questions/27930449/jpa-many-to-one-relation-need-to-save-only-id
    // Funciona, pois acusa falha de restrição de FK quando insere um valor que não existe na tabela relacionada
    // Não funciona caso, na mesma transação, deseje salvar E selecionar a tabela relacionada
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "period_id", insertable = false, updatable = false)
    PeriodEntity periodo;

    @Column(name = "period_id")
    short periodId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_level_id", insertable = false, updatable = false)
    LevelEntity level;

    @Column(name = "study_level_id")
    short levelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "format_id", insertable = false,updatable = false)
    FormatEntity formatEntity;

    @Column(name = "format_id")
    short formatId;

    String titulo;

    String descricao;

    String imagem;

    String requisitos;

    @Column(name = "data_inicio")
    LocalDate dataInicio;

    @Column(name = "data_final")
    LocalDate dataFinal;

    @Column(name = "duracao_meses")
    int duracaoMeses;

    @Column(nullable = false)
    float remuneracao;

    @Column(name = "carga_horaria_semanal")
    int cargaHorariaSemanal;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    LocalDateTime updatedAt;


    @Override
    public String getId() {
        return String.valueOf(id);
    }

    public void setId(String id) {
        this.id = Long.parseLong(id);
    }

    @Override
    public OrganizationEntity getOwner() {
        return owner;
    }

    @Override
    public void setOwner(IOrganization owner) {
        this.owner = (OrganizationEntity) owner;
    }

    public List<AreaEntity> getAreas() {
        return areas;
    }

    public void setAreas(List<AreaEntity> areas) {
        this.areas = areas;
    }

    public List<OrganizationEntity> getExclusiveReceivers() {
        return exclusiveReceivers;
    }

    public void setExclusiveReceivers(List<OrganizationEntity> exclusiveReceivers) {
        this.exclusiveReceivers = exclusiveReceivers;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity addressEntity) {
        this.address = addressEntity;
    }

    public ContactEntity getContact() {
        return contact;
    }

    public void setContact(ContactEntity contactEntity) {
        this.contact = contactEntity;
    }

    public PeriodEntity getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodEntity periodo) {
        this.periodo = periodo;
    }

    public short getPeriodId() {
        return periodId;
    }

    public void setPeriodId(short periodId) {
        this.periodId = periodId;
    }

    public LevelEntity getLevel() {
        return level;
    }

    public void setLevel(LevelEntity level) {
        this.level = level;
    }

    public short getLevelId() {
        return levelId;
    }

    public void setLevelId(short levelId) {
        this.levelId = levelId;
    }

    public FormatEntity getFormat() {
        return formatEntity;
    }

    public void setFormat(FormatEntity formatEntity) {
        this.formatEntity = formatEntity;
    }

    public short getFormatId() {
        return formatId;
    }

    public void setFormatId(short formatId) {
        this.formatId = formatId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
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

    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setDuracaoMeses(int duracaoMeses) {
        this.duracaoMeses = duracaoMeses;
    }

    public float getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(float remuneracao) {
        this.remuneracao = remuneracao;
    }

    public int getCargaHorariaSemanal() {
        return cargaHorariaSemanal;
    }

    public void setCargaHorariaSemanal(int cargaHorariaSemanal) {
        this.cargaHorariaSemanal = cargaHorariaSemanal;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setOwner(OrganizationEntity owner) {
        this.owner = owner;
    }

    public Set<ModeratedJobsEntity> getModeratedJobs() {
        return moderatedJobs;
    }

    public void setModeratedJobs(Set<ModeratedJobsEntity> moderatedJobs) {
        this.moderatedJobs = moderatedJobs;
    }

    public FormatEntity getFormatEntity() {
        return formatEntity;
    }

    public void setFormatEntity(FormatEntity formatEntity) {
        this.formatEntity = formatEntity;
    }

}
