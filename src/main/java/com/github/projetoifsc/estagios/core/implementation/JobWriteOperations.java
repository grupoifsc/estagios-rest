package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.core.*;
import com.github.projetoifsc.estagios.core.models.IJob;
import com.github.projetoifsc.estagios.core.models.IJobEntryData;
import com.github.projetoifsc.estagios.core.models.IOrg;
import com.github.projetoifsc.estagios.core.models.projections.JobPrivateDetailsProjection;

import java.util.List;

class JobWriteOperations {

    JobReadOperations jobReadOperations;
    IJobDAO jobDB;
    IOrganizationDAO organizationDB;
    JsonParser jsonParser = new JsonParser();

    public JobWriteOperations(JobReadOperations jobReadOperations, IJobDAO jobDB, IOrganizationDAO organizationDB) {
        this.jobReadOperations = jobReadOperations;
        this.jobDB = jobDB;
        this.organizationDB = organizationDB;
    }


    public JobPrivateDetailsProjection create(String organizationId, IJobEntryData traineeship) {
        var organization = organizationDB.findById(organizationId);
        return saveOrUpdate(organization, traineeship);
    }

    private JobPrivateDetailsProjection saveOrUpdate(IOrg organization, IJobEntryData traineeship) {
        traineeship.setOwner(organization);
        if(traineeship.getReceiversIds() != null && !traineeship.getReceiversIds().isEmpty()) {
            var receiversList = organizationDB.findAllById(traineeship.getReceiversIds());
            ReceiverValidation.validateReceivers(receiversList);
        }
        var id = jobDB.saveAndGetId(traineeship);
        return jobDB.getJobPrivateDetails(id);
    }


    // TODO BugFix: Resolver incronguência que ocorre quando uma vaga antes pública
    //  se torna exclusiva, mas já foi aprovada por IEs para as quais a vaga não é mais
    //  ofertada
    public JobPrivateDetailsProjection update(String organizationId, String traineeshipId, IJobEntryData newData) {
        var traineeship = jobDB.getJobBasicInfo(traineeshipId);
        var organization = organizationDB.findById(organizationId);

        if ( OrganizationValidation.isOwner(organization, traineeship) ) {
            newData.setId(traineeshipId);
            return saveOrUpdate(organization, newData);
        }

        var errorMessage = "Organizations can only update their own traineeships";
        throw new UnauthorizedAccessException(errorMessage);
    }


    public void delete(String organizationId, String traineeshipId) {
        var traineeship = jobDB.getJobBasicInfo(traineeshipId);
        var organization = organizationDB.findById(organizationId);

        if ( OrganizationValidation.isOwner(organization, traineeship) ) {
            jobDB.delete(traineeship.getId());
            return;
        }

        var errorMessage = "Organizations can only delete their own traineeships";
        throw new UnauthorizedAccessException(errorMessage);
    }


    public void approve(String organizationId, List<String> traineeshipIds) {
        var org = organizationDB.findById(organizationId);
        var jobs = jobDB.getJobBasicInfo(traineeshipIds);

        var errors = jobs.stream().filter(job -> !canModerate(org, job)).map(IJob::getId).toList();
        if(!errors.isEmpty()) {
            var errorMessage = "Not allowed to moderate the resource(s): " + errors.toString();
            throw new UnauthorizedAccessException(errorMessage);
        }

        jobDB.setJobApprovedByOrg(jobs, organizationId);

    }


    private boolean canModerate(IOrg org, IJob job) {
        if(OrganizationValidation.isIE(org) && !OrganizationValidation.isOwner(org, job)) {
            return jobDB.isJobOfferedToOrg(job.getId(), org.getId());
        }
        return false;
    }


    public void reject(String organizationId, List<String> traineeshipIds) {
        var org = organizationDB.findById(organizationId);
        var jobs = jobDB.getJobBasicInfo(traineeshipIds);

        var errors = jobs.stream().filter(job -> !canModerate(org, job)).map(IJob::getId).toList();
        if(!errors.isEmpty()) {
            var errorMessage = "Not allowed to moderate the resource(s): " + errors.toString();
            throw new UnauthorizedAccessException(errorMessage);
        }

        jobDB.setJobRejectedByOrg(jobs, organizationId);

    }


}
