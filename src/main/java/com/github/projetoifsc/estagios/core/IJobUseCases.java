package com.github.projetoifsc.estagios.core;

import com.github.projetoifsc.estagios.core.models.IJobEntryData;
import com.github.projetoifsc.estagios.core.models.projections.JobPrivateDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.JobPublicDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.ModerationDetailsProjection;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IJobUseCases {

    JobPrivateDetailsProjection create(String organizationId, IJobEntryData traineeship);
    JobPrivateDetailsProjection update(String organizationId, String traineeshipId, IJobEntryData newData);
    void delete(String organizationId, String traineeshipId);

    JobPublicDetailsProjection getOnePublicDetails(String organizationId, String traineeshipId);
    JobPrivateDetailsProjection getOnePrivateDetails(String organizationId, String traineeshipId);

    void approve(String organizationId, List<String> traineeshipIds);
    void reject(String organizationId, List<String> traineeshipIds);

    Page<JobPrivateDetailsProjection> getAllCreatedDetails(String loggedId, String targetId);
    List<JobPublicDetailsProjection> getAllAvailableSummary(String loggedId, String targetId);
    List<JobPublicDetailsProjection> getAllRejectedSummary(String loggedId, String targetId);
    List<JobPublicDetailsProjection> getAllPendingSummary(String loggedId, String targetId);

    List<JobPublicDetailsProjection> filterAllAvailableSummary(String loggedId, String targetId);

    ModerationDetailsProjection getModerationInfo(String organizationId, String traineeshipId);



}
