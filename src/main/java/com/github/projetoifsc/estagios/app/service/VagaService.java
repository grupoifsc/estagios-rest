package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.dto.*;
import com.github.projetoifsc.estagios.app.service.handler.RequestHandlerChain;
import com.github.projetoifsc.estagios.app.utils.mock.UserMock;
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

    PagedResourcesAssembler<VagaPublicProfileDTO> pageAssembler = new PagedResourcesAssembler<VagaPublicProfileDTO>(null, null);



    public ResponseEntity<VagaPrivateProfileDTO> create(VagaPrivateProfileDTO vaga) {

        requestHandlerChain.handle(vaga);

        vaga.setId("123");
        vaga.setOwner(UserMock.getOne());

        var mapped = mapper.map
                (vaga, VagaPrivateProfileDTO.class);

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


    public ResponseEntity<VagaPrivateProfileDTO> update(String vagaId, VagaPrivateProfileDTO vaga) {
        requestHandlerChain.handle(vaga);

        vaga.setId("123");
        vaga.setOwner(UserMock.getOne());
        var mapped = mapper.map(
                vaga, VagaPrivateProfileDTO.class
        );
        return new ResponseEntity<VagaPrivateProfileDTO> (
                mapped,
                HttpStatus.OK );
    }


    public ResponseEntity<VagaPrivateProfileDTO> delete(String vagaId) {
        return new ResponseEntity<VagaPrivateProfileDTO> (
                HttpStatus.NO_CONTENT );
    }


    public ResponseEntity<VagaPublicProfileDTO> getPublicProfile(String vagaId) {
        var vaga = VagaMock.getOne();
        var mapped = mapper.map(
                vaga,
                VagaPublicProfileDTO.class
        );
        return new ResponseEntity<>(
                mapped,
                HttpStatus.OK
        );
    }


    public ResponseEntity<VagaPrivateProfileDTO> getPrivateProfile(String vagaId) {
        var vaga = VagaMock.getOne();
        var mapped = mapper.map(
                vaga,
                VagaPrivateProfileDTO.class
        );
        return new ResponseEntity<> (
                mapped,
                HttpStatus.OK );
    }


    public ResponseEntity<Page<VagaPublicProfileDTO>> getAllReceivedByUser(String id, HashMap<String, String> filterArgs) {
        var vagas = VagaMock.getList();
        var vagasDto = vagas.stream().map(vaga -> mapper.map(
                vaga,
                VagaPublicProfileDTO.class
        )).toList();
        var pageImpl = new PageImpl<>(vagasDto);
        return new ResponseEntity<> (
                pageImpl,
                HttpStatus.OK );
    }


    public ResponseEntity<Page<VagaPrivateProfileDTO>> getAllCreatedByUser(String id, Integer page, Integer limit) {
        var vagas = VagaMock.getList();
        var vagasDTO = vagas.stream().map(vaga -> mapper.map(
                vaga,
                VagaPrivateProfileDTO.class
        )).toList();
        var pageImpl = new PageImpl<>(vagasDTO);

        return new ResponseEntity<> (
                pageImpl,
                HttpStatus.OK );
    }


    public ResponseEntity<Page<UserDTO>> getVagaRecipients(String id) {
        var users = UserMock.getList();
        var usersDTO = users.stream().map(user -> mapper.map(
                user,
                UserDTO.class
        )).toList();
        var pageImpl = new PageImpl<>(usersDTO);

        return new ResponseEntity<> (
                pageImpl,
                HttpStatus.OK );
    }

}
