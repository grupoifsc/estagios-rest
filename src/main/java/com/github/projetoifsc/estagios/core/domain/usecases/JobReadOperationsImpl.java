package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.domain.iJob;
import com.github.projetoifsc.estagios.core.domain.iJobRepository;
import com.github.projetoifsc.estagios.core.domain.usecases.helper.OrganizationValidation;
import com.github.projetoifsc.estagios.core.exceptions.UnauthorizedAccessException;
import static com.github.projetoifsc.estagios.core.domain.usecases.helper.OrganizationValidation.isSelf;

import java.util.List;
import java.util.stream.Stream;

class JobReadOperationsImpl implements IJobReadOperations {

    private final iJobRepository traineeshipRepository;
    private final IOrganizationRepository organizationRepository;

    public JobReadOperationsImpl(iJobRepository traineeshipRepository, IOrganizationRepository organizationRepository) {
        this.traineeshipRepository = traineeshipRepository;
        this.organizationRepository = organizationRepository;
    }


    public List<iJob> getAllCreated(String loggedId, String targetId) {
        if(isSelf(loggedId, targetId))
            return organizationRepository.getCreatedJobs(targetId);
        var errorMessage = "Organizations can only access their own created traineeships";
        throw new UnauthorizedAccessException(errorMessage);
    }

    public List<iJob> getAllReceived(String loggedId, String targetId) {
        if(isSelf(loggedId, targetId)) {
            var exclusivelyReceived = organizationRepository.getExclusiveReceivedJobs(targetId);
            var globalTraineeships = traineeshipRepository.findAllWithoutReceivers();
            return Stream.concat(exclusivelyReceived.stream(), globalTraineeships.stream()).toList();
        }
        var errorMessage = "Organizations can only access their own created traineeships";
        throw new UnauthorizedAccessException(errorMessage);

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
