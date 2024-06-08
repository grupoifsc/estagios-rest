package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.*;
import com.github.projetoifsc.estagios.core.models.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;

import static com.github.projetoifsc.estagios.core.implementation.OrganizationValidation.*;

import java.util.ArrayList;
import java.util.List;

class JobReadOperations {

    private final IJobDAO jobDB;
    private final IOrganizationDAO organizationDB;

    public JobReadOperations(IJobDAO jobDB, IOrganizationDAO organizationDB) {
        this.jobDB = jobDB;
        this.organizationDB = organizationDB;
    }


    public JobPublicDetailsProjection getOnePublicDetails(String organizationId, String traineeshipId) {
        var organization = organizationDB.findById(organizationId);
        var traineeship = jobDB.getPublicDetails(traineeshipId);

        var exclusiveReceivers = organizationDB.getExclusiveReceiversForJob(traineeshipId);

        if (OrganizationValidation.isOwner(organization, traineeship)
                || OrganizationValidation.isReceiver(organization, exclusiveReceivers)) {
            return traineeship;
        }

        var errorMessage = "Organizations can only see traineeships which they own or receive";
        throw new UnauthorizedAccessException(errorMessage);
    }

    public JobPrivateDetailsProjection getOnePrivateDetails(String organizationId, String traineeshipId) {
        var organization = organizationDB.findById(organizationId);
        var job = jobDB.getPrivateDetails(traineeshipId);

        if (OrganizationValidation.isOwner(organization, job)) {
            return job;
        }

        var errorMessage = "Organizations can only see private details of traineeships which they own";
        throw new UnauthorizedAccessException(errorMessage);
    }


    public Page<JobPrivateSummaryProjection> getAllCreatedSummary(String loggedId, String targetId) {
        if(isSelf(loggedId, targetId))
            return jobDB.getAllCreatedJobsSummaryFromOrg(targetId);
        var errorMessage = "Organizations can only access their own created jobs";
        throw new UnauthorizedAccessException(errorMessage);
    }

    public List<JobPublicSummaryProjection> getAllApprovedSummary(String loggedId, String targetId) {
        var org = organizationDB.findById(loggedId);
        if(isSelf(loggedId, targetId) && isIE(org))
            return jobDB.getAllApprovedSummaryFromOrg(loggedId);
        throw new UnauthorizedAccessException("User not authorized to access these resources because is not self OR is not IE");
    }

    public List<JobPublicSummaryProjection> getAllRejectedSummary(String loggedId, String targetId) {
        var org = organizationDB.findById(loggedId);
        if(isSelf(loggedId, targetId) && isIE(org))
            return jobDB.getAllRejectedSummaryFromOrg(loggedId);
        throw new UnauthorizedAccessException("User not authorized to access these resources because is not self OR is not IE");
    }


    public List<JobPublicSummaryProjection> getAllPendingSummary(String loggedId, String targetId) {
        var org = organizationDB.findById(loggedId);
        if(isSelf(loggedId, targetId) && isIE(org))
            return jobDB.getAllPendingSummaryFromOrg(loggedId);
        throw new UnauthorizedAccessException("User not authorized to access these resources because is not self OR is not IE");
    }


    // TODO DB: Aqui tbm tem lógica de negócio... Isto é: são as vagas que ainda não foram aprovadas ou rejeitadas e são "recebidas"
    //  TODO DB: Jogar esta lógica lá para o banco de dados...
    public List<IJob> getAllReceivedSummary(IOrganization org) {
        if(OrganizationValidation.isIE(org)) {
            var received = new ArrayList<IJob>();
            var exclusiveJobs = jobDB.getExclusiveReceivedJobsSummaryForOrg(org.getId());
            var publicJobs = jobDB.findAllPublicJobsSummary();
            received.addAll(exclusiveJobs);
            received.addAll(publicJobs);
            return received;
        }
        throw new UnauthorizedAccessException("Must be IE to get received jobs");
    }


    public List<JobPublicSummaryProjection> getAllAvailableSummary(String loggedId, String targetId) {
        var org = organizationDB.findById(loggedId);
        if(isSelf(loggedId, targetId) && isIE(org))
            return jobDB.getAllAvailableSummaryFromOrg(loggedId);
        throw new UnauthorizedAccessException("User not authorized to access these resources because is not self OR is not IE");
    }


    public ModerationProjection getModerationInfo(String orgId, String jobId) {
        var org = organizationDB.findById(orgId);
        if(isIE(org)) {
            try{
                return jobDB.getModerationInfo(orgId, jobId);
            }
            catch (EntityNotFoundException enf) {
                var job = jobDB.getBasicInfo(jobId);
                return Moderation.resolve(org, job);
            }
        }
        var errorMessage = "Only IEs can see moderation info";
        throw new InvalidReceiverException(errorMessage);
    }

}
