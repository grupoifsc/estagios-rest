package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.*;

class JobWriteOperations {

    JobReadOperations jobReadOperations;
    IJobDAO jobDB;
    IOrganizationDAO organizationDB;

    public JobWriteOperations(JobReadOperations jobReadOperations, IJobDAO jobDB, IOrganizationDAO organizationDB) {
        this.jobReadOperations = jobReadOperations;
        this.jobDB = jobDB;
        this.organizationDB = organizationDB;
    }


    public IJob create(String organizationId, IJobEntryData traineeship) {
        var organization = organizationDB.findById(organizationId);
        return saveOrUpdate(organization, traineeship);
    }

    private IJob saveOrUpdate(IOrganization organization, IJobEntryData traineeship) {
        traineeship.setOwner(organization);
        if(traineeship.getReceiversIds() != null && !traineeship.getReceiversIds().isEmpty()) {
            var receiversList = organizationDB.findAllById(traineeship.getReceiversIds());
            ReceiverValidation.validateReceivers(receiversList);
        }
        var id = jobDB.saveAndGetId(traineeship);
        return jobDB.getPrivateDetails(id);
    }


    public IJob update(String organizationId, String traineeshipId, IJobEntryData newData) {
        var traineeship = jobDB.getBasicInfo(traineeshipId);
        var organization = organizationDB.findById(organizationId);

        if ( OrganizationValidation.isOwner(organization, traineeship) ) {
            newData.setId(traineeshipId);
            return saveOrUpdate(organization, newData);
        }

        var errorMessage = "Organizations can only update their own traineeships";
        throw new UnauthorizedAccessException(errorMessage);
    }


    public void delete(String organizationId, String traineeshipId) {
        var traineeship = jobDB.getBasicInfo(traineeshipId);
        var organization = organizationDB.findById(organizationId);

        if ( OrganizationValidation.isOwner(organization, traineeship) ) {
            jobDB.delete(traineeship.getId());
            return;
        }

        var errorMessage = "Organizations can only delete their own traineeships";
        throw new UnauthorizedAccessException(errorMessage);
    }


    public IJob approve(String organizationId, String traineeshipId) {
        var org = organizationDB.findById(organizationId);
        var job = jobDB.getBasicInfo(traineeshipId);
        if(canModerate(org, job))
            return jobDB.setJobApprovedByOrg(traineeshipId, organizationId);
        var errorMessage = "Not allowed to moderate this resource";
        throw new UnauthorizedAccessException(errorMessage);
    }


    // TODO Refactor: pode substituir por métodos como isPublic() e um isReceiver() -> Este teria que ser chamada ao banco de dados...
    private boolean canModerate(IOrganization org, IJob job) {
        if(OrganizationValidation.isIE(org) && !OrganizationValidation.isOwner(org, job)) {
            var receivedJobs = jobReadOperations.getAllReceivedSummary(org);
            return receivedJobs.contains(job);
        }
        return false;
    }


    public IJob reject(String organizationId, String traineeshipId) {
        var org = organizationDB.findById(organizationId);
        var job = jobDB.getBasicInfo(traineeshipId);
        if(canModerate(org, job))
            return jobDB.setJobRejectedByOrg(traineeshipId, organizationId);
        var errorMessage = "Not allowed to moderate this resource";
        throw new UnauthorizedAccessException(errorMessage);
     }


}
