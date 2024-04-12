package com.github.projetoifsc.estagios.core;

import java.util.List;

public interface IJobUseCases {

    List<IJob> getAllCreated(String loggedId, String targetId);
    List<IJob> getAllReceived(String loggedId, String targetId);
    IJob getPrivateDetails(String organizationId, String traineeshipId);
    IJob getPublicDetails(String organizationId, String traineeshipId);
    IJob create(String organizationId, IJob traineeship);
    IJob update(String organizationId, String traineeshipId, IJob newData);
    void delete(String organizationId, String traineeshipId);

}
