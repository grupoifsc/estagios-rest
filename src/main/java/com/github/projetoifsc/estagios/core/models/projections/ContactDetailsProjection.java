package com.github.projetoifsc.estagios.core.models.projections;

import com.github.projetoifsc.estagios.core.models.IContact;

public interface ContactDetailsProjection {

    String getId();
    String getType();
    String getEmail();
    String getTelefone();

}
