package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.model.request.NewVagaRequest;
import com.github.projetoifsc.estagios.app.model.response.VagaPrivateDetailsView;
import com.github.projetoifsc.estagios.app.model.response.VagaPrivateSummaryView;
import com.github.projetoifsc.estagios.app.model.response.VagaPublicDetailsView;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.core.IJobUseCases;
import com.github.projetoifsc.estagios.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class VagaService {

    private final Mapper mapper;
    private final IJobUseCases jobUseCases;


    @Autowired
    public VagaService(Mapper mapper, IJobUseCases jobUseCases) {
        this.mapper = mapper;
        this.jobUseCases = jobUseCases;
    }


    public VagaPrivateDetailsView create(UserPrincipal userPrincipal, NewVagaRequest newVagaRequest) {
        var savedVaga = jobUseCases.create(userPrincipal.getId(), newVagaRequest);
        return mapper.map(
                savedVaga,
                VagaPrivateDetailsView.class
        );
    }


    public VagaPrivateDetailsView update(UserPrincipal userPrincipal, String vagaId, NewVagaRequest newVagaRequest) {
        var updatedVaga = jobUseCases.update(userPrincipal.getId(), vagaId, newVagaRequest);
        return mapper.map(
                updatedVaga,
                VagaPrivateDetailsView.class
        );
    }


    public void delete(UserPrincipal userPrincipal, String vagaId) {
        jobUseCases.delete(userPrincipal.getId(), vagaId);
    }


    public VagaPublicDetailsView getPublicProfile(UserPrincipal userPrincipal, String vagaId) {
        var vaga = jobUseCases.getOnePublicDetails(userPrincipal.getId(), vagaId);
        return mapper.map(
                vaga,
                VagaPublicDetailsView.class
        );
    }


    public VagaPrivateDetailsView getPrivateProfile(UserPrincipal userPrincipal, String vagaId) {
        var vaga = jobUseCases.getOnePrivateDetails(userPrincipal.getId(), vagaId);
        return mapper.map(
                vaga,
                VagaPrivateDetailsView.class
        );
    }


     public List<VagaPublicDetailsView> getAllReceivedByUser(UserPrincipal userPrincipal, String targetUserId, HashMap<String, String> filterArgs) {
        var vagas = jobUseCases.getAllReceivedSummary(userPrincipal.getId(), targetUserId);
        return vagas.stream().map(vaga -> mapper.map(
                vaga,
                VagaPublicDetailsView.class
        )).toList();
    }


    public Page<VagaPrivateSummaryView> getAllCreatedByUser(UserPrincipal userPrincipal, String targetUserId, Integer page, Integer limit) {
        var vagas = jobUseCases.getAllCreatedSummary(userPrincipal.getId(), targetUserId);
        return vagas.map(vaga -> mapper.map(
                vaga,
                VagaPrivateSummaryView.class
        ));
    }


}
