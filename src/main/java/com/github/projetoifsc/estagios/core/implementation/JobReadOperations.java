package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IOrganizationDAO;
import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.core.IJobDAO;
import org.springframework.data.domain.Page;

import static com.github.projetoifsc.estagios.core.implementation.OrganizationValidation.isSelf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class JobReadOperations {

    private final IJobDAO jobDB;
    private final IOrganizationDAO organizationDB;

    public JobReadOperations(IJobDAO jobDB, IOrganizationDAO organizationDB) {
        this.jobDB = jobDB;
        this.organizationDB = organizationDB;
    }


    public Page<IJob> getAllCreatedSummary(String loggedId, String targetId) {
        if(isSelf(loggedId, targetId))
            return organizationDB.getAllCreatedJobsSummaryFromOrg(targetId);
        var errorMessage = "Organizations can only access their own created traineeships";
        throw new UnauthorizedAccessException(errorMessage);
    }


    public List<IJob> getAllReceivedSummary(String loggedId, String targetId) {
        if(isSelf(loggedId, targetId)) {
            Set<IJob> set = new HashSet<>();
            if(organizationDB.findById(loggedId).getIe()) {
                var created = organizationDB.getAllCreatedJobsSummaryFromOrg(loggedId).stream().toList();
                set.addAll(created);
            }
            var exclusivelyReceived = organizationDB.getExclusiveReceivedJobsSummaryForOrg(targetId);
            set.addAll(exclusivelyReceived);
            var globallyReceived = jobDB.findAllPublicJobs();
            set.addAll(globallyReceived);
            return new ArrayList<>(set);
        }
        var errorMessage = "Organizations can only access their own created traineeships";
        throw new UnauthorizedAccessException(errorMessage);
    }


    public IJob getOnePrivateDetails(String organizationId, String traineeshipId) {
        var organization = organizationDB.findById(organizationId);
        var job = jobDB.getPrivateDetails(traineeshipId);

        if (OrganizationValidation.isOwner(organization, job)) {
            return job;
        }

        var errorMessage = "Organizations can only see private details of traineeships which they own";
        throw new UnauthorizedAccessException(errorMessage);
    }


    public IJob getOnePublicDetails(String organizationId, String traineeshipId) {
        var organization = organizationDB.findById(organizationId);
        var traineeship = jobDB.getBasicInfoById(traineeshipId);

        var exclusiveReceivers = jobDB.getExclusiveReceiversForJob(traineeshipId);

        if (OrganizationValidation.isOwner(organization, traineeship)
                || OrganizationValidation.isReceiver(organization, exclusiveReceivers)) {
            return jobDB.getPublicDetails(traineeshipId);
        }

        var errorMessage = "Organizations can only see traineeships which they own or receive";
        throw new UnauthorizedAccessException(errorMessage);
    }


    public List<IJob> getAllApprovedSummary(String loggedId, String targetId) {
        if(isSelf(loggedId, targetId))
            return jobDB.getAllApprovedSummaryByOrg(loggedId);
        throw new UnauthorizedAccessException("User not authorized to access these resources because is not self");
    }

}
