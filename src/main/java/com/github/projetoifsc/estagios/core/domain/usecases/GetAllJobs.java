package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.domain.iJob;
import com.github.projetoifsc.estagios.core.domain.iJobRepository;
import com.github.projetoifsc.estagios.core.exceptions.UnauthorizedAccessException;
import static com.github.projetoifsc.estagios.core.domain.usecases.helper.OrganizationValidation.isSelf;

import java.util.List;
import java.util.stream.Stream;

public class GetAllJobs {

    private iJobRepository traineeshipRepository;
    private IOrganizationRepository organizationRepository;

    public GetAllJobs(iJobRepository traineeshipRepository, IOrganizationRepository organizationRepository) {
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

}
