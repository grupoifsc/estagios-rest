package com.github.projetoifsc.estagios.app.controller;

import com.github.projetoifsc.estagios.app.configs.OpenApiConfig;
import com.github.projetoifsc.estagios.app.model.response.PublicAreaResponse;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.app.service.AreaService;
import com.github.projetoifsc.estagios.app.utils.MediaTypes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@SecurityScheme(
//		name = "bearerToken",
//		type = SecuritySchemeType.HTTP,
//		scheme = "bearer",
//		bearerFormat = "JWT"
//)
@SecurityRequirement(name = OpenApiConfig.AUTHORIZATION)
@RestController
@RequestMapping(value = OpenApiConfig.BASE_URL,
				produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML, 
								MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS } )

public class AreaController {

	private final AreaService service;

	public AreaController(AreaService service) {
		this.service = service;
	}


	@GetMapping("/areas")
	@Operation(summary="Ver Todas", description="Ver todas as áreas", tags={OpenApiConfig.AREAS}, operationId="getAllAreas")
	public ResponseEntity<List<PublicAreaResponse>> getAllAreas (
			@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		return new ResponseEntity<>(
				service.getAll(userPrincipal),
				HttpStatus.OK
		);
	}


	@GetMapping("/areas/{id}")
	@Operation(summary="Ver", description="Ver uma área", tags={OpenApiConfig.AREAS}, operationId="getArea")
	public ResponseEntity<PublicAreaResponse> verArea (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable String id
	) {
		return new ResponseEntity<>(
				service.getById(userPrincipal, id),
				HttpStatus.OK
		);
	}


}
