package com.github.projetoifsc.estagios.app.model.interfaces;

import com.github.projetoifsc.estagios.core.IAddress;

public interface AddressProjection extends IAddress {
    String getId();
    String getRua();
    String getBairro();
    String getCidade();
    String getEstado();
    String getPais();
}
