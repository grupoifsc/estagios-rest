package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IJobUseCases;
import com.github.projetoifsc.estagios.core.IOrganizationDB;
import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.core.IJobDB;
import org.springframework.data.domain.Page;

import java.util.List;


public class JobUseCases implements IJobUseCases {

    private final JobReadOperations readOperations;
    private final JobWriteOperations writeOperations;

    public JobUseCases(IJobDB jobRepository, IOrganizationDB organizationRepository) {
        readOperations = new JobReadOperations(jobRepository, organizationRepository);
        writeOperations = new JobWriteOperations(jobRepository, organizationRepository);
    }

    @Override
    public Page<IJob> getAllCreated(String loggedId, String targetId) {
        return readOperations.getAllCreated(loggedId, targetId);
    }

    @Override
    public List<IJob> getAllReceived(String loggedId, String targetId) {
        return readOperations.getAllReceived(loggedId, targetId);
    }

    @Override
    public IJob getPrivateDetails(String organizationId, String traineeshipId) {
        return readOperations.getPrivateDetails(organizationId, traineeshipId);
    }

    @Override
    public IJob getPublicDetails(String organizationId, String traineeshipId) {
        return readOperations.getPublicDetails(organizationId, traineeshipId);
    }

    @Override
    public IJob create(String organizationId, IJob traineeship) {
        return writeOperations.create(organizationId, traineeship);
    }

    @Override
    public IJob update(String organizationId, String traineeshipId, IJob newData) {
        return writeOperations.update(organizationId, traineeshipId, newData);
    }

    @Override
    public void delete(String organizationId, String traineeshipId) {
        writeOperations.delete(organizationId, traineeshipId);
    }

}
