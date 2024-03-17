package com.github.projetoifsc.estagios.core.domain;

import java.util.List;

public interface ITraineeshipRepository {

    ITraineeship findById(String id);

    ITraineeship save(ITraineeship created);
    void delete(String ITraineeship);

    List<ITraineeship> findAllWhereReceiversIsNull(boolean exclusive);
    List<IOrganization> getReceivers(String ITraineeship);
    ITraineeship getPublicDetails(String ITraineeship);
    ITraineeship getPrivateDetails(String ITraineeship);

}
