package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.app.model.interfaces.JobPublicSummaryProjection;
import com.github.projetoifsc.estagios.app.model.interfaces.OrgBasicInfoProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@JsonPropertyOrder(value = {"id", "titulo", "criado_por", "descricao",
        "requisitos", "carga_horaria", "remuneracao",
        "modificado_em", "_links"})
public class PublicJobSummaryResponse extends BasicJobResponse implements JobPublicSummaryProjection {

//    @JsonProperty("id")
//    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "1")
//    private String id;


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

    @Schema(accessMode = Schema.AccessMode.READ_ONLY )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("modificado_em")
    private LocalDateTime updatedAt;

    @Override
    public OrgBasicInfoProjection getOwner() {
        return super.owner;
    }


    @Override
    public @NotBlank String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(@NotBlank String requisitos) {
        this.requisitos = requisitos;
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
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

//    @Override
//    public String getId() {
//        return id;
//    }
//
//    @Override
//    public void setId(String id) {
//        this.id = id;
//    }


}
