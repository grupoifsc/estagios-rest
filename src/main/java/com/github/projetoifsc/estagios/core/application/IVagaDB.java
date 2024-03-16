package com.github.projetoifsc.estagios.core.application;

import java.util.Collection;

public interface IVagaDB {

    String getId();


    void setId(String id);

    void setOwner(IUserDB user);

    Collection<? extends IUserDB> getReceivers();

    IUserDB getOwner();

    IVagaDB getPublicProfile();
    IVagaDB getPrivateProfile();

}
