package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.*;
import com.github.projetoifsc.estagios.core.models.*;
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
    public JobPublicSummaryProjection approve(String organizationId, String traineeshipId) {
        return writeOperations.approve(organizationId, traineeshipId);
    }

    @Override
    public JobPublicSummaryProjection reject(String organizationId, String traineeshipId) {
        return writeOperations.reject(organizationId, traineeshipId);
    }

    @Override
    public Page<JobPrivateSummaryProjection> getAllCreatedSummary(String loggedId, String targetId) {
        return readOperations.getAllCreatedSummary(loggedId, targetId);
    }

    @Override
    public List<JobPublicSummaryProjection> getAllAvailableSummary(String loggedId, String targetId) {
        return readOperations.getAllAvailableSummary(loggedId, targetId);
    }

    @Override
    public List<JobPublicSummaryProjection> getAllPendingSummary(String loggedId, String targetId) {
        return readOperations.getAllPendingSummary(loggedId, targetId);
    }

    @Override
    public List<JobPublicSummaryProjection> getAllRejectedSummary(String loggedId, String targetId) {
        return readOperations.getAllRejectedSummary(loggedId, targetId);
    }


    @Override
    public List<JobPublicSummaryProjection> filterAllAvailableSummary(String loggedId, String targetId) {
        return List.of();
    }

}
