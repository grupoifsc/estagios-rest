package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.model.request.NewJobRequest;
import com.github.projetoifsc.estagios.app.model.response.PrivateJobDetailsResponse;
import com.github.projetoifsc.estagios.app.model.response.PrivateJobSummaryResponse;
import com.github.projetoifsc.estagios.app.model.response.PublicJobDetailsResponse;
import com.github.projetoifsc.estagios.app.model.response.PublicJobSummaryResponse;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.core.IJobUseCases;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaService {

    private final Mapper mapper;
    private final IJobUseCases jobUseCases;

    public VagaService(Mapper mapper, IJobUseCases jobUseCases) {
        this.mapper = mapper;
        this.jobUseCases = jobUseCases;
    }


    public PrivateJobDetailsResponse create(UserPrincipal userPrincipal, NewJobRequest newJobRequest) {
        var savedVaga = jobUseCases.create(userPrincipal.getId(), newJobRequest);
        return mapper.map(
                savedVaga,
                PrivateJobDetailsResponse.class
        );
    }


    public PrivateJobDetailsResponse update(UserPrincipal userPrincipal, String vagaId, NewJobRequest newJobRequest) {
        var updatedVaga = jobUseCases.update(userPrincipal.getId(), vagaId, newJobRequest);
        return mapper.map(
                updatedVaga,
                PrivateJobDetailsResponse.class
        );
    }


    public void delete(UserPrincipal userPrincipal, String vagaId) {
        jobUseCases.delete(userPrincipal.getId(), vagaId);
    }


    public PublicJobDetailsResponse getPublicProfile(UserPrincipal userPrincipal, String vagaId) {
        var vaga = jobUseCases.getOnePublicDetails(userPrincipal.getId(), vagaId);
        return mapper.map(
                vaga,
                PublicJobDetailsResponse.class
        );
    }


    public PrivateJobDetailsResponse getPrivateProfile(UserPrincipal userPrincipal, String vagaId) {
        var vaga = jobUseCases.getOnePrivateDetails(userPrincipal.getId(), vagaId);
        return mapper.map(
                vaga,
                PrivateJobDetailsResponse.class
        );
    }


    public Page<PrivateJobSummaryResponse> getAuthUserCreatedJobs(UserPrincipal userPrincipal, Integer page, Integer limit) {
        var vagas = jobUseCases.getAllCreatedSummary(userPrincipal.getId(), userPrincipal.getId());
        return vagas.map(vaga -> mapper.map(
                vaga,
                PrivateJobSummaryResponse.class
        ));
    }


    public Page<PublicJobSummaryResponse> getAuthUserAvailableJobs(UserPrincipal userPrincipal, Integer page, Integer limit) {
        var available = jobUseCases.getAllAvailableSummary(userPrincipal.getId(), userPrincipal.getId());
        var mapped = available
                .stream().map(job -> mapper.map(job, PublicJobSummaryResponse.class)).toList();
        return new PageImpl<>(mapped);
    }


    public Page<PublicJobSummaryResponse> getAuthUserRejectedJobs(UserPrincipal userPrincipal, Integer page, Integer limit) {
        var rejected = jobUseCases.getAllRejectedSummary(userPrincipal.getId(), userPrincipal.getId());
        var mapped = rejected
                .stream().map(job -> mapper.map(job, PublicJobSummaryResponse.class)).toList();
        return new PageImpl<>(mapped);
    }

    public Page<PublicJobSummaryResponse> getAuthUserPendingJobs(UserPrincipal userPrincipal, Integer page, Integer limit) {
        var pending = jobUseCases.getAllPendingSummary(userPrincipal.getId(), userPrincipal.getId());
        var mapped = pending
                .stream().map(job -> mapper.map(job, PublicJobSummaryResponse.class)).toList();
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
