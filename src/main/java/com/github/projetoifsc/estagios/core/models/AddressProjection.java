package com.github.projetoifsc.estagios.core.models;

public interface AddressProjection extends IAddress {
    String getId();
    String getRua();
    String getBairro();
    String getCidade();
    String getEstado();
    String getPais();
}
