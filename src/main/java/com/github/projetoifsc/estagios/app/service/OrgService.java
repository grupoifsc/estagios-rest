package com.github.projetoifsc.estagios.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.projetoifsc.estagios.app.view.OrgPrivateProfileBasicView;
import com.github.projetoifsc.estagios.app.service.handler.RequestHandlerChain;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IOrganizationUseCases;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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


@Service
public class OrgService {

    IOrganizationUseCases organizationUseCases;

    @Autowired
    OrgService(IOrganizationUseCases organizationUseCases) {
        this.organizationUseCases = organizationUseCases;
    }

    ModelMapper mapper = new ModelMapper();
    ObjectMapper jsonMapper = new ObjectMapper();
    RequestHandlerChain requestHandlerChain = new RequestHandlerChain();

    public ResponseEntity<IOrganization> create(OrgPrivateProfileBasicView perfil) {

//        requestHandlerChain.handle(perfil);
//        var userDTO = mapper.map(
//                perfil,
//                OrgPrivateProfileDTO.class);

        var responseObject = organizationUseCases.createProfile(perfil);

        return new ResponseEntity<>(
                responseObject,
                HttpStatus.CREATED);

    }


    public ResponseEntity<IOrganization> getAuthUserPerfil(String id) {

//        var userDTO = mapper.map(
//                organizationUseCases.getPrivateProfile("1", "1"),
//                OrgPrivateProfileDTO.class
//        );
        var response = organizationUseCases.getPrivateProfile(id, id);

        return new ResponseEntity<> (
                response,
                HttpStatus.OK);
    }


    public ResponseEntity<IOrganization> updateAuthUserPerfil(String id, OrgPrivateProfileBasicView perfil) {

//        requestHandlerChain.handle(perfil);

//        var userDTO = mapper.map(
//                perfil,
//                OrgPrivateProfileDTO.class
//        );
        var responseObject = organizationUseCases.updateProfile(id,id, perfil);

        return new ResponseEntity<>(
                responseObject,
                HttpStatus.OK);
    }


    public ResponseEntity<IOrganization> deleteAuthUserPerfil(String id) {
        organizationUseCases.deleteProfile(id, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<Page<IOrganization>> getAllUsers() {
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        var responseObject = organizationUseCases.getAll();
        System.out.println(responseObject);
        // map to DTOs
        // TODO correct DTO type
        var dtos = responseObject.getContent().stream().map(org -> mapper.map(org, OrgPrivateProfileBasicView.class)).toList();
        dtos.forEach(dto -> {
            System.out.println(dto);
            try {
                System.out.println(jsonMapper.writeValueAsString(dto));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        });
        return new ResponseEntity<>(
                responseObject,
                HttpStatus.OK
        );
    }


    public ResponseEntity<Page<IOrganization>> getAllSchools() {
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        var responseObject = organizationUseCases.getAll();
        System.out.println(responseObject);
        // map to DTOs
        // TODO correct DTO type
        var dtos = responseObject.getContent().stream().map(org -> mapper.map(org, OrgPrivateProfileBasicView.class)).toList();
        dtos.forEach(dto -> {
            System.out.println(dto);
            try {
                System.out.println(jsonMapper.writeValueAsString(dto));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        });

        var dtoPage = responseObject.map(org ->
        {
            var converted = mapper.map(org, OrgPrivateProfileBasicView.class);
            return (IOrganization) converted;
        }
        );

        return new ResponseEntity<>(
                dtoPage,
                HttpStatus.OK
        );
    }


    public ResponseEntity<IOrganization> getUserPublicProfile(String id) {
        var responseObject = organizationUseCases.getPublicProfile(id, id);

        //        var userDTO = mapper.map(
//                user,
//                OrgPublicProfileDTO.class
//        );
        return new ResponseEntity<>(
                responseObject,
                HttpStatus.OK
        );
    }


}
