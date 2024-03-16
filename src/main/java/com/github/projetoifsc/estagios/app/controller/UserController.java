package com.github.projetoifsc.estagios.app.controller;

import com.github.projetoifsc.estagios.app.dto.UserDTO;
import com.github.projetoifsc.estagios.app.dto.UserPrivateProfileDTO;
import com.github.projetoifsc.estagios.app.dto.UserPublicProfileDTO;
import com.github.projetoifsc.estagios.app.service.UserService;
import com.github.projetoifsc.estagios.app.utils.HttpErrorMessages;
import com.github.projetoifsc.estagios.app.utils.MediaTypes;
import com.github.projetoifsc.estagios.app.utils.swagger.SwaggerTags;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(
		value = SwaggerTags.BASE_URL + "/entidades",
		produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML,
				MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS } )

public class UserController {

	UserService service;

	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}


	@PostMapping(value = "/perfil", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML } )
	@Operation(summary="Criar Perfil da Entidade", description="Cadastro de Nova Entidade", tags={SwaggerTags.PERFIL}, operationId="userPost")
	@ApiResponses({
			@ApiResponse(responseCode = "201"),
			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.BAD_REQUEST_MSG) })} ),
			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.UNAUTHORIZED_MSG) })} ),
			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<UserPrivateProfileDTO> createNewUser (
			@RequestBody UserPrivateProfileDTO perfil
	) {
		return service.create(perfil);
	}


	@GetMapping("/perfil")
	@Operation(summary="Perfil da entidade autenticado", description="Ver Perfil Privado da entidade autenticada", tags={SwaggerTags.PERFIL}, operationId="getPerfil")
	@ApiResponses({
	    @ApiResponse(responseCode = "200"),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<UserPrivateProfileDTO> getAuthUserPerfil () {
		return service.getAuthUserPerfil();
	}

	
	@PutMapping(value = "/perfil", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML } )
	@Operation(summary="Atualizar perfil", description="Atualizar perfil da entidade autenticada", tags={SwaggerTags.PERFIL}, operationId="putPerfil")
	@ApiResponses({
	    @ApiResponse(responseCode = "200"),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<UserPrivateProfileDTO> updateAuthUserPerfil (
			@RequestBody UserPrivateProfileDTO perfil
	)  {
		return service.updateAuthUserPerfil(perfil);
	}
	
	
	
	@DeleteMapping("/perfil")
	@Operation(summary="Deletar Perfil", description="Deletar perfil da entidade autenticada e todos os seus dados (vagas de estágio, áreas, etc)", tags={SwaggerTags.PERFIL}, operationId="deletePerfil")
	@ApiResponses({
	    @ApiResponse(responseCode = "204"),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.TOO_MANY_REQUESTS_MSG) })} )
	  })
	public ResponseEntity<UserPrivateProfileDTO> deleteAuthUserPerfil () {
		return service.deleteAuthUserPerfil();
	}


	@GetMapping("")
	@Operation(summary="Entidades", description="Lista das entidades cadastradas no sistema", tags={SwaggerTags.PERFIL}, operationId="usersGetAll")
	@ApiResponses({
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.BAD_REQUEST_MSG) })} ),
			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.UNAUTHORIZED_MSG) })} ),
			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<Page<UserDTO>> getAllUsers() {
		return service.getAllUsers();
	}


	@GetMapping("/educacionais")
	@Operation(summary="Instituições de Ensino", description="Lista de todas as instituições de ensino cadastradas no sistema", tags={SwaggerTags.PERFIL}, operationId="getSchools")
	@ApiResponses({
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.BAD_REQUEST_MSG) })} ),
			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.UNAUTHORIZED_MSG) })} ),
			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<Page<UserDTO>> getAllSchools() {
		return service.getAllSchools();
	}


	@GetMapping("/{id}")
	@Operation(summary="Perfil da entidade", description="Ver Perfil Público da entidade", tags={SwaggerTags.PERFIL}, operationId="getUserPublicProfile")
	@ApiResponses({
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.BAD_REQUEST_MSG) })} ),
			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.UNAUTHORIZED_MSG) })} ),
			@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.NOT_FOUND_MSG) })} ),
			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<UserPublicProfileDTO> getUserPublicProfile(
			@PathVariable String id
	) {
		return service.getUserPublicProfile(id);
	}


}
