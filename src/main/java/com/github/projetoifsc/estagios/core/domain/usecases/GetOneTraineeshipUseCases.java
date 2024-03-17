package com.github.projetoifsc.estagios.core.domain.usecases;


import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.domain.ITraineeship;
import com.github.projetoifsc.estagios.core.domain.ITraineeshipRepository;
import com.github.projetoifsc.estagios.core.exceptions.UnauthorizedAccessException;

public class GetOneTraineeshipUseCases {

    ITraineeshipRepository traineeshipRepository;
    IOrganizationRepository organizationRepository;

    public GetOneTraineeshipUseCases(ITraineeshipRepository traineeshipRepository, IOrganizationRepository organizationRepository) {
        this.traineeshipRepository = traineeshipRepository;
        this.organizationRepository = organizationRepository;
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



}

