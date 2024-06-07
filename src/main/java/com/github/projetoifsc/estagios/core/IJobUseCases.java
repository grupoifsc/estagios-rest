package com.github.projetoifsc.estagios.core;

import com.github.projetoifsc.estagios.core.models.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IJobUseCases {

    JobPrivateDetailsProjection create(String organizationId, IJobEntryData traineeship);
    JobPrivateDetailsProjection update(String organizationId, String traineeshipId, IJobEntryData newData);
    void delete(String organizationId, String traineeshipId);

    JobPublicDetailsProjection getOnePublicDetails(String organizationId, String traineeshipId);
    JobPrivateDetailsProjection getOnePrivateDetails(String organizationId, String traineeshipId);

    JobPublicSummaryProjection approve(String organizationId, String traineeshipId);
    JobPublicSummaryProjection reject(String organizationId, String traineeshipId);

    Page<JobPrivateSummaryProjection> getAllCreatedSummary(String loggedId, String targetId);
    List<JobPublicSummaryProjection> getAllAvailableSummary(String loggedId, String targetId);
    List<JobPublicSummaryProjection> getAllRejectedSummary(String loggedId, String targetId);
    List<JobPublicSummaryProjection> getAllPendingSummary(String loggedId, String targetId);

    List<JobPublicSummaryProjection> filterAllAvailableSummary(String loggedId, String targetId);

}
