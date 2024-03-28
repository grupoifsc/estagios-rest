package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.dto.OrgDTO;
import com.github.projetoifsc.estagios.app.dto.OrgPrivateProfileDTO;
import com.github.projetoifsc.estagios.app.dto.OrgPublicProfileDTO;
import com.github.projetoifsc.estagios.app.service.handler.RequestHandlerChain;
import com.github.projetoifsc.estagios.app.utils.mock.OrgMock;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


// TODO:
// Chain Of Responsibility:
// UserRequestHandler()
// a) Valida dados de autenticação
// b) Autentica
// c) Autoriza
// d) Valida dados de entrada
// e) Tira o HATEOAS
// Fim do Handler
// f) Chama outra camada (AQUI ENTRA CÓDIGO ESPECÍFICO)
// g) Aplica HATEOAS na resposta (TBM EH ESPECÍFICO)
// h) Devolve a resposta ao Controller


import java.util.List;


@Service
public class OrgService {

    ModelMapper mapper = new ModelMapper();
    RequestHandlerChain requestHandlerChain = new RequestHandlerChain();


    public ResponseEntity<OrgPrivateProfileDTO> create(OrgPrivateProfileDTO perfil) {

        requestHandlerChain.handle(perfil);

        perfil.setId("111");
        var userDTO = mapper.map(
                perfil,
                OrgPrivateProfileDTO.class);

        return new ResponseEntity<>(
                userDTO,
                HttpStatus.CREATED);

    }


    public ResponseEntity<OrgPrivateProfileDTO> getAuthUserPerfil() {

        var userDTO = mapper.map(
                OrgMock.getOne(),
                OrgPrivateProfileDTO.class
        );

        return new ResponseEntity<> (
                userDTO,
                HttpStatus.OK);
    }


    public ResponseEntity<OrgPrivateProfileDTO> updateAuthUserPerfil(OrgPrivateProfileDTO perfil) {

        requestHandlerChain.handle(perfil);

        perfil.setId("123");
        var userDTO = mapper.map(
                perfil,
                OrgPrivateProfileDTO.class
        );

        return new ResponseEntity<>(
                userDTO,
                HttpStatus.OK);
    }


    public ResponseEntity<OrgPrivateProfileDTO> deleteAuthUserPerfil() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<Page<OrgDTO>> getAllUsers() {
        var users = OrgMock.getList();
        return this.getPageFromList(users);
    }


    public ResponseEntity<Page<OrgDTO>> getAllSchools() {
        var users = OrgMock.getSchools();
        return this.getPageFromList(users);
    }


    public ResponseEntity<OrgPublicProfileDTO> getUserPublicProfile(String id) {
        var user = OrgMock.getOne();
        var userDTO = mapper.map(
                user,
                OrgPublicProfileDTO.class
        );
        return new ResponseEntity<>(
                userDTO,
                HttpStatus.OK
        );
    }


    private ResponseEntity<Page<OrgDTO>> getPageFromList(List<OrgPrivateProfileDTO> users) {
        var usersDTO = users.stream().map(user -> mapper.map(user, OrgDTO.class)).toList();
        var page = new PageImpl<>(usersDTO);
        return new ResponseEntity<>(
                page,
                HttpStatus.OK
        );
    }


}
