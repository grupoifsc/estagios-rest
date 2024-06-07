package com.github.projetoifsc.estagios.app.controller;

import com.github.projetoifsc.estagios.app.configs.OpenApiConfig;
import com.github.projetoifsc.estagios.app.model.response.PrivateOrgProfileResponse;
import com.github.projetoifsc.estagios.app.model.response.PublicOrgProfileResponse;
import com.github.projetoifsc.estagios.app.model.response.wrapper.SuccessResponse;
import com.github.projetoifsc.estagios.app.model.request.NewOrgProfileRequest;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.app.service.OrgService;
import com.github.projetoifsc.estagios.app.service.ResponseEntityService;
import com.github.projetoifsc.estagios.app.utils.MediaTypes;
import com.github.projetoifsc.estagios.core.models.IOrganization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.github.projetoifsc.estagios.app.configs.OpenApiConfig.AUTHORIZATION;
import static com.github.projetoifsc.estagios.app.configs.OpenApiConfig.ORGS;


@RestController
@RequestMapping(
		value = OpenApiConfig.BASE_URL,
		produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML,
				MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS } )

public class OrganizationsController {

	private final OrgService service;
	private final ResponseEntityService responseEntity;

	public OrganizationsController(OrgService service, ResponseEntityService responseEntity) {
		this.service = service;
        this.responseEntity = responseEntity;
    }


	@PostMapping(value = "/orgs", consumes = { MediaTypes.APPLICATION_JSON,
			MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML } )
	@Operation(summary="Cadastrar", description="Cadastro de Nova Organização",
			tags={ORGS}, operationId="userPost")
	@ApiResponse(responseCode = "201")
	public ResponseEntity<SuccessResponse<PrivateOrgProfileResponse>> createNewUser (
			@RequestBody NewOrgProfileRequest newUser
	) {
		return responseEntity.createdResponse(
				service.create(newUser)
		);
	}


	@GetMapping("/orgs/schools")
	@Operation(summary="Ver Instituições de Ensino", description="Lista de todas as " +
			"instituições de ensino cadastradas no sistema",
			tags={ORGS}, operationId="getSchools",
			security = {@SecurityRequirement(name = AUTHORIZATION)})
	public ResponseEntity<SuccessResponse<Page<PublicOrgProfileResponse>>> getAllSchools(
			@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		return responseEntity.successResponse(
				service.getAllSchools(userPrincipal)
		);
	}


	@GetMapping("/orgs/{id}")
	@Operation(summary="Perfil público da organização", description="Ver Perfil " +
			"Público da organização", tags={ORGS},
			operationId="getUserPublicProfile",
			security = {@SecurityRequirement(name = AUTHORIZATION)})
	public ResponseEntity<SuccessResponse<PublicOrgProfileResponse>> getUserPublicProfile(
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable String id
	) {
		return responseEntity.successResponse(
				service.getUserPublicProfile(userPrincipal, id)
		);
	}

}
