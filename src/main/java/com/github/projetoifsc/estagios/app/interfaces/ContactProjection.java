package com.github.projetoifsc.estagios.app.interfaces;

import com.github.projetoifsc.estagios.core.IContact;

public interface ContactProjection extends IContact {
    String getId();
    String getEmail();
    String getTelefone();
}
