package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.*;


class JobWriteOperations {

    JobReadOperations jobReadOperations;
    IJobDAO traineeshipRepository;
    IOrganizationDAO organizationRepository;

    public JobWriteOperations(JobReadOperations jobReadOperations, IJobDAO traineeshipRepository, IOrganizationDAO organizationRepository) {
        this.jobReadOperations = jobReadOperations;
        this.traineeshipRepository = traineeshipRepository;
        this.organizationRepository = organizationRepository;
    }


    public IJob create(String organizationId, IJobEntryData traineeship) {
        var organization = organizationRepository.findById(organizationId);
        return saveOrUpdate(organization, traineeship);
    }


    public IJob update(String organizationId, String traineeshipId, IJobEntryData newData) {
        var traineeship = traineeshipRepository.getBasicInfoById(traineeshipId);
        var organization = organizationRepository.findById(organizationId);

        if ( OrganizationValidation.isOwner(organization, traineeship) ) {
            newData.setId(traineeshipId);
            return saveOrUpdate(organization, newData);
        }

        var errorMessage = "Organizations can only update their own traineeships";
        throw new UnauthorizedAccessException(errorMessage);
    }


    public void delete(String organizationId, String traineeshipId) {
        var traineeship = traineeshipRepository.getBasicInfoById(traineeshipId);
        var organization = organizationRepository.findById(organizationId);

        if ( OrganizationValidation.isOwner(organization, traineeship) ) {
            traineeshipRepository.delete(traineeship.getId());
            return;
        }

        var errorMessage = "Organizations can only delete their own traineeships";
        throw new UnauthorizedAccessException(errorMessage);
    }



    private IJob saveOrUpdate(IOrganization organization, IJobEntryData traineeship) {
        traineeship.setOwner(organization);
        if(traineeship.getReceiversIds() != null && !traineeship.getReceiversIds().isEmpty()) {
            var receiversList = organizationRepository.findAllById(traineeship.getReceiversIds());
            ReceiverValidation.validateReceivers(receiversList);
        }
        var id = traineeshipRepository.saveAndGetId(traineeship);
        return traineeshipRepository.getPrivateDetails(id);
    }


    public void approve(String organizationId, String traineeshipId) {
        //  Checar SE: organizationId is receiver de traineeshipId
        var received = jobReadOperations.getAllReceivedSummary(organizationId, organizationId);
        var ids = received.stream().map(IJob::getId).toList();
        if(ids.contains(traineeshipId)) {
            traineeshipRepository.setJobApprovedByOrg(traineeshipId, organizationId);
        }
    }


    public void reject(String organizationId, String traineeshipId) {
        //  Checar SE: organizationId is receiver de traineeshipId
        var received = jobReadOperations.getAllReceivedSummary(organizationId, organizationId);
        var ids = received.stream().map(IJob::getId).toList();
        if(ids.contains(traineeshipId)) {
            traineeshipRepository.setJobReprovedByOrg(traineeshipId, organizationId);
        }
    }


}
