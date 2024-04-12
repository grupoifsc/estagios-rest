package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.domain.iJob;
import com.github.projetoifsc.estagios.core.domain.iJobRepository;
import com.github.projetoifsc.estagios.core.exceptions.UnauthorizedAccessException;
import static com.github.projetoifsc.estagios.core.domain.usecases.OrganizationValidation.isSelf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    // TODO Ver se o Set vai estar funcionando aqui, se o equals vai estar correto...
    public List<iJob> getAllReceived(String loggedId, String targetId) {
        if(isSelf(loggedId, targetId)) {
            Set<iJob> set = new HashSet<>();
            if(organizationRepository.findById(loggedId).isSchool()) {
                var created = organizationRepository.getCreatedJobs(loggedId);
                set.addAll(created);
            }
            var exclusivelyReceived = organizationRepository.getExclusiveReceivedJobs(targetId);
            set.addAll(exclusivelyReceived);
            var globallyReceived = traineeshipRepository.findAllWithoutReceivers();
            set.addAll(globallyReceived);
            return new ArrayList<>(set);
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
