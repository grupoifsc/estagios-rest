package com.github.projetoifsc.estagios.core;

import java.util.List;

public interface IJobDB {

    IJob findById(String id);
    IJob save(IJob created);
    void delete(String ITraineeship);
    List<IOrganization> getReceivers(String ITraineeship);
    IJob getPublicDetails(String ITraineeship);
    IJob getPrivateDetails(String ITraineeship);
    List<IJob> findAllWithoutReceivers();

}
