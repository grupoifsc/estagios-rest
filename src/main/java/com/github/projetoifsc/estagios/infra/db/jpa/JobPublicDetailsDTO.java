package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.models.*;
import com.github.projetoifsc.estagios.core.models.projections.AddressDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.ContactDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.JobPublicDetailsProjection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

class JobPublicDetailsDTO implements JobPublicDetailsProjection {

    private String id;
    private OrgSummaryProjectionDTO owner;
    private List<IArea> areas;
    private IPeriod period;
    private ILevel level;
    private IFormat format;
    private String titulo;
    private String descricao;
    private String imagem;
    private String requisitos;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private int duracaoMeses;
    private float remuneracao;
    private int cargaHorariaSemanal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ContactDetailsProjection contact;
    private AddressDetailsProjection address;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public OrgSummaryProjectionDTO getOwner() {
        return owner;
    }

    @Override
    public void setOwner(IOrg owner) {
        this.owner = new OrgSummaryProjectionDTO();
        this.owner.setId(owner.getId());
        this.owner.setNome(owner.getNome());
        this.owner.setIe(owner.getIe());
    }

    public void setOwner(OrgSummaryProjectionDTO owner) {
        this.owner = owner;
    }

    @Override
    public List<IArea> getAreas() {
        return areas;
    }

    public void setAreas(List<IArea> areas) {
        this.areas = areas;
    }

    @Override
    public IPeriod getPeriod() {
        return period;
    }

    public void setPeriod(IPeriod period) {
        this.period = period;
    }

    @Override
    public ILevel getLevel() {
        return level;
    }

    public void setLevel(ILevel level) {
        this.level = level;
    }

    @Override
    public IFormat getFormat() {
        return format;
    }

    public void setFormat(IFormat format) {
        this.format = format;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
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
    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setDuracaoMeses(int duracaoMeses) {
        this.duracaoMeses = duracaoMeses;
    }

    @Override
    public float getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(float remuneracao) {
        this.remuneracao = remuneracao;
    }

    @Override
    public int getCargaHorariaSemanal() {
        return cargaHorariaSemanal;
    }

    public void setCargaHorariaSemanal(int cargaHorariaSemanal) {
        this.cargaHorariaSemanal = cargaHorariaSemanal;
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
    public ContactDetailsProjection getContact() {
        return contact;
    }

    public void setContact(ContactDetailsProjection contact) {
        this.contact = contact;
    }

    @Override
    public AddressDetailsProjection getAddress() {
        return address;
    }

    public void setAddress(AddressDetailsProjection address) {
        this.address = address;
    }

}
