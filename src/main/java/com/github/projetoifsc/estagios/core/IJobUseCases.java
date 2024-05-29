package com.github.projetoifsc.estagios.core;

import org.springframework.data.domain.Page;

import java.util.List;

public interface IJobUseCases {

    Page<IJob> getAllCreatedSummary(String loggedId, String targetId);
    List<IJob> getAllReceivedSummary(String loggedId, String targetId);
    IJob getOnePrivateDetails(String organizationId, String traineeshipId);
    IJob getOnePublicDetails(String organizationId, String traineeshipId);
    IJob create(String organizationId, IJobEntryData traineeship);
    IJob update(String organizationId, String traineeshipId, IJobEntryData newData);
    void delete(String organizationId, String traineeshipId);

    void aprove(String organizationId, String traineeshipId);
    void reject(String organizationId, String traineeshipId);
    List<IJob> getAllAprovedSummary(String loggedId, String targetId);

}
