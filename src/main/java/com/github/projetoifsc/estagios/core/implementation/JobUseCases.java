package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.*;
import org.springframework.data.domain.Page;

import java.util.List;


public class JobUseCases implements IJobUseCases {

    private final JobReadOperations readOperations;
    private final JobWriteOperations writeOperations;

    public JobUseCases(IJobDB jobRepository, IOrganizationDB organizationRepository) {
        readOperations = new JobReadOperations(jobRepository, organizationRepository);
        writeOperations = new JobWriteOperations(readOperations, jobRepository, organizationRepository);
    }

    @Override
    public Page<IJob> getAllCreatedSummary(String loggedId, String targetId) {
        return readOperations.getAllCreatedSummary(loggedId, targetId);
    }

    @Override
    public List<IJob> getAllReceivedSummary(String loggedId, String targetId) {
        return readOperations.getAllReceivedSummary(loggedId, targetId);
    }

    @Override
    public IJob getOnePrivateDetails(String organizationId, String traineeshipId) {
        return readOperations.getOnePrivateDetails(organizationId, traineeshipId);
    }

    @Override
    public IJob getOnePublicDetails(String organizationId, String traineeshipId) {
        return readOperations.getOnePublicDetails(organizationId, traineeshipId);
    }

    @Override
    public IJob create(String organizationId, IJobEntryData traineeship) {
        return writeOperations.create(organizationId, traineeship);
    }

    @Override
    public IJob update(String organizationId, String traineeshipId, IJobEntryData newData) {
        return writeOperations.update(organizationId, traineeshipId, newData);
    }

    @Override
    public void delete(String organizationId, String traineeshipId) {
        writeOperations.delete(organizationId, traineeshipId);
    }

    @Override
    public void aprove(String organizationId, String traineeshipId) {
        writeOperations.approve(organizationId, traineeshipId);
    }

    @Override
    public void reject(String organizationId, String traineeshipId) {
        writeOperations.reject(organizationId, traineeshipId);
    }

    @Override
    public List<IJob> getAllAprovedSummary(String loggedId, String targetId) {
        return readOperations.getAllApprovedSummary(loggedId, targetId);
    }

}
