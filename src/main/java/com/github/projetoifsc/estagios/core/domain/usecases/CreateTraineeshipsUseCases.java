package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IOrganization;
import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.domain.ITraineeship;
import com.github.projetoifsc.estagios.core.domain.ITraineeshipRepository;
import com.github.projetoifsc.estagios.core.exceptions.UnauthorizedAccessException;


public class CreateTraineeshipsUseCases {

    ITraineeshipRepository traineeshipRepository;
    IOrganizationRepository organizationRepository;

    public CreateTraineeshipsUseCases(ITraineeshipRepository traineeshipRepository, IOrganizationRepository organizationRepository) {
        this.traineeshipRepository = traineeshipRepository;
        this.organizationRepository = organizationRepository;
    }


    public ITraineeship create(String organizationId, ITraineeship traineeship) {
        var organization = organizationRepository.findById(organizationId);
        return saveOrUpdate(organization, traineeship);
    }


    public ITraineeship update(String organizationId, String traineeshipId, ITraineeship newData) {
        var traineeship = traineeshipRepository.findById(traineeshipId);
        var organization = organizationRepository.findById(organizationId);

        if ( TraineeshipValidation.organizationIsOwner(organization, traineeship) ) {
            newData.setId(traineeshipId);
            return saveOrUpdate(organization, traineeship);
        }

        var errorMessage = "Organizations can only update their own traineeships";
        throw new UnauthorizedAccessException(errorMessage);
    }


    public void delete(String organizationId, String traineeshipId) {
        var traineeship = traineeshipRepository.findById(traineeshipId);
        var organization = organizationRepository.findById(organizationId);

        if ( TraineeshipValidation.organizationIsOwner(organization, traineeship) ) {
            traineeshipRepository.delete(traineeship.getId());
            return;
        }

        var errorMessage = "Organizations can only delete their own traineeships";
        throw new UnauthorizedAccessException(errorMessage);
    }


    public ITraineeship getPrivateDetails(String organizationId, String traineeshipId) {
        var organization = organizationRepository.findById(organizationId);
        var traineeship = traineeshipRepository.findById(traineeshipId);

        if (TraineeshipValidation.organizationIsOwner(organization, traineeship)) {
            return traineeshipRepository.getPrivateDetails(traineeshipId);
        }

        var errorMessage = "Organizations can only see private details of traineeships which they own";
        throw new UnauthorizedAccessException(errorMessage);
    }


    public ITraineeship getPublicDetails(String organizationId, String traineeshipId) {
        var organization = organizationRepository.findById(organizationId);
        var traineeship = traineeshipRepository.findById(traineeshipId);

        var receivers = traineeshipRepository.getReceivers(traineeshipId);

        if (TraineeshipValidation.organizationIsOwner(organization, traineeship)
                || TraineeshipValidation.organizationIsReceiver(organization, receivers)) {
            return traineeshipRepository.getPublicDetails(traineeshipId);
        }

        var errorMessage = "Organizations can only see traineeships which they own or receive";
        throw new UnauthorizedAccessException(errorMessage);
    }


    private ITraineeship saveOrUpdate(IOrganization organization, ITraineeship traineeship) {
        traineeship.setOwner(organization);
        var receiversList = organizationRepository.findAllById(traineeship.getReceiversIds());
        TraineeshipValidation.validateReceivers(receiversList);
        return traineeshipRepository.save(traineeship);
    }



}