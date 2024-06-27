package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.model.response.JobPrivateDetails;
import com.github.projetoifsc.estagios.app.model.response.JobPublicDetails;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.core.IJobUseCases;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import com.github.projetoifsc.estagios.core.models.IJobEntryData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
        jsonParser.printValue(savedVaga);
        var mapped =  mapper.map(
                savedVaga,
                JobPrivateDetails.class
        );
        jsonParser.printValue(mapped);
        return mapped;
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


    public JobPublicDetails getPublicProfile(UserPrincipal userPrincipal, String vagaId) {
        var vaga = jobUseCases.getOnePublicDetails(userPrincipal.getId(), vagaId);
        return mapper.map(
                vaga,
                JobPublicDetails.class
        );
    }


    public JobPrivateDetails getPrivateProfile(UserPrincipal userPrincipal, String vagaId) {
        var vaga = jobUseCases.getOnePrivateDetails(userPrincipal.getId(), vagaId);
        return mapper.map(
                vaga,
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


    public Page<JobPublicDetails> getAuthUserAvailableJobs(UserPrincipal userPrincipal, Integer page, Integer limit) {
        var available = jobUseCases.getAllAvailableSummary(userPrincipal.getId(), userPrincipal.getId());
        var mapped = available
                .stream().map(job -> mapper.map(job, JobPublicDetails.class)).toList();
        return new PageImpl<>(mapped);
    }


    public Page<JobPublicDetails> getAuthUserRejectedJobs(UserPrincipal userPrincipal, Integer page, Integer limit) {
        var rejected = jobUseCases.getAllRejectedSummary(userPrincipal.getId(), userPrincipal.getId());
        var mapped = rejected
                .stream().map(job -> mapper.map(job, JobPublicDetails.class)).toList();
        return new PageImpl<>(mapped);
    }

    public Page<JobPublicDetails> getAuthUserPendingJobs(UserPrincipal userPrincipal, Integer page, Integer limit) {
        var pending = jobUseCases.getAllPendingSummary(userPrincipal.getId(), userPrincipal.getId());
        var mapped = pending
                .stream().map(job -> mapper.map(job, JobPublicDetails.class)).toList();
        return new PageImpl<>(mapped);
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
