package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.model.response.JobPrivateDetails;
import com.github.projetoifsc.estagios.app.model.response.JobPublicDetails;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.core.IJobUseCases;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import com.github.projetoifsc.estagios.core.models.IJobEntryData;
import com.github.projetoifsc.estagios.core.models.projections.JobPrivateDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.JobPublicDetailsProjection;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaService {

    private final Mapper mapper;
    private final IJobUseCases jobUseCases;
    private final JsonParser jsonParser;

    public VagaService(Mapper mapper, IJobUseCases jobUseCases, JsonParser jsonParser) {
        this.mapper = mapper;
        this.jobUseCases = jobUseCases;
        this.jsonParser = jsonParser;
    }


    public JobPrivateDetails create(UserPrincipal userPrincipal, IJobEntryData newJobRequest) {
        var savedVaga = jobUseCases.create(userPrincipal.getId(), newJobRequest);
        return mapper.map(
                savedVaga,
                JobPrivateDetails.class
        );
    }


    public JobPrivateDetails update(UserPrincipal userPrincipal, String vagaId, IJobEntryData newJobRequest) {
        var updatedVaga = jobUseCases.update(userPrincipal.getId(), vagaId, newJobRequest);
        return mapper.map(
                updatedVaga,
                JobPrivateDetails.class
        );
    }


    public void delete(UserPrincipal userPrincipal, String vagaId) {
        jobUseCases.delete(userPrincipal.getId(), vagaId);
    }

    public JobPublicDetails getJobDetails(UserPrincipal userPrincipal, String vagaId) {
        var vaga = jobUseCases.getOneDetails(userPrincipal.getId(), vagaId);
        if(vaga instanceof JobPrivateDetailsProjection) {
            return mapper.map(vaga,
                JobPrivateDetails.class);
        }
        if(vaga instanceof JobPublicDetailsProjection) {
            return mapper.map(vaga,
                JobPublicDetails.class);
        }
        return null;
    }

    public JobPublicDetails getPublicProfile(UserPrincipal userPrincipal, String vagaId) {
        var vaga = jobUseCases.getOnePublicDetails(userPrincipal.getId(), vagaId);
        return mapper.map(vaga,
                JobPublicDetails.class);
    }


    public JobPublicDetails getPublicProfileWithModerationStatus(UserPrincipal userPrincipal, String vagaId) {
        var vaga = jobUseCases.getOnePublicDetailsWithMod(userPrincipal.getId(), vagaId);
        return mapper.map(vaga,
                JobPublicDetails.class);
    }


    public JobPrivateDetails getPrivateProfile(UserPrincipal userPrincipal, String vagaId) {
        var vaga = jobUseCases.getOnePrivateDetails(userPrincipal.getId(), vagaId);
        return mapper.map(vaga,
                JobPrivateDetails.class
        );
    }

    public Page<JobPrivateDetails> getAuthUserCreatedJobs(UserPrincipal userPrincipal, Integer page, Integer limit) {
        var vagas = jobUseCases.getAllCreatedDetails(userPrincipal.getId(), userPrincipal.getId());
        return vagas.map(vaga -> mapper.map(
                vaga,
                JobPrivateDetails.class
        ));
    }

    public Page<JobPrivateDetails> getAuthUserCreatedJobsPaginated(UserPrincipal userPrincipal, String search, Integer page, Integer limit) {
        var vagas = jobUseCases.getAllCreatedDetailsWithPagination(userPrincipal.getId(), userPrincipal.getId(), search, page, limit);
        return vagas.map(vaga -> mapper.map(
                vaga,
                JobPrivateDetails.class
        ));
    }

    public Page<JobPublicDetails> getAuthUserReceivedJobs(UserPrincipal userPrincipal, String search, Integer page, Integer limit) {
        var received = jobUseCases.getAllReceivedWithPagination(userPrincipal.getId(), userPrincipal.getId(), search, page, limit);
        return received.map(job -> mapper.map(
                job,
                JobPublicDetails.class
        ));
    }

    public Page<JobPublicDetails> getAuthUserAvailableJobs(UserPrincipal userPrincipal, String search, Integer page, Integer limit) {
        var available = jobUseCases.getAllAvailableWithPagination(userPrincipal.getId(), userPrincipal.getId(), search, page, limit);
        return available.map(job -> mapper.map(
                job,
                JobPublicDetails.class
        ));
    }


    public Page<JobPublicDetails> getAuthUserRejectedJobs(UserPrincipal userPrincipal, Integer page, Integer limit) {
        var rejected = jobUseCases.getAllRejected(userPrincipal.getId(), userPrincipal.getId());
        return rejected.map(job -> mapper.map(
                job,
                JobPublicDetails.class
        ));
    }


    public Page<JobPublicDetails> getAuthUserPendingJobs(UserPrincipal userPrincipal, Integer page, Integer limit) {
        var pending = jobUseCases.getAllPending(userPrincipal.getId(), userPrincipal.getId());
        return pending.map(job -> mapper.map(
                job,
                JobPublicDetails.class
        ));
    }


    public void approve(UserPrincipal userPrincipal, List<String> jobIds) {
        jobUseCases.approve(userPrincipal.getId(), jobIds);
        //return mapper.map(approved, PublicJobSummaryResponse.class);
    }

    public void reject(UserPrincipal userPrincipal, List<String> jobIds) {
        jobUseCases.reject(userPrincipal.getId(), jobIds);
        //return mapper.map(rejected, PublicJobSummaryResponse.class);
    }

}
