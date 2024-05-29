package com.github.projetoifsc.estagios.app.controller;

import com.github.projetoifsc.estagios.app.model.request.NewUserRequest;
import com.github.projetoifsc.estagios.app.security.UserPrincipal;
import com.github.projetoifsc.estagios.app.service.OrgService;
import com.github.projetoifsc.estagios.app.utils.HttpErrorMessages;
import com.github.projetoifsc.estagios.app.utils.MediaTypes;
import com.github.projetoifsc.estagios.app.utils.swagger.SwaggerTags;
import com.github.projetoifsc.estagios.core.IOrganization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.github.projetoifsc.estagios.app.utils.swagger.SwaggerTags.AUTHORIZATION;

//@SecurityScheme(
//		name = AUTHORIZATION,
//		type = SecuritySchemeType.HTTP,
//		scheme = "bearer",
//		bearerFormat = "JWT"
//)
@RestController
@RequestMapping(
		value = SwaggerTags.BASE_URL + "/organizacoes",
		produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML,
				MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS } )

public class OrgController {

	OrgService service;
	private final String securityName = AUTHORIZATION;

	@Autowired
	public OrgController(OrgService service) {
		this.service = service;
	}


	@PostMapping(value = "", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML } )
	@Operation(summary="Cadastrar", description="Cadastro de Nova Organização", tags={SwaggerTags.ORGS}, operationId="userPost")
//	@ApiResponses({
//			@ApiResponse(responseCode = "201"),
//			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.BAD_REQUEST_MSG) })} ),
//			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.UNAUTHORIZED_MSG) })} ),
//			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.TOO_MANY_REQUESTS_MSG) })} )
//	})
	public ResponseEntity<IOrganization> createNewUser (
			@RequestBody NewUserRequest newUser
	) {
		return new ResponseEntity<>(
				service.create(newUser),
				HttpStatus.CREATED
		);
	}


	@GetMapping("/{id}")
	@Operation(summary="Ver Perfil", description="Ver Perfil Privado da organização", tags={SwaggerTags.ORGS}, operationId="getPerfil", security = {@SecurityRequirement(name = AUTHORIZATION)})
//	@ApiResponses({
//	    @ApiResponse(responseCode = "200"),
//	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.BAD_REQUEST_MSG) })} ),
//	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.UNAUTHORIZED_MSG) })} ),
//		@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
//	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.TOO_MANY_REQUESTS_MSG) })} )
//	})
	public ResponseEntity<IOrganization> getAuthUserPerfil (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable String id
	) {
		return new ResponseEntity<>(
				service.getAuthUserPerfil(id),
				HttpStatus.OK
		);
	}

	
	@PutMapping(value = "/{id}", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML } )
	@Operation(summary="Atualizar perfil", description="Atualizar perfil da organização", tags={SwaggerTags.ORGS}, operationId="putPerfil", security = {@SecurityRequirement(name = securityName)})
//	@ApiResponses({
//	    @ApiResponse(responseCode = "200"),
//	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.BAD_REQUEST_MSG) })} ),
//	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.UNAUTHORIZED_MSG) })} ),
//		@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
//	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.TOO_MANY_REQUESTS_MSG) })} )
//	})
	public ResponseEntity<IOrganization> updateAuthUserPerfil (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable String id,
			@RequestBody NewUserRequest updatedUser
	)  {
		return new ResponseEntity<>(
				service.updateAuthUserPerfil(id, updatedUser),
				HttpStatus.OK
		);
	}
	
	
	
	@DeleteMapping("/{id}")
	@Operation(summary="Deletar Perfil", description="Deletar perfil da organização e todos os seus dados (vagas de estágio, áreas, etc)", tags={SwaggerTags.ORGS}, operationId="deletePerfil", security = {@SecurityRequirement(name = securityName)})
//	@ApiResponses({
//	    @ApiResponse(responseCode = "204"),
//	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.BAD_REQUEST_MSG) })} ),
//	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.UNAUTHORIZED_MSG) })} ),
//		@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
//	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.TOO_MANY_REQUESTS_MSG) })} )
//	  })
	public ResponseEntity<IOrganization> deleteAuthUserPerfil (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable String id) {
		service.deleteAuthUserPerfil(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


//	@GetMapping("")
//	@Operation(summary="Entidades", description="Lista das entidades cadastradas no sistema", tags={SwaggerTags.ENTIDADES}, operationId="usersGetAll")
//	@ApiResponses({
//			@ApiResponse(responseCode = "200"),
//			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.BAD_REQUEST_MSG) })} ),
//			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.UNAUTHORIZED_MSG) })} ),
//			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.TOO_MANY_REQUESTS_MSG) })} )
//	})
//	public ResponseEntity<Page<UserDTO>> getAllUsers() {
//		return service.getAllUsers();
//	}


	@GetMapping("/educacionais")
	@Operation(summary="Ver Instituições de Ensino", description="Lista de todas as instituições de ensino cadastradas no sistema", tags={SwaggerTags.ORGS}, operationId="getSchools", security = {@SecurityRequirement(name = securityName)})
//	@ApiResponses({
//			@ApiResponse(responseCode = "200"),
//			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.BAD_REQUEST_MSG) })} ),
//			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.UNAUTHORIZED_MSG) })} ),
//			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.TOO_MANY_REQUESTS_MSG) })} )
//	})
	public ResponseEntity<Page<IOrganization>> getAllSchools(
			@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		return new ResponseEntity<>(
				service.getAllSchools(),
				HttpStatus.OK
		);
	}


	@GetMapping("/{id}/public")
	@Operation(summary="Perfil público da organização", description="Ver Perfil Público da organização", tags={SwaggerTags.ORGS}, operationId="getUserPublicProfile", security = {@SecurityRequirement(name = securityName)})
	@ApiResponses({
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.BAD_REQUEST_MSG) })} ),
			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.UNAUTHORIZED_MSG) })} ),
			@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.NOT_FOUND_MSG) })} ),
			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<IOrganization> getUserPublicProfile(
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable String id
	) {
		return new ResponseEntity<>(
				service.getUserPublicProfile(id),
				HttpStatus.OK
		);
	}


}
