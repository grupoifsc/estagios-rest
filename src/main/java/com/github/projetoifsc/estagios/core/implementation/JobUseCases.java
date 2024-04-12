package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.core.IJobRepository;

import java.util.List;


class JobUseCases {

    private final JobReadOperations readOperations;
    private final JobWriteOperations writeOperations;

    public JobUseCases(IJobRepository jobRepository, IOrganizationRepository organizationRepository) {
        readOperations = new JobReadOperations(jobRepository, organizationRepository);
        writeOperations = new JobWriteOperations(jobRepository, organizationRepository);
    }

    public List<IJob> getAllCreated(String loggedId, String targetId) {
        return readOperations.getAllCreated(loggedId, targetId);
    }

    public List<IJob> getAllReceived(String loggedId, String targetId) {
        return readOperations.getAllReceived(loggedId, targetId);
    }

    public IJob getPrivateDetails(String organizationId, String traineeshipId) {
        return readOperations.getPrivateDetails(organizationId, traineeshipId);
    }

    public IJob getPublicDetails(String organizationId, String traineeshipId) {
        return readOperations.getPublicDetails(organizationId, traineeshipId);
    }

    public IJob create(String organizationId, IJob traineeship) {
        return writeOperations.create(organizationId, traineeship);
    }

    public IJob update(String organizationId, String traineeshipId, IJob newData) {
        return writeOperations.update(organizationId, traineeshipId, newData);
    }

    public void delete(String organizationId, String traineeshipId) {
        writeOperations.delete(organizationId, traineeshipId);
    }

}
