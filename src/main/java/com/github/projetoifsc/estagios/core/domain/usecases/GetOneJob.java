package com.github.projetoifsc.estagios.core.domain.usecases;


import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.domain.iJob;
import com.github.projetoifsc.estagios.core.domain.iJobRepository;
import com.github.projetoifsc.estagios.core.domain.usecases.helper.OrganizationValidation;
import com.github.projetoifsc.estagios.core.exceptions.UnauthorizedAccessException;

public class GetOneJob {

    iJobRepository traineeshipRepository;
    IOrganizationRepository organizationRepository;

    public GetOneJob(iJobRepository traineeshipRepository, IOrganizationRepository organizationRepository) {
        this.traineeshipRepository = traineeshipRepository;
        this.organizationRepository = organizationRepository;
    }


    public iJob getPrivateDetails(String organizationId, String traineeshipId) {
        var organization = organizationRepository.findById(organizationId);
        var traineeship = traineeshipRepository.findById(traineeshipId);

        if (OrganizationValidation.isOwner(organization, traineeship)) {
            return traineeshipRepository.getPrivateDetails(traineeshipId);
        }

        var errorMessage = "Organizations can only see private details of traineeships which they own";
        throw new UnauthorizedAccessException(errorMessage);
    }


    public iJob getPublicDetails(String organizationId, String traineeshipId) {
        var organization = organizationRepository.findById(organizationId);
        var traineeship = traineeshipRepository.findById(traineeshipId);

        var receivers = traineeshipRepository.getReceivers(traineeshipId);

        if (OrganizationValidation.isOwner(organization, traineeship)
                || OrganizationValidation.isReceiver(organization, receivers)) {
            return traineeshipRepository.getPublicDetails(traineeshipId);
        }

        var errorMessage = "Organizations can only see traineeships which they own or receive";
        throw new UnauthorizedAccessException(errorMessage);
    }



}

