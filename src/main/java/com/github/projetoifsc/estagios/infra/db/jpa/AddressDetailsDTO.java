package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.models.projections.AddressDetailsProjection;

import java.io.Serializable;

/**
 * DTO for {@link AddressEntity}
 */
class AddressDetailsDTO implements AddressDetailsProjection {
    private Long id;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String type;

    public String getId() {
        return String.valueOf(id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}