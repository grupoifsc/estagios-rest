package com.github.projetoifsc.estagios.core;

import org.springframework.data.domain.Page;

import java.util.List;

public interface IJobUseCases {

    IJob create(String organizationId, IJobEntryData traineeship);
    IJob update(String organizationId, String traineeshipId, IJobEntryData newData);
    void delete(String organizationId, String traineeshipId);

    IJob getOnePublicDetails(String organizationId, String traineeshipId);
    IJob getOnePrivateDetails(String organizationId, String traineeshipId);

    IJob approve(String organizationId, String traineeshipId);
    IJob reject(String organizationId, String traineeshipId);

    Page<IJob> getAllCreatedSummary(String loggedId, String targetId);
    List<IJob> getAllAvailableSummary(String loggedId, String targetId);
    List<IJob> getAllApprovedSummary(String loggedId, String targetId);
    List<IJob> getAllRejectedSummary(String loggedId, String targetId);
    List<IJob> getAllPendingSummary(String loggedId, String targetId);

    List<IJob> filterAllAvailableSummary(String loggedId, String targetId);

}
