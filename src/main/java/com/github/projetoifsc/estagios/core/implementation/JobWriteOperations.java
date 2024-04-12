package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.core.IJobRepository;
import com.github.projetoifsc.estagios.core.implementation.UnauthorizedAccessException;


class JobWriteOperations {

    IJobRepository traineeshipRepository;
    IOrganizationRepository organizationRepository;

    public JobWriteOperations(IJobRepository traineeshipRepository, IOrganizationRepository organizationRepository) {
        this.traineeshipRepository = traineeshipRepository;
        this.organizationRepository = organizationRepository;
    }


    public IJob create(String organizationId, IJob traineeship) {
        var organization = organizationRepository.findById(organizationId);
        return saveOrUpdate(organization, traineeship);
    }


    public IJob update(String organizationId, String traineeshipId, IJob newData) {
        var traineeship = traineeshipRepository.findById(traineeshipId);
        var organization = organizationRepository.findById(organizationId);

        if ( OrganizationValidation.isOwner(organization, traineeship) ) {
            newData.setId(traineeshipId);
            return saveOrUpdate(organization, traineeship);
        }

        var errorMessage = "Organizations can only update their own traineeships";
        throw new UnauthorizedAccessException(errorMessage);
    }


    public void delete(String organizationId, String traineeshipId) {
        var traineeship = traineeshipRepository.findById(traineeshipId);
        var organization = organizationRepository.findById(organizationId);

        if ( OrganizationValidation.isOwner(organization, traineeship) ) {
            traineeshipRepository.delete(traineeship.getId());
            return;
        }

        var errorMessage = "Organizations can only delete their own traineeships";
        throw new UnauthorizedAccessException(errorMessage);
    }



    private IJob saveOrUpdate(IOrganization organization, IJob traineeship) {
        traineeship.setOwner(organization);
        var receiversList = organizationRepository.findAllById(traineeship.getReceiversIds());
        ReceiverValidation.validateReceivers(receiversList);
        return traineeshipRepository.save(traineeship);
    }



}
