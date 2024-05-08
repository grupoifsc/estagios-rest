package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IOrganizationDB;
import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.core.IJobDB;
import org.springframework.data.domain.Page;

import static com.github.projetoifsc.estagios.core.implementation.OrganizationValidation.isSelf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class JobReadOperations {

    private final IJobDB traineeshipRepository;
    private final IOrganizationDB organizationRepository;

    public JobReadOperations(IJobDB traineeshipRepository, IOrganizationDB organizationRepository) {
        this.traineeshipRepository = traineeshipRepository;
        this.organizationRepository = organizationRepository;
    }


    public Page<IJob> getAllCreated(String loggedId, String targetId) {
        if(isSelf(loggedId, targetId))
            return organizationRepository.getCreatedJobs(targetId);
        var errorMessage = "Organizations can only access their own created traineeships";
        throw new UnauthorizedAccessException(errorMessage);
    }

    // TODO Ver se o Set vai estar funcionando aqui, se o equals vai estar correto...
    // Aqui tem o problema da paginação... Vai ficar estranho, vai ficar desencontrado
    public List<IJob> getAllReceived(String loggedId, String targetId) {
        if(isSelf(loggedId, targetId)) {
            Set<IJob> set = new HashSet<>();
            if(organizationRepository.findById(loggedId).getIe()) {
                var created = organizationRepository.getCreatedJobs(loggedId).stream().toList();
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

    public IJob getPrivateDetails(String organizationId, String traineeshipId) {
        var organization = organizationRepository.findById(organizationId);
        var traineeship = traineeshipRepository.findById(traineeshipId);

        if (OrganizationValidation.isOwner(organization, traineeship)) {
            return traineeshipRepository.getPrivateDetails(traineeshipId);
        }

        var errorMessage = "Organizations can only see private details of traineeships which they own";
        throw new UnauthorizedAccessException(errorMessage);
    }


    public IJob getPublicDetails(String organizationId, String traineeshipId) {
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
