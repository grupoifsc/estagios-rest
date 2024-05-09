package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.view.*;
import com.github.projetoifsc.estagios.app.service.handler.RequestHandlerChain;
import com.github.projetoifsc.estagios.app.utils.mock.OrgMock;
import com.github.projetoifsc.estagios.app.utils.mock.VagaMock;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class VagaService {

    ModelMapper mapper = new ModelMapper();

    RequestHandlerChain requestHandlerChain = new RequestHandlerChain();

    PagedResourcesAssembler<VagaPublicProfileSerializableView> pageAssembler = new PagedResourcesAssembler<VagaPublicProfileSerializableView>(null, null);



    public ResponseEntity<VagaPrivateProfileSerializableView> create(VagaPrivateProfileSerializableView vaga) {

        requestHandlerChain.handle(vaga);

        vaga.setId("123");
        vaga.setOwner(OrgMock.getOne());

        var mapped = mapper.map
                (vaga, VagaPrivateProfileSerializableView.class);

        return new ResponseEntity<>(
                mapped,
                HttpStatus.CREATED );
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


    public ResponseEntity<VagaPrivateProfileSerializableView> update(String vagaId, VagaPrivateProfileSerializableView vaga) {
        requestHandlerChain.handle(vaga);

        vaga.setId("123");
        vaga.setOwner(OrgMock.getOne());
        var mapped = mapper.map(
                vaga, VagaPrivateProfileSerializableView.class
        );
        return new ResponseEntity<VagaPrivateProfileSerializableView> (
                mapped,
                HttpStatus.OK );
    }


    public ResponseEntity<VagaPrivateProfileSerializableView> delete(String vagaId) {
        return new ResponseEntity<VagaPrivateProfileSerializableView> (
                HttpStatus.NO_CONTENT );
    }


    public ResponseEntity<VagaPublicProfileSerializableView> getPublicProfile(String vagaId) {
        var vaga = VagaMock.getOne();
        var mapped = mapper.map(
                vaga,
                VagaPublicProfileSerializableView.class
        );
        return new ResponseEntity<>(
                mapped,
                HttpStatus.OK
        );
    }


    public ResponseEntity<VagaPrivateProfileSerializableView> getPrivateProfile(String vagaId) {
        var vaga = VagaMock.getOne();
        var mapped = mapper.map(
                vaga,
                VagaPrivateProfileSerializableView.class
        );
        return new ResponseEntity<> (
                mapped,
                HttpStatus.OK );
    }


    public ResponseEntity<Page<VagaPublicProfileSerializableView>> getAllReceivedByUser(String id, HashMap<String, String> filterArgs) {
        var vagas = VagaMock.getList();
        var vagasDto = vagas.stream().map(vaga -> mapper.map(
                vaga,
                VagaPublicProfileSerializableView.class
        )).toList();
        var pageImpl = new PageImpl<>(vagasDto);
        return new ResponseEntity<> (
                pageImpl,
                HttpStatus.OK );
    }


    public ResponseEntity<Page<VagaPrivateProfileSerializableView>> getAllCreatedByUser(String id, Integer page, Integer limit) {
        var vagas = VagaMock.getList();
        var vagasDTO = vagas.stream().map(vaga -> mapper.map(
                vaga,
                VagaPrivateProfileSerializableView.class
        )).toList();
        var pageImpl = new PageImpl<>(vagasDTO);

        return new ResponseEntity<> (
                pageImpl,
                HttpStatus.OK );
    }


    public ResponseEntity<Page<OrgBasicView>> getVagaRecipients(String id) {
        var users = OrgMock.getList();
        var usersDTO = users.stream().map(user -> mapper.map(
                user,
                OrgBasicView.class
        )).toList();
        var pageImpl = new PageImpl<>(usersDTO);

        return new ResponseEntity<> (
                pageImpl,
                HttpStatus.OK );
    }

}
