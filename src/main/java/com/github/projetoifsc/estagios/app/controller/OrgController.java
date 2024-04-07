package com.github.projetoifsc.estagios.app.controller;

import com.github.projetoifsc.estagios.app.dto.OrgDTO;
import com.github.projetoifsc.estagios.app.dto.OrgPrivateProfileDTO;
import com.github.projetoifsc.estagios.app.dto.OrgPublicProfileDTO;
import com.github.projetoifsc.estagios.app.service.OrgService;
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

import static com.github.projetoifsc.estagios.app.utils.HttpErrorMessages.FORBIDDEN_MSG;


@RestController
@RequestMapping(
		value = SwaggerTags.BASE_URL + "/organizacoes",
		produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML,
				MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS } )

public class OrgController {

	OrgService service;

	@Autowired
	public OrgController(OrgService service) {
		this.service = service;
	}


	@PostMapping(value = "", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML } )
	@Operation(summary="Cadastrar", description="Cadastro de Nova Organização", tags={SwaggerTags.ORGS}, operationId="userPost")
	@ApiResponses({
			@ApiResponse(responseCode = "201"),
			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.BAD_REQUEST_MSG) })} ),
			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.UNAUTHORIZED_MSG) })} ),
			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<OrgPrivateProfileDTO> createNewUser (
			@RequestBody OrgPrivateProfileDTO perfil
	) {
		return service.create(perfil);
	}


	@GetMapping("/{id}")
	@Operation(summary="Ver Perfil", description="Ver Perfil Privado da organização", tags={SwaggerTags.ORGS}, operationId="getPerfil")
	@ApiResponses({
	    @ApiResponse(responseCode = "200"),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.UNAUTHORIZED_MSG) })} ),
		@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<OrgPrivateProfileDTO> getAuthUserPerfil () {
		return service.getAuthUserPerfil();
	}

	
	@PutMapping(value = "/{id}", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML } )
	@Operation(summary="Atualizar perfil", description="Atualizar perfil da organização", tags={SwaggerTags.ORGS}, operationId="putPerfil")
	@ApiResponses({
	    @ApiResponse(responseCode = "200"),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.UNAUTHORIZED_MSG) })} ),
		@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<OrgPrivateProfileDTO> updateAuthUserPerfil (
			@RequestBody OrgPrivateProfileDTO perfil
	)  {
		return service.updateAuthUserPerfil(perfil);
	}
	
	
	
	@DeleteMapping("/{id}")
	@Operation(summary="Deletar Perfil", description="Deletar perfil da organização e todos os seus dados (vagas de estágio, áreas, etc)", tags={SwaggerTags.ORGS}, operationId="deletePerfil")
	@ApiResponses({
	    @ApiResponse(responseCode = "204"),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.UNAUTHORIZED_MSG) })} ),
		@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.TOO_MANY_REQUESTS_MSG) })} )
	  })
	public ResponseEntity<OrgPrivateProfileDTO> deleteAuthUserPerfil () {
		return service.deleteAuthUserPerfil();
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
	@Operation(summary="Ver Instituições de Ensino", description="Lista de todas as instituições de ensino cadastradas no sistema", tags={SwaggerTags.ORGS}, operationId="getSchools")
	@ApiResponses({
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.BAD_REQUEST_MSG) })} ),
			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.UNAUTHORIZED_MSG) })} ),
			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<Page<OrgDTO>> getAllSchools() {
		return service.getAllSchools();
	}


	@GetMapping("/{id}/public")
	@Operation(summary="Perfil público da organização", description="Ver Perfil Público da organização", tags={SwaggerTags.ORGS}, operationId="getUserPublicProfile")
	@ApiResponses({
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.BAD_REQUEST_MSG) })} ),
			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.UNAUTHORIZED_MSG) })} ),
			@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.NOT_FOUND_MSG) })} ),
			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = HttpErrorMessages.TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<OrgPublicProfileDTO> getUserPublicProfile(
			@PathVariable String id
	) {
		return service.getUserPublicProfile(id);
	}


}
