package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.*;
import com.github.projetoifsc.estagios.core.models.IJobEntryData;
import com.github.projetoifsc.estagios.core.models.projections.JobPrivateDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.JobPublicDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.ModerationDetailsProjection;
import org.springframework.data.domain.Page;

import java.util.List;

public class JobUseCases implements IJobUseCases {

    private final JobReadOperations readOperations;
    private final JobWriteOperations writeOperations;

    public JobUseCases(IJobDAO jobRepository, IOrganizationDAO organizationRepository) {
        readOperations = new JobReadOperations(jobRepository, organizationRepository);
        writeOperations = new JobWriteOperations(readOperations, jobRepository, organizationRepository);
    }

    @Override
    public JobPrivateDetailsProjection create(String organizationId, IJobEntryData traineeship) {
        return writeOperations.create(organizationId, traineeship);
    }

    @Override
    public JobPrivateDetailsProjection update(String organizationId, String traineeshipId, IJobEntryData newData) {
        return writeOperations.update(organizationId, traineeshipId, newData);
    }

    @Override
    public void delete(String organizationId, String traineeshipId) {
        writeOperations.delete(organizationId, traineeshipId);
    }

    @Override
    public JobPublicDetailsProjection getOnePublicDetails(String organizationId, String traineeshipId) {
        return readOperations.getOnePublicDetails(organizationId, traineeshipId);
    }

    @Override
    public JobPrivateDetailsProjection getOnePrivateDetails(String organizationId, String traineeshipId) {
        return readOperations.getOnePrivateDetails(organizationId, traineeshipId);
    }

    @Override
    public void approve(String organizationId, List<String> traineeshipIds) {
        writeOperations.approve(organizationId, traineeshipIds);
    }

    @Override
    public void reject(String organizationId, List<String> traineeshipIds) {
        writeOperations.reject(organizationId, traineeshipIds);
    }

    @Override
    public Page<JobPrivateDetailsProjection> getAllCreatedDetails(String loggedId, String targetId) {
        return readOperations.getAllCreatedDetails(loggedId, targetId);
    }

    @Override
    public List<JobPublicDetailsProjection> getAllAvailableSummary(String loggedId, String targetId) {
        return readOperations.getAllAvailableSummary(loggedId, targetId);
    }

    @Override
    public List<JobPublicDetailsProjection> getAllPendingSummary(String loggedId, String targetId) {
        return readOperations.getAllPendingSummary(loggedId, targetId);
    }

    @Override
    public List<JobPublicDetailsProjection> getAllRejectedSummary(String loggedId, String targetId) {
        return readOperations.getAllRejectedSummary(loggedId, targetId);
    }


    @Override
    public List<JobPublicDetailsProjection> filterAllAvailableSummary(String loggedId, String targetId) {
        return List.of();
    }


    @Override
    public ModerationDetailsProjection getModerationInfo(String organizationId, String traineeshipId) {
        return readOperations.getModerationInfo(organizationId, traineeshipId);
    }

}
