package com.github.projetoifsc.estagios.app.controller.orgs;

import com.github.projetoifsc.estagios.app.configs.OpenApiConfig;
import com.github.projetoifsc.estagios.app.model.response.Address;
import com.github.projetoifsc.estagios.app.model.response.OrgPrivateProfile;
import com.github.projetoifsc.estagios.app.model.response.wrapper.SuccessResponse;
import com.github.projetoifsc.estagios.app.model.request.OrgEntryData;
import com.github.projetoifsc.estagios.app.model.response.Contact;
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

import static com.github.projetoifsc.estagios.app.configs.OpenApiConfig.PERFIL;

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
            tags={PERFIL}, operationId="getPerfil")
    public ResponseEntity<SuccessResponse<OrgPrivateProfile>> getAuthUserPerfil (
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        System.out.println(userPrincipal.getId());
        System.out.println(userPrincipal.getIe());
        return responseEntity.successResponse(
                service.getAuthUserPerfil(userPrincipal)
        );
    }


    @PutMapping(value = "", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML } )
    @Operation(summary="Atualizar perfil", description="Atualizar perfil da organização",
            tags={PERFIL}, operationId="putPerfil")
    public ResponseEntity<SuccessResponse<OrgPrivateProfile>> updateAuthUserPerfil (
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody OrgEntryData updatedUser
    )  {
        return responseEntity.successResponse(
                service.updateAuthUserPerfil(userPrincipal, updatedUser)
        );
    }


    @DeleteMapping("")
    @Operation(summary="Deletar Perfil", description="Deletar perfil da " +
            "organização e todos os seus dados (vagas de estágio, áreas, etc)",
            tags={PERFIL}, operationId="deletePerfil")
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
            tags={PERFIL}, operationId="getUserAddresses")
    public ResponseEntity<SuccessResponse<List<Address>>> getAllUserAddresses(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return responseEntity.successResponse(
                service.getAuthUserAddresses(userPrincipal)
        );
    }


    @GetMapping("/contacts")
    @Operation(summary="Contatos Cadastrados", description="Ver todos os contatos " +
            "cadastrados para a organização",
            tags={PERFIL}, operationId="getUserContacts")
    public ResponseEntity<SuccessResponse<List<Contact>>> getAllUserContacts(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return responseEntity.successResponse(
                service.getAuthUserContacts(userPrincipal)
        );
    }


}
