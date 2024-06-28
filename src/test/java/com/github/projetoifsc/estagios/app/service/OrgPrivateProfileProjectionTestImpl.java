package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.core.models.projections.AddressDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.ContactDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.OrgPrivateProfileProjection;

import java.time.LocalDateTime;

class OrgPrivateProfileProjectionTestImpl implements OrgPrivateProfileProjection {

    private String id;
    private String username;
    private String nome;
    private String cnpj;
    private boolean ie;
    private String info;
    private String website;
    private String redesSociais;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ContactDetailsProjection mainContact;
    private ContactDetailsProjection applianceContact;
    private AddressDetailsProjection mainAddress;

    @Override
    public String getId() {
        return id;
    }

    //@Override
    public UserCredentialsProjection getUserCredentials() {
        return null;
    }

    //@Override
    public void setId(String id) {
        this.id = id;
    }

//    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public Boolean getIe() {
        return ie;
    }

    public void setIe(boolean ie) {
        this.ie = ie;
    }

    @Override
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String getRedesSociais() {
        return redesSociais;
    }

    public void setRedesSociais(String redesSociais) {
        this.redesSociais = redesSociais;
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
    public ContactDetailsProjection getMainContact() {
        return mainContact;
    }

    public void setMainContact(ContactDetailsProjection mainContact) {
        this.mainContact = mainContact;
    }

    @Override
    public ContactDetailsProjection getApplianceContact() {
        return applianceContact;
    }

    public void setApplianceContact(ContactDetailsProjection applianceContact) {
        this.applianceContact = applianceContact;
    }

    @Override
    public AddressDetailsProjection getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(AddressDetailsProjection mainAddress) {
        this.mainAddress = mainAddress;
    }

}
