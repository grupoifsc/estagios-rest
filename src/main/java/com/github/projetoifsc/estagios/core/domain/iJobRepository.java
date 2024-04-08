package com.github.projetoifsc.estagios.core.domain;

import java.util.List;

public interface iJobRepository {

    iJob findById(String id);

    iJob save(iJob created);
    void delete(String ITraineeship);

    List<IOrganization> getReceivers(String ITraineeship);
    iJob getPublicDetails(String ITraineeship);
    iJob getPrivateDetails(String ITraineeship);

    List<iJob> findAllWithoutReceivers();

}
