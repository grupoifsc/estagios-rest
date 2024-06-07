package com.github.projetoifsc.estagios.app.controller;

import com.github.projetoifsc.estagios.app.configs.OpenApiConfig;
import com.github.projetoifsc.estagios.app.model.response.PrivateOrgProfileResponse;
import com.github.projetoifsc.estagios.app.model.response.wrapper.SuccessResponse;
import com.github.projetoifsc.estagios.app.model.request.NewOrgProfileRequest;
import com.github.projetoifsc.estagios.app.model.shared.AddressModel;
import com.github.projetoifsc.estagios.app.model.shared.ContactModel;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.app.service.OrgService;
import com.github.projetoifsc.estagios.app.service.ResponseEntityService;
import com.github.projetoifsc.estagios.app.utils.MediaTypes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.projetoifsc.estagios.app.configs.OpenApiConfig.AUTH_USER_PROFILE;

@SecurityRequirement(name = OpenApiConfig.AUTHORIZATION)
@RestController
@RequestMapping(value = OpenApiConfig.BASE_URL + "/org",
        produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML,
                MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS } )
public class AuthUserProfileController {

    private final ResponseEntityService responseEntity;
    private final OrgService service;

    public AuthUserProfileController(ResponseEntityService responseEntity, OrgService service) {
        this.responseEntity = responseEntity;
        this.service = service;
    }


    @GetMapping("")
    @Operation(summary="Ver Perfil", description="Ver Perfil Privado da organização",
            tags={AUTH_USER_PROFILE}, operationId="getPerfil")
    public ResponseEntity<SuccessResponse<PrivateOrgProfileResponse>> getAuthUserPerfil (
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return responseEntity.successResponse(
                service.getAuthUserPerfil(userPrincipal)
        );
    }


    @PutMapping(value = "", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML } )
    @Operation(summary="Atualizar perfil", description="Atualizar perfil da organização",
            tags={AUTH_USER_PROFILE}, operationId="putPerfil")
    public ResponseEntity<SuccessResponse<PrivateOrgProfileResponse>> updateAuthUserPerfil (
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody NewOrgProfileRequest updatedUser
    )  {
        return responseEntity.successResponse(
                service.updateAuthUserPerfil(userPrincipal, updatedUser)
        );
    }


    @DeleteMapping("")
    @Operation(summary="Deletar Perfil", description="Deletar perfil da " +
            "organização e todos os seus dados (vagas de estágio, áreas, etc)",
            tags={AUTH_USER_PROFILE}, operationId="deletePerfil")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteAuthUserPerfil (
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        service.deleteAuthUserPerfil(userPrincipal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/addresses")
    @Operation(summary="Endereços Cadastrados", description="Ver todos os " +
            "endereços cadastrados para a organização",
            tags={AUTH_USER_PROFILE}, operationId="getUserAddresses")
    public ResponseEntity<SuccessResponse<List<AddressModel>>> getAllUserAddresses(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return responseEntity.successResponse(
                service.getAuthUserAddresses(userPrincipal)
        );
    }


    @GetMapping("/contacts")
    @Operation(summary="Contatos Cadastrados", description="Ver todos os contatos " +
            "cadastrados para a organização",
            tags={AUTH_USER_PROFILE}, operationId="getUserContacts")
    public ResponseEntity<SuccessResponse<List<ContactModel>>> getAllUserContacts(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return responseEntity.successResponse(
                service.getAuthUserContacts(userPrincipal)
        );
    }


}
