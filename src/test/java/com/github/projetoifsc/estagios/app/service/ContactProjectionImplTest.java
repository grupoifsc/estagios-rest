package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.model.interfaces.ContactProjection;

class ContactProjectionImplTest implements ContactProjection {

    private String id;
    private String email;
    private String telefone;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
