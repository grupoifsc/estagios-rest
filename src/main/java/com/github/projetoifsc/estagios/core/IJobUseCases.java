package com.github.projetoifsc.estagios.core;

import org.springframework.data.domain.Page;

import java.util.List;

public interface IJobUseCases {

    Page<IJob> getAllCreated(String loggedId, String targetId);
    List<IJob> getAllReceived(String loggedId, String targetId);
    IJob getPrivateDetails(String organizationId, String traineeshipId);
    IJob getPublicDetails(String organizationId, String traineeshipId);
    IJob create(String organizationId, IJob traineeship);
    IJob update(String organizationId, String traineeshipId, IJob newData);
    void delete(String organizationId, String traineeshipId);

}
