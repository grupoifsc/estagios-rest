package com.github.projetoifsc.estagios.app.controller;

import com.github.projetoifsc.estagios.app.configs.OpenApiConfig;
import com.github.projetoifsc.estagios.app.model.request.NewOrgProfileRequest;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.app.service.OrgService;
import com.github.projetoifsc.estagios.app.utils.MediaTypes;
import com.github.projetoifsc.estagios.core.IAddress;
import com.github.projetoifsc.estagios.core.IContact;
import com.github.projetoifsc.estagios.core.IOrganization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.projetoifsc.estagios.app.configs.OpenApiConfig.AUTHORIZATION;


@RestController
@RequestMapping(
		value = OpenApiConfig.BASE_URL + "/organizacoes",
		produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML,
				MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS } )

public class OrgController {


	private final OrgService service;
	private final String securityName = AUTHORIZATION;


	public OrgController(OrgService service) {
		this.service = service;
	}


	@PostMapping(value = "", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML } )
	@Operation(summary="Cadastrar", description="Cadastro de Nova Organização", tags={OpenApiConfig.ORGS}, operationId="userPost")
	@ApiResponses({@ApiResponse(responseCode = "201")})
	public ResponseEntity<IOrganization> createNewUser (
			@RequestBody NewOrgProfileRequest newUser
	) {
		return new ResponseEntity<>(
				service.create(newUser),
				HttpStatus.CREATED
		);
	}


	@GetMapping("/{id}")
	@Operation(summary="Ver Perfil", description="Ver Perfil Privado da organização", tags={OpenApiConfig.ORGS}, operationId="getPerfil", security = {@SecurityRequirement(name = AUTHORIZATION)})
	@ApiResponses({@ApiResponse(responseCode = "200")})
	public ResponseEntity<IOrganization> getAuthUserPerfil (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable String id
	) {
		return new ResponseEntity<>(
				service.getAuthUserPerfil(userPrincipal, id),
				HttpStatus.OK
		);
	}

	
	@PutMapping(value = "/{id}", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML } )
	@Operation(summary="Atualizar perfil", description="Atualizar perfil da organização", tags={OpenApiConfig.ORGS}, operationId="putPerfil", security = {@SecurityRequirement(name = securityName)})
	@ApiResponses({@ApiResponse(responseCode = "200")})
	public ResponseEntity<IOrganization> updateAuthUserPerfil (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable String id,
			@RequestBody NewOrgProfileRequest updatedUser
	)  {
		return new ResponseEntity<>(
				service.updateAuthUserPerfil(userPrincipal, id, updatedUser),
				HttpStatus.OK
		);
	}
	
	
	
	@DeleteMapping("/{id}")
	@Operation(summary="Deletar Perfil", description="Deletar perfil da organização e todos os seus dados (vagas de estágio, áreas, etc)", tags={OpenApiConfig.ORGS}, operationId="deletePerfil", security = {@SecurityRequirement(name = securityName)})
	@ApiResponses({@ApiResponse(responseCode = "204")})
	public ResponseEntity<IOrganization> deleteAuthUserPerfil (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable String id) {
		service.deleteAuthUserPerfil(userPrincipal, id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}



	@GetMapping("/educacionais")
	@Operation(summary="Ver Instituições de Ensino", description="Lista de todas as instituições de ensino cadastradas no sistema", tags={OpenApiConfig.ORGS}, operationId="getSchools", security = {@SecurityRequirement(name = securityName)})
	@ApiResponses({@ApiResponse(responseCode = "200")})
	public ResponseEntity<Page<IOrganization>> getAllSchools(
			@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		return new ResponseEntity<>(
				service.getAllSchools(userPrincipal),
				HttpStatus.OK
		);
	}


	@GetMapping("/{id}/public")
	@Operation(summary="Perfil público da organização", description="Ver Perfil Público da organização", tags={OpenApiConfig.ORGS}, operationId="getUserPublicProfile", security = {@SecurityRequirement(name = securityName)})
	@ApiResponses({@ApiResponse(responseCode = "200")})
	public ResponseEntity<IOrganization> getUserPublicProfile(
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable String id
	) {
		return new ResponseEntity<>(
				service.getUserPublicProfile(userPrincipal, id),
				HttpStatus.OK
		);
	}


	@GetMapping("/{id}/addresses")
	@Operation(summary="Endereços Cadastrados", description="Ver todos os endereços cadastrados para a organização", tags={OpenApiConfig.ORGS}, operationId="getUserAddresses", security = {@SecurityRequirement(name = securityName)})
	@ApiResponses({@ApiResponse(responseCode = "200")})
	public ResponseEntity<List<IAddress>> getAllUserAddresses(
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable String id
	) {
		return new ResponseEntity<>(
				service.getAllUserAddresses(userPrincipal, id),
				HttpStatus.OK
		);
	}


	@GetMapping("/{id}/contacts")
	@Operation(summary="Contatos Cadastrados", description="Ver todos os contatos cadastrados para a organização", tags={OpenApiConfig.ORGS}, operationId="getUserContacts", security = {@SecurityRequirement(name = securityName)})
	@ApiResponses({@ApiResponse(responseCode = "200")})
	public ResponseEntity<List<IContact>> getAllUserContacts(
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable String id
	) {
		return new ResponseEntity<>(
				service.getAllUserContacts(userPrincipal, id),
				HttpStatus.OK
		);
	}


}
