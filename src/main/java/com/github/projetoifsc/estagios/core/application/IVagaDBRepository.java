package com.github.projetoifsc.estagios.core.application;

import java.util.Collection;
import java.util.List;

public interface IVagaDBRepository {
    IVagaDB save(IVagaDB created);
    IVagaDB findById(Long vagaId);
    void delete(IVagaDB vaga);
    List<IVagaDB> findByExclusive(boolean exclusive);
    Collection<? extends IVagaDB> findByExclusiveAndOwner(boolean exclusive, IUserDB owner);
    Collection<? extends IVagaDB> findByReceiverAndOwner(IUserDB receiver, IUserDB owner);

}
