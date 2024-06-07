package com.github.projetoifsc.estagios.app.controller;

import com.github.projetoifsc.estagios.app.configs.OpenApiConfig;
import com.github.projetoifsc.estagios.app.model.response.wrapper.SuccessResponse;
import com.github.projetoifsc.estagios.app.model.request.NewJobRequest;
import com.github.projetoifsc.estagios.app.model.response.PrivateJobDetailsResponse;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.app.service.ResponseEntityService;
import com.github.projetoifsc.estagios.app.service.VagaService;
import com.github.projetoifsc.estagios.app.utils.MediaTypes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.github.projetoifsc.estagios.app.configs.OpenApiConfig.VAGAS;


@SecurityRequirement(name = OpenApiConfig.AUTHORIZATION)
@RestController
@RequestMapping(value = OpenApiConfig.BASE_URL,
				produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML, 
						MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS })
public class VagasController {

	private final VagaService service;
	private final ResponseEntityService responseEntity;


	public VagasController(VagaService service, ResponseEntityService responseEntity) {
		this.service = service;
        this.responseEntity = responseEntity;
    }


	@PostMapping(value = "/vagas",
			consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML,
					MediaTypes.APPLICATION_YAML })
	@Operation(summary="Criar Vaga", description="Criar Nova Vaga de Estágio",
			tags= {VAGAS}, operationId="postVaga")
	@ApiResponse(responseCode = "201")
	public ResponseEntity<SuccessResponse<PrivateJobDetailsResponse>> create (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@RequestBody NewJobRequest vaga
	) {
		return responseEntity.createdResponse(
				service.create(userPrincipal, vaga)
		);
	}


	@PutMapping("/vagas/{id}")
	@Operation(summary="Atualizar Vaga", description="Atualizar uma vaga de estágio",
			tags= {VAGAS}, operationId="putVaga")
	@ApiResponses({@ApiResponse(responseCode = "200")})
	public ResponseEntity<SuccessResponse<PrivateJobDetailsResponse>> update(
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable("id") String vagaId,
			@RequestBody NewJobRequest vaga
	) {
		return responseEntity.successResponse(
				service.update(userPrincipal, vagaId, vaga)
		);
	}


	@DeleteMapping("/vagas/{id}")
	@Operation(summary="Deletar Vaga", description="Deletar uma vaga de estágio " +
			"e todos os dados relacionados a ela",
			tags= {VAGAS}, operationId="deleteVaga")
	@ApiResponse(responseCode = "204")
	public ResponseEntity<Void> delete(
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable("id") String vagaId
	) {
		service.delete(userPrincipal, vagaId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


	@GetMapping("/vagas/{id}")
	@Operation(summary="Ver Perfil de Vaga", description="Ver perfil **** " +
			"ATUALIZAR MENSAGEM! privado de uma vaga. Autorizado apenas ao " +
			"criador da vaga.", tags= {VAGAS}, operationId="getVagaPrivate")
	public ResponseEntity<SuccessResponse<PrivateJobDetailsResponse>> getPrivateProfile (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable("id") String vagaId
	) {
		return responseEntity.successResponse(
				service.getPrivateProfile(userPrincipal, vagaId)
		);
	}



}
