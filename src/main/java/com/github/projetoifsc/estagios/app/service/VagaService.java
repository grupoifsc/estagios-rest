package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.model.request.NewVagaRequest;
import com.github.projetoifsc.estagios.app.model.response.VagaPrivateDetailsView;
import com.github.projetoifsc.estagios.app.model.response.VagaPrivateSummaryView;
import com.github.projetoifsc.estagios.app.model.response.VagaPublicDetailsView;
import com.github.projetoifsc.estagios.app.service.handler.RequestHandlerChain;
import com.github.projetoifsc.estagios.core.IJobUseCases;
import com.github.projetoifsc.estagios.core.IOrganizationUseCases;
import com.github.projetoifsc.estagios.utils.JsonParser;
import com.github.projetoifsc.estagios.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class VagaService {

    Mapper mapper;
    IJobUseCases jobUseCases;
    IOrganizationUseCases organizationUseCases;

    JsonParser jsonParser = new JsonParser();

    RequestHandlerChain requestHandlerChain = new RequestHandlerChain();

    PagedResourcesAssembler<VagaPublicDetailsView> pageAssembler = new PagedResourcesAssembler<VagaPublicDetailsView>(null, null);


    @Autowired
    public VagaService(Mapper mapper, IJobUseCases jobUseCases, IOrganizationUseCases organizationUseCases) {
        this.mapper = mapper;
        this.jobUseCases = jobUseCases;
        this.organizationUseCases = organizationUseCases;
    }


    public VagaPrivateDetailsView create(NewVagaRequest vaga) {
        //requestHandlerChain.handle(vaga);
        var created = jobUseCases.create("198", vaga);
        return mapper.map(
                created,
                VagaPrivateDetailsView.class
        );
    }


//    public ResponseEntity<PagedModel<EntityModel<VagaPublicProfileDTO>>> search(HashMap<String, String> stringArgs, HashMap<String, Integer> numericArgs) {
//        var vagas = VagaMock.getList();
//        var vagasDto = vagas.stream().map(vaga -> mapper.map(
//                vaga,
//                VagaPublicProfileDTO.class
//        )).toList();
//
//        var pageImpl = new PageImpl<>(vagasDto);
//
//        var paged = pageAssembler.toModel(pageImpl, Link.of("jhjhhk"));
//
//
//        return new ResponseEntity<> (
//                paged,
//                HttpStatus.OK );
//
//    }


    public VagaPrivateDetailsView update(String vagaId, NewVagaRequest vaga) {
        //requestHandlerChain.handle(vaga);
        var created = jobUseCases.update("198", vagaId, vaga);
        return mapper.map(
                created,
                VagaPrivateDetailsView.class
        );
    }


    public void delete(String vagaId) {
        jobUseCases.delete("195", vagaId);
    }


    public VagaPublicDetailsView getPublicProfile(String vagaId) {
        var vaga = jobUseCases.getOnePublicDetails("195", vagaId);
        return mapper.map(
                vaga,
                VagaPublicDetailsView.class
        );
    }


    public VagaPrivateDetailsView getPrivateProfile(String vagaId) {
        var vaga = jobUseCases.getOnePrivateDetails("198", vagaId);
        return mapper.map(
                vaga,
                VagaPrivateDetailsView.class
        );
    }

    // TODO: Melhorar a apresentação
    public List<VagaPublicDetailsView> getAllReceivedByUser(String id, HashMap<String, String> filterArgs) {
        var vagas = jobUseCases.getAllReceivedSummary(id, id);
        return vagas.stream().map(vaga -> mapper.map(
                vaga,
                VagaPublicDetailsView.class
        )).toList();
    }


    public Page<VagaPrivateSummaryView> getAllCreatedByUser(String id, Integer page, Integer limit) {
        var vagas = jobUseCases.getAllCreatedSummary(id, id);
        return vagas.map(vaga -> mapper.map(
                vaga,
                VagaPrivateSummaryView.class
        ));
    }


//    public ResponseEntity<Page<OrgBasicView>> getVagaRecipients(String id) {
//        var users = organizationUseCases.get
//        var usersDTO = users.stream().map(user -> mapper.map(
//                user,
//                OrgBasicView.class
//        )).toList();
//        var pageImpl = new PageImpl<>(usersDTO);
//
//        return new ResponseEntity<> (
//                pageImpl,
//                HttpStatus.OK );
//    }

}
