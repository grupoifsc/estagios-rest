package com.github.projetoifsc.estagios.app.controller.vagas;


import com.github.projetoifsc.estagios.app.configs.OpenApiConfig;
import com.github.projetoifsc.estagios.app.model.response.JobPrivateDetails;
import com.github.projetoifsc.estagios.app.model.response.wrapper.SuccessResponse;
import com.github.projetoifsc.estagios.app.model.response.JobPublicDetails;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.app.service.ResponseEntityService;
import com.github.projetoifsc.estagios.app.service.VagaService;
import com.github.projetoifsc.estagios.app.utils.MediaTypes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.projetoifsc.estagios.app.configs.OpenApiConfig.MODERACAO;
import static com.github.projetoifsc.estagios.app.configs.OpenApiConfig.VAGAS;
import static com.github.projetoifsc.estagios.app.utils.validation.PaginationValidation.DEFAULT_LIMIT_VALUE;
import static com.github.projetoifsc.estagios.app.utils.validation.PaginationValidation.DEFAULT_PAGE_VALUE;

@SecurityRequirement(name = OpenApiConfig.AUTHORIZATION)
@RestController
@RequestMapping(value = OpenApiConfig.BASE_URL + "/org/vagas",
        produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML,
                MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS } )
public class AuthUserVagasController {

    private final ResponseEntityService responseEntity;
    private final VagaService service;

    public AuthUserVagasController(ResponseEntityService responseEntity, VagaService service) {
        this.responseEntity = responseEntity;
        this.service = service;
    }

    @GetMapping("/owned")
    @Operation(summary="Vagas Criadas", description="Ver as vagas criadas pela " +
            "organização", tags= {VAGAS}, operationId="getVagasCriadas")
    public ResponseEntity<SuccessResponse<Page<JobPrivateDetails>>> getAllCreatedByUser (
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
            @RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page
    ) {
        return responseEntity.successResponse(
                service.getAuthUserCreatedJobsPaginated(userPrincipal, page, limit)
        );
    }


    @GetMapping("/recebidas")
    @Operation(summary="Vagas Recebidas", description="Ver todas as vagas " +
            "recebidas por uma instituição",
            tags= {MODERACAO}, operationId="getVagasRecebidas")
    public ResponseEntity<SuccessResponse<Page<JobPublicDetails>>> getAllReceivedForUser (
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
            @RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page
    ) {
        return responseEntity.successResponse(
                service.getAuthUserReceivedJobs(userPrincipal, page, limit)
        );
    }


    @GetMapping("/disponiveis")
    @Operation(summary="Vagas Disponíveis", description="Ver todas as vagas " +
            "APROVADAS e disponíveis para a instituição",
            tags= {MODERACAO}, operationId="getVagasDisponiveis")
    public ResponseEntity<SuccessResponse<Page<JobPublicDetails>>> getAllAvailableForUser (
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
            @RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page
    ) {
        return responseEntity.successResponse(
                service.getAuthUserAvailableJobs(userPrincipal, page, limit)
        );
    }


    @PutMapping("/disponiveis")
    @Operation(summary="Aprovar Vaga", description="Aprovar uma " +
            "oferta de vaga", tags= {MODERACAO}, operationId="postVagasAprovadas")
    public ResponseEntity<SuccessResponse<Void>> approve (
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody List<String> jobIds
    ) {
        service.approve(userPrincipal, jobIds);
        return responseEntity.successResponse(null);
    }


    @GetMapping("/rejeitadas")
    @Operation(summary="Vagas Rejeitadas", description="Ver as vagas rejeitadas " +
            "pela instituição.", tags= {MODERACAO},
            operationId="getVagasRejeitadas")
    public ResponseEntity<SuccessResponse<Page<JobPublicDetails>>> getAllRejectedByUser (
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
            @RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page
    ) {
        return responseEntity.successResponse(
                service.getAuthUserRejectedJobs(userPrincipal, page, limit)
        );
    }


    @PutMapping("/rejeitadas")
    @Operation(summary="Rejeitar Vagas", description="Rejeitar uma oferta de vaga",
            tags= {MODERACAO}, operationId="postVagasRejeitadas")
    public ResponseEntity<SuccessResponse<Void>> reject (
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody List<String> jobIds
    ) {
        service.reject(userPrincipal, jobIds);
        return responseEntity.successResponse(null);
    }


    @GetMapping("/pendentes")
    @Operation(summary="Moderação Pendente", description="Ver as vagas com " +
            "moderação pendente.", tags= {MODERACAO},
            operationId="getVagasPendentes")
    public ResponseEntity<SuccessResponse<Page<JobPublicDetails>>> getAllPendingForUser (
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
            @RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page
    ) {
        return responseEntity.successResponse(
                service.getAuthUserPendingJobs(userPrincipal, page, limit)
        );
    }


}
