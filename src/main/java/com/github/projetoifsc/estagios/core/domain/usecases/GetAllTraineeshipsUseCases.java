package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IOrganization;
import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.domain.ITraineeship;
import com.github.projetoifsc.estagios.core.domain.ITraineeshipRepository;
import com.github.projetoifsc.estagios.core.exceptions.UnauthorizedAccessException;
import static com.github.projetoifsc.estagios.core.domain.usecases.OrganizationValidation.canGetAllTraineeships;

import java.util.List;
import java.util.stream.Stream;

public class GetAllTraineeshipsUseCases {

    private ITraineeshipRepository traineeshipRepository;
    private IOrganizationRepository organizationRepository;

    public GetAllTraineeshipsUseCases(ITraineeshipRepository traineeshipRepository, IOrganizationRepository organizationRepository) {
        this.traineeshipRepository = traineeshipRepository;
        this.organizationRepository = organizationRepository;
    }


    public List<ITraineeship> getAllCreated(String loggedId, String targetId) {
        if(canGetAllTraineeships(loggedId, targetId))
            return organizationRepository.getCreatedJobs(targetId);
        var errorMessage = "Organizations can only access their own created traineeships";
        throw new UnauthorizedAccessException(errorMessage);
    }

    public List<ITraineeship> getAllReceived(String loggedId, String targetId) {
        if(canGetAllTraineeships(loggedId, targetId)) {
            var exclusivelyReceived = organizationRepository.getExclusiveReceivedJobs(targetId);
            var globalTraineeships = traineeshipRepository.findAllWithoutReceivers();
            return Stream.concat(exclusivelyReceived.stream(), globalTraineeships.stream()).toList();
        }
        var errorMessage = "Organizations can only access their own created traineeships";
        throw new UnauthorizedAccessException(errorMessage);

    }

}
