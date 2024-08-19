package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.core.*;
import com.github.projetoifsc.estagios.core.models.IJob;
import com.github.projetoifsc.estagios.core.models.projections.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;

import static com.github.projetoifsc.estagios.core.implementation.OrganizationValidation.*;

import java.util.List;

class JobReadOperations {

    private final IJobDAO jobDB;
    private final IOrganizationDAO organizationDB;
    private final JsonParser jsonParser = new JsonParser();

    public JobReadOperations(IJobDAO jobDB, IOrganizationDAO organizationDB) {
        this.jobDB = jobDB;
        this.organizationDB = organizationDB;
    }


    public JobPublicDetailsProjection getOnePublicDetails(String organizationId, String traineeshipId) {
        var organization = organizationDB.findByIdSummaryProjection(organizationId);
        var jobBasicProjection = jobDB.getJobSummary(traineeshipId);

        if (OrganizationValidation.isOwner(organization, jobBasicProjection)
                || OrganizationValidation.isReceiver(organization, jobBasicProjection)) {
            return jobDB.getJobPublicDetails(traineeshipId);
        }

        var errorMessage = "Organizations can only see traineeships which they own or receive";
        throw new UnauthorizedAccessException(errorMessage);
    }

    public JobPrivateDetailsProjection getOnePrivateDetails(String organizationId, String traineeshipId) {
        var organization = organizationDB.findByIdSummaryProjection(organizationId);
        var job = jobDB.getJobPrivateDetails(traineeshipId);
        if (OrganizationValidation.isOwner(organization, job)) {
            return job;
        }
        var errorMessage = "Organizations can only see private details of traineeships which they own";
        throw new UnauthorizedAccessException(errorMessage);
    }


    public Page<JobPrivateDetailsProjection> getAllCreatedDetails(String loggedId, String targetId) {
        if(isSelf(loggedId, targetId))
            return jobDB.getAllCreatedBy(targetId);
        var errorMessage = "Organizations can only access their own created jobs";
        throw new UnauthorizedAccessException(errorMessage);
    }

    public Page<JobPrivateDetailsProjection> getAllCreatedDetailsWithModeration(String loggedId, String targetId, int page, int limit) {
        if(isSelf(loggedId, targetId))
            return jobDB.getAllCreatedByWithPagination(targetId, page, limit);
        var errorMessage = "Organizations can only access their own created jobs";
        throw new UnauthorizedAccessException(errorMessage);
    }


    public Page<JobPublicDetailsProjection> getAllRejected(String loggedId, String targetId) {
        var org = organizationDB.findByIdSummaryProjection(loggedId);
        if(isSelf(loggedId, targetId) && isIE(org))
            return jobDB.getAllRejectedBy(loggedId);
        throw new UnauthorizedAccessException("User not authorized to access these resources because is not self OR is not IE");
    }


    public Page<JobPublicDetailsProjection> getAllPending(String loggedId, String targetId) {
        var org = organizationDB.findByIdSummaryProjection(loggedId);
        if(isSelf(loggedId, targetId) && isIE(org))
            return jobDB.getAllToBeModeratedBy(loggedId);
        throw new UnauthorizedAccessException("User not authorized to access these resources because is not self OR is not IE");
    }


//    // TODO DB: Aqui tbm tem lógica de negócio... Isto é: são as vagas que ainda não foram aprovadas ou rejeitadas e são "recebidas"
//    //  TODO DB: Jogar esta lógica lá para o banco de dados...
//    public List<JobPublicDetailsProjection> getAllReceivedSummary(IOrg org) {
//        if(OrganizationValidation.isIE(org)) {
//            var received = new ArrayList<JobPublicDetailsProjection>();
//            var exclusiveJobs = jobDB.getExclusiveReceivedJobsSummaryForOrg(org.getId());
//            var publicJobs = jobDB.findAllPublicJobsSummary();
//            received.addAll(exclusiveJobs);
//            received.addAll(publicJobs);
//            return received;
//        }
//        throw new UnauthorizedAccessException("Must be IE to get received jobs");
//    }


    public Page<JobPublicDetailsProjection> getAllAvailable(String loggedId, String targetId) {
        var org = organizationDB.findByIdSummaryProjection(loggedId);
        if(isSelf(loggedId, targetId) && isIE(org))
            return jobDB.getAllCreatedOrApprovedBy(loggedId);
        throw new UnauthorizedAccessException("User not authorized to access these resources because is not self OR is not IE");
    }


    public ModerationDetailsProjection getModerationInfo(String orgId, String jobId) {
        var org = organizationDB.findByIdSummaryProjection(orgId);
        return this.getModerationInfo(org, jobId);
    }

    public ModerationDetailsProjection getModerationInfo(OrgSummaryProjection org, String jobId) {
        if(isIE(org)) {
            return jobDB.getModerationInfo(org.getId(), jobId);
        }
        var errorMessage = "Only IEs can see moderation info";
        throw new InvalidReceiverException(errorMessage);
    }

    public IJob getOneDetails(String organizationId, String traineeshipId) {
        var organization = organizationDB.findByIdSummaryProjection(organizationId);
        var jobBasicProjection = jobDB.getJobSummary(traineeshipId);
        if (OrganizationValidation.isOwner(organization, jobBasicProjection)) {
            return jobDB.getJobPrivateDetails(traineeshipId);
        }
        if (OrganizationValidation.isReceiver(organization, jobBasicProjection)) {
            return jobDB.getJobPublicDetails(traineeshipId);
        }
        var errorMessage = "Organizations can only see traineeships which they own or receive";
        throw new UnauthorizedAccessException(errorMessage);
    }


    public JobPublicDetailsProjection getOnePublicDetailsWithMod(String organizationId, String traineeshipId) {
        var organization = organizationDB.findByIdSummaryProjection(organizationId);
        var jobBasicProjection = jobDB.getJobSummary(traineeshipId);
        JobPublicDetailsProjection job;
        if (OrganizationValidation.isOwner(organization, jobBasicProjection)
                || OrganizationValidation.isReceiver(organization, jobBasicProjection)) {
            job = jobDB.getJobPublicDetails(traineeshipId);
        }
        else {
            var errorMessage = "Organizations can only see traineeships which they own or receive";
            throw new UnauthorizedAccessException(errorMessage);
        }
        var moderationInfo = this.getModerationInfo(organization, jobBasicProjection.getId());
        moderationInfo = ModerationResolver.resolve(organization, jobBasicProjection, moderationInfo);
        job.setModerationDetail(moderationInfo);
        return job;
    }


    public Page<JobPublicDetailsProjection> getAllReceivedWithPagination(String loggedId, String targetId, int page, int limit) {
        var org = organizationDB.findByIdSummaryProjection(loggedId);
        if(isSelf(loggedId, targetId) && isIE(org))
            return jobDB.getAllReceivedByOrgPaginated(loggedId, page, limit);
        throw new UnauthorizedAccessException("User not authorized to access these resources because is not self OR is not IE");
    }

}
