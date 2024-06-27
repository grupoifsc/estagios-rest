package com.github.projetoifsc.estagios.core.models.projections;

import com.github.projetoifsc.estagios.core.models.IAddress;

public interface AddressDetailsProjection {

    String getId();
    String getType();
    String getRua();
    String getBairro();
    String getCidade();
    String getEstado();
    String getPais();

}
