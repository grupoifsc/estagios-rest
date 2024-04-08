package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.domain.iJob;
import com.github.projetoifsc.estagios.core.domain.iJobRepository;

import java.util.List;


public class JobUseCases implements IJobReadOperations, IJobWriteOperations {

   // private iJobRepository jobRepository;
   // private IOrganizationRepository organizationRepository;

    private final IJobReadOperations readOperations;
    private final JobWriteOperationsImpl writeOperations;

    public JobUseCases(iJobRepository jobRepository, IOrganizationRepository organizationRepository) {
     //   this.jobRepository = jobRepository;
      //  this.organizationRepository = organizationRepository;
        readOperations = new JobReadOperationsImpl(jobRepository, organizationRepository);
        writeOperations = new JobWriteOperationsImpl(jobRepository, organizationRepository);
    }

    @Override
    public List<iJob> getAllCreated(String loggedId, String targetId) {
        return readOperations.getAllCreated(loggedId, targetId);
    }

    @Override
    public List<iJob> getAllReceived(String loggedId, String targetId) {
        return readOperations.getAllReceived(loggedId, targetId);
    }

    @Override
    public iJob getPrivateDetails(String organizationId, String traineeshipId) {
        return readOperations.getPrivateDetails(organizationId, traineeshipId);
    }

    @Override
    public iJob getPublicDetails(String organizationId, String traineeshipId) {
        return readOperations.getPublicDetails(organizationId, traineeshipId);
    }

    @Override
    public iJob create(String organizationId, iJob traineeship) {
        return writeOperations.create(organizationId, traineeship);
    }

    @Override
    public iJob update(String organizationId, String traineeshipId, iJob newData) {
        return writeOperations.update(organizationId, traineeshipId, newData);
    }

    @Override
    public void delete(String organizationId, String traineeshipId) {
        writeOperations.delete(organizationId, traineeshipId);
    }

}
