package com.github.projetoifsc.estagios.core.application;

import java.util.Collection;

public interface IUserDB {

    boolean getIe();

    String getId();
    void setId(String id);

    Collection<? extends IVagaDB> getExclusiveReceivedVagas();
    Collection<? extends IVagaDB> getOwnedVagas();

    IUserDB getPrivateProfile();

    IUserDB getPublicProfile();

}
