package com.github.projetoifsc.estagios.app.controller;

import com.github.projetoifsc.estagios.app.configs.OpenApiConfig;
import com.github.projetoifsc.estagios.app.model.response.wrapper.SuccessResponse;
import com.github.projetoifsc.estagios.app.model.response.PublicAreaResponse;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.app.service.AreaService;
import com.github.projetoifsc.estagios.app.service.ResponseEntityService;
import com.github.projetoifsc.estagios.app.utils.MediaTypes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SecurityRequirement(name = OpenApiConfig.AUTHORIZATION)
@RestController
@RequestMapping(value = OpenApiConfig.BASE_URL,
				produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML, 
								MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS } )

public class AreasController {

	private final AreaService service;
	private final ResponseEntityService responseEntity;

	public AreasController(AreaService service, ResponseEntityService responseEntity) {
		this.service = service;
        this.responseEntity = responseEntity;
    }


	@GetMapping("/areas")
	@Operation(summary="Ver Todas", description="Ver todas as áreas", tags={OpenApiConfig.AREAS}, operationId="getAllAreas")
	public ResponseEntity<SuccessResponse<List<PublicAreaResponse>>> getAllAreas (
			@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		return responseEntity.successResponse(service.getAll(userPrincipal));
	}


	@GetMapping("/areas/{id}")
	@Operation(summary="Ver", description="Ver uma área", tags={OpenApiConfig.AREAS}, operationId="getArea")
	public ResponseEntity<SuccessResponse<PublicAreaResponse>> verArea (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable String id
	) {
		return responseEntity.successResponse(service.getById(userPrincipal, id));
	}


}
