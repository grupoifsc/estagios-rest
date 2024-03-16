package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.dto.UserDTO;
import com.github.projetoifsc.estagios.app.dto.UserPrivateProfileDTO;
import com.github.projetoifsc.estagios.app.dto.UserPublicProfileDTO;
import com.github.projetoifsc.estagios.app.service.handler.RequestHandlerChain;
import com.github.projetoifsc.estagios.app.utils.mock.UserMock;
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
public class UserService {

    ModelMapper mapper = new ModelMapper();
    RequestHandlerChain requestHandlerChain = new RequestHandlerChain();


    public ResponseEntity<UserPrivateProfileDTO> create(UserPrivateProfileDTO perfil) {

        requestHandlerChain.handle(perfil);

        perfil.setId("111");
        var userDTO = mapper.map(
                perfil,
                UserPrivateProfileDTO.class);

        return new ResponseEntity<>(
                userDTO,
                HttpStatus.CREATED);

    }


    public ResponseEntity<UserPrivateProfileDTO> getAuthUserPerfil() {

        var userDTO = mapper.map(
                UserMock.getOne(),
                UserPrivateProfileDTO.class
        );

        return new ResponseEntity<> (
                userDTO,
                HttpStatus.OK);
    }


    public ResponseEntity<UserPrivateProfileDTO> updateAuthUserPerfil(UserPrivateProfileDTO perfil) {

        requestHandlerChain.handle(perfil);

        perfil.setId("123");
        var userDTO = mapper.map(
                perfil,
                UserPrivateProfileDTO.class
        );

        return new ResponseEntity<>(
                userDTO,
                HttpStatus.OK);
    }


    public ResponseEntity<UserPrivateProfileDTO> deleteAuthUserPerfil() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<Page<UserDTO>> getAllUsers() {
        var users = UserMock.getList();
        return this.getPageFromList(users);
    }


    public ResponseEntity<Page<UserDTO>> getAllSchools() {
        var users = UserMock.getSchools();
        return this.getPageFromList(users);
    }


    public ResponseEntity<UserPublicProfileDTO> getUserPublicProfile(String id) {
        var user = UserMock.getOne();
        var userDTO = mapper.map(
                user,
                UserPublicProfileDTO.class
        );
        return new ResponseEntity<>(
                userDTO,
                HttpStatus.OK
        );
    }


    private ResponseEntity<Page<UserDTO>> getPageFromList(List<UserPrivateProfileDTO> users) {
        var usersDTO = users.stream().map(user -> mapper.map(user, UserDTO.class)).toList();
        var page = new PageImpl<>(usersDTO);
        return new ResponseEntity<>(
                page,
                HttpStatus.OK
        );
    }


}
