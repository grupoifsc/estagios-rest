package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.*;
import com.github.projetoifsc.estagios.core.models.IJob;
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
    public IJob getOneDetails(String organizationId, String traineeshipId) {
        //return readOperations.getOneDetails(organizationId, traineeshipId);
        return readOperations.getOneDetailsWithModStatus(organizationId, traineeshipId);
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
    public JobPublicDetailsProjection getOnePublicDetailsWithMod(String organizationId, String traineeshipId) {
        return readOperations.getOnePublicDetailsWithMod(organizationId,traineeshipId);
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
    public Page<JobPrivateDetailsProjection> getAllCreatedDetailsWithPagination(String loggedId, String targetId, int page, int limit) {
        return readOperations.getAllCreatedDetailsWithModeration(loggedId, targetId, page, limit);
    }

    @Override
    public Page<JobPublicDetailsProjection> getAllAvailable(String loggedId, String targetId) {
        return readOperations.getAllAvailable(loggedId, targetId);
    }

    @Override
    public Page<JobPublicDetailsProjection> getAllPending(String loggedId, String targetId) {
        return readOperations.getAllPending(loggedId, targetId);
    }

    @Override
    public Page<JobPublicDetailsProjection> getAllReceivedWithPagination(String loggedId, String targetId, int page, int limit) {
        return readOperations.getAllReceivedWithPagination(loggedId, targetId, page, limit);
    }

    @Override
    public Page<JobPublicDetailsProjection> getAllRejected(String loggedId, String targetId) {
        return readOperations.getAllRejected(loggedId, targetId);
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
