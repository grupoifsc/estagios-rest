package com.github.projetoifsc.estagios.app.controller;

import com.github.projetoifsc.estagios.app.configs.OpenApiConfig;
import com.github.projetoifsc.estagios.app.model.request.NewJobRequest;
import com.github.projetoifsc.estagios.app.model.response.PrivateJobDetailsResponse;
import com.github.projetoifsc.estagios.app.model.response.PrivateJobSummaryResponse;
import com.github.projetoifsc.estagios.app.model.response.PublicJobDetailsResponse;
import com.github.projetoifsc.estagios.app.model.response.PublicJobSummaryResponse;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.app.service.VagaService;
import com.github.projetoifsc.estagios.app.utils.MediaTypes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.github.projetoifsc.estagios.app.utils.validation.PaginationValidation.DEFAULT_LIMIT_VALUE;
import static com.github.projetoifsc.estagios.app.utils.validation.PaginationValidation.DEFAULT_PAGE_VALUE;


@SecurityRequirement(name = OpenApiConfig.AUTHORIZATION)
@RestController
@RequestMapping(value = OpenApiConfig.BASE_URL,
				produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML, 
						MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS })
public class VagaController {

	private final VagaService service;


	public VagaController(VagaService service) {
		this.service = service;
	}


	@PostMapping(value = "/vagas", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML })
	@Operation(summary="Criar Vaga", description="Criar Nova Vaga de Estágio", tags= {OpenApiConfig.VAGAS}, operationId="postVaga")
	@ApiResponses({@ApiResponse(responseCode = "201")})
	public ResponseEntity<PrivateJobDetailsResponse> create (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@RequestBody NewJobRequest vaga
	) {
		return new ResponseEntity<>(
				service.create(userPrincipal, vaga),
				HttpStatus.CREATED
		);
	}


	@PutMapping("/vagas/{id}")
	@Operation(summary="Atualizar Vaga", description="Atualizar uma vaga de estágio", tags= {OpenApiConfig.VAGAS}, operationId="putVaga")
	@ApiResponses({@ApiResponse(responseCode = "200")})
	public ResponseEntity<PrivateJobDetailsResponse> update(
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable("id") String vagaId,
			@RequestBody NewJobRequest vaga
	) {
		return new ResponseEntity<>(
				service.update(userPrincipal, vagaId, vaga),
				HttpStatus.OK
		);
	}


	@DeleteMapping("/vagas/{id}")
	@Operation(summary="Deletar Vaga", description="Deletar uma vaga de estágio e todos os dados relacionados a ela", tags= {OpenApiConfig.VAGAS}, operationId="deleteVaga")
	@ApiResponses({@ApiResponse(responseCode = "204")})
	public ResponseEntity<PrivateJobDetailsResponse> delete(
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable("id") String vagaId
	) {
		service.delete(userPrincipal, vagaId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


	@GetMapping("/vagas/{id}/public")
	@Operation(summary="Ver Vaga", description="Ver o perfil público de uma vaga. Autorizado apenas ao criador ou destinatários da vaga.", tags= {OpenApiConfig.VAGAS}, operationId="getVaga")
	@ApiResponses({@ApiResponse(responseCode = "200")})
	public ResponseEntity<PublicJobDetailsResponse> getPublicProfile (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable("id") String vagaId
	) {
		return new ResponseEntity<>(
				service.getPublicProfile(userPrincipal, vagaId),
				HttpStatus.OK
		);
	}


	@GetMapping("/vagas/{id}")
	@Operation(summary="Ver Perfil Privado de Vaga", description="Ver perfil privado de uma vaga. Autorizado apenas ao criador da vaga.", tags= {OpenApiConfig.VAGAS}, operationId="getVagaPrivate")
	@ApiResponses({@ApiResponse(responseCode = "200")})
	public ResponseEntity<PrivateJobDetailsResponse> getPrivateProfile (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable("id") String vagaId
	) {
		return new ResponseEntity<>(
				service.getPrivateProfile(userPrincipal, vagaId),
				HttpStatus.OK
		);
	}


	@GetMapping("/organizacoes/{id}/vagas")
	@Operation(summary="Vagas Criadas", description="Ver as vagas criadas pela organização", tags= { OpenApiConfig.VAGAS}, operationId="getVagasCriadas")
	@ApiResponses({@ApiResponse(responseCode = "200")})
	public ResponseEntity<Page<PrivateJobSummaryResponse>> getAllCreatedByUser (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
			@RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page,
			@PathVariable String id) {
		return new ResponseEntity<>(
				service.getAllCreatedByUser(userPrincipal, id, page, limit),
				HttpStatus.OK
		);
	}

	@GetMapping("/organizacoes/{id}/vagas/disponiveis")
	@Operation(summary="Vagas Disponíveis Aprovadas", description="Ver todas as vagas APROVADAS e disponíveis para a instituição", tags= { OpenApiConfig.VAGAS}, operationId="getVagasDisponiveis")
	@ApiResponses({@ApiResponse(responseCode = "200")})
	public ResponseEntity<Page<PublicJobSummaryResponse>> getAllAvailableForUser (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
			@RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page,
			@PathVariable String id) {
		return new ResponseEntity<>(
				service.getAllAvailableForUser(userPrincipal, id, page, limit),
				HttpStatus.OK
		);
	}

	@GetMapping("/organizacoes/{id}/vagas/aprovadas")
	@Operation(summary="Vagas Aprovadas", description="Ver as vagas aprovadas pela instituição. Exibe apenas vagas recebidas de terceiros", tags= { OpenApiConfig.VAGAS}, operationId="getVagasArpovadas")
	@ApiResponses({@ApiResponse(responseCode = "200")})
	public ResponseEntity<Page<PublicJobSummaryResponse>> getAllApprovedByUser (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
			@RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page,
			@PathVariable String id) {
		return new ResponseEntity<>(
				service.getAllApprovedByUser(userPrincipal, id, page, limit),
				HttpStatus.OK
		);
	}

	@PostMapping("/vagas/aprovadas/{jobId}")
	@Operation(summary="Aprovar Vaga", description="Aprovar uma oferta de vaga", tags= { OpenApiConfig.VAGAS}, operationId="postVagasAprovadas")
	@ApiResponses({@ApiResponse(responseCode = "200")})
	public ResponseEntity<PublicJobSummaryResponse> approve (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable String jobId) {
		return new ResponseEntity<>(
				service.approve(userPrincipal, jobId),
				HttpStatus.OK
		);
	}


	@GetMapping("/organizacoes/{id}/vagas/rejeitadas")
	@Operation(summary="Vagas Rejeitadas", description="Ver as vagas rejeitadas pela instituição.", tags= { OpenApiConfig.VAGAS}, operationId="getVagasRejeitadas")
	@ApiResponses({@ApiResponse(responseCode = "200")})
	public ResponseEntity<Page<PublicJobSummaryResponse>> getAllRejectedByUser (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
			@RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page,
			@PathVariable String id) {
		return new ResponseEntity<>(
				service.getAllRejectedByUser(userPrincipal, id, page, limit),
				HttpStatus.OK
		);
	}

	@PostMapping("/vagas/rejeitadas/{jobId}")
	@Operation(summary="Rejeitar Vaga", description="Rejeitar uma oferta de vaga", tags= { OpenApiConfig.VAGAS}, operationId="postVagasRejeitadas")
	@ApiResponses({@ApiResponse(responseCode = "200")})
	public ResponseEntity<PublicJobSummaryResponse> reject (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable String jobId) {
		return new ResponseEntity<>(
				service.reject(userPrincipal, jobId),
				HttpStatus.OK
		);
	}


	@GetMapping("/organizacoes/{id}/vagas/pendentes")
	@Operation(summary="Moderação Pendente", description="Ver as vagas com moderação pendente.", tags= { OpenApiConfig.VAGAS}, operationId="getVagasPendentes")
	@ApiResponses({@ApiResponse(responseCode = "200")})
	public ResponseEntity<Page<PublicJobSummaryResponse>> getAllPendingForUser (
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
			@RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page,
			@PathVariable String id) {
		return new ResponseEntity<>(
				service.getAllPendingForUser(userPrincipal, id, page, limit),
				HttpStatus.OK
		);
	}


//	@GetMapping("/entidades/{id}/vagas/recebidas")
//	@Operation(summary="Vagas recebidas", description="Ver as vagas recebidas pelo usuário autenticado", tags= {OpenApiConfig.VAGAS}, operationId="getVagasRecebidas")
//	@ApiResponses({@ApiResponse(responseCode = "200")})
//	public ResponseEntity<List<PublicJobDetailsResponse>> getAllReceivedByUser (
//			@AuthenticationPrincipal UserPrincipal userPrincipal,
//			@RequestParam(value = "titulo", defaultValue = "", required = false) String titulo,
//			@RequestParam(value = "areas", defaultValue = "", required = false) String areas,
//			@RequestParam(value = "niveis", defaultValue = "", required = false) @Schema(allowableValues = {"fundamental", "medio", "superior", "tecnico", "pos"}) String niveis,
//			@RequestParam(value = "remuneracao", defaultValue = "0") @Schema(description = "Remuneração mínima") Integer remuneracao,
//			@RequestParam(value = "periodos", defaultValue = "") @Schema(allowableValues = {"matutino", "vespertino", "noturno"}) String periodos,
//			@RequestParam(value = "sort", defaultValue = "") @Schema(allowableValues = {"periodos", "remuneracao", "data"}) String sort,
//			@RequestParam(value = "order", defaultValue = "ASC") @Schema(type = "string", allowableValues = {"ASC", "DESC"}) String order,
//			@RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
//			@RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page,
//			@PathVariable String id) {
//		var filterArgs = new HashMap<String, String>();
//		filterArgs.put("titulo", titulo);
//		filterArgs.put("areas", areas);
//		filterArgs.put("niveis", niveis);
//		filterArgs.put("periodos", periodos);
//		filterArgs.put("sort", sort);
//		filterArgs.put("remuneracao", remuneracao.toString());
//		filterArgs.put("page", page.toString());
//		filterArgs.put("limit", limit.toString());
//		return new ResponseEntity<>(
//				service.getAllReceivedByUser(userPrincipal, id, filterArgs),
//				HttpStatus.OK
//		);
//	}




}
