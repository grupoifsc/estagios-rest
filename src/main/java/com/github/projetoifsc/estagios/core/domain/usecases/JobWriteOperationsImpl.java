package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IOrganization;
import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.domain.iJob;
import com.github.projetoifsc.estagios.core.domain.iJobRepository;
import com.github.projetoifsc.estagios.core.domain.usecases.helper.OrganizationValidation;
import com.github.projetoifsc.estagios.core.domain.usecases.helper.ReceiverValidation;
import com.github.projetoifsc.estagios.core.exceptions.UnauthorizedAccessException;


class JobWriteOperationsImpl implements IJobWriteOperations {

    iJobRepository traineeshipRepository;
    IOrganizationRepository organizationRepository;

    public JobWriteOperationsImpl(iJobRepository traineeshipRepository, IOrganizationRepository organizationRepository) {
        this.traineeshipRepository = traineeshipRepository;
        this.organizationRepository = organizationRepository;
    }


    public iJob create(String organizationId, iJob traineeship) {
        var organization = organizationRepository.findById(organizationId);
        return saveOrUpdate(organization, traineeship);
    }


    public iJob update(String organizationId, String traineeshipId, iJob newData) {
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



    private iJob saveOrUpdate(IOrganization organization, iJob traineeship) {
        traineeship.setOwner(organization);
        var receiversList = organizationRepository.findAllById(traineeship.getReceiversIds());
        ReceiverValidation.validateReceivers(receiversList);
        return traineeshipRepository.save(traineeship);
    }



}
