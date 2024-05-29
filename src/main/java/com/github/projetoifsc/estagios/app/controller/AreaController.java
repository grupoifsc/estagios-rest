package com.github.projetoifsc.estagios.app.controller;

import com.github.projetoifsc.estagios.app.model.response.AreaView;
import com.github.projetoifsc.estagios.app.security.UserPrincipal;
import com.github.projetoifsc.estagios.app.service.AreaService;
import com.github.projetoifsc.estagios.app.utils.MediaTypes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.projetoifsc.estagios.app.utils.swagger.SwaggerTags.*;

//@SecurityScheme(
//		name = "bearerToken",
//		type = SecuritySchemeType.HTTP,
//		scheme = "bearer",
//		bearerFormat = "JWT"
//)
@SecurityRequirement(name = AUTHORIZATION)
@RestController
@RequestMapping(value = BASE_URL,
				produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML, 
								MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS } )

public class AreaController {

	AreaService service;

	@Autowired
	public AreaController(AreaService service) {
		this.service = service;
	}

	@GetMapping("/areas")
	@Operation(summary="Ver Todas", description="Ver todas as áreas", tags={AREAS}, operationId="getAllAreas")
	public ResponseEntity<List<AreaView>> getAllAreas (
			@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		return new ResponseEntity<>(
				service.getAll(),
				HttpStatus.OK
		);
	}


	@GetMapping("/areas/{id}")
	@Operation(summary="Ver", description="Ver uma área", tags={AREAS}, operationId="getArea")
	public ResponseEntity<AreaView> verArea (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable String id
	) {
		return new ResponseEntity<>(
				service.getById(id),
				HttpStatus.OK
		);
	}
	
}
