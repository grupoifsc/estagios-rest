package com.github.projetoifsc.estagios.app.controller;

import com.github.projetoifsc.estagios.app.dto.AreaDTO;
import com.github.projetoifsc.estagios.app.service.AreaService;
import com.github.projetoifsc.estagios.app.utils.MediaTypes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.github.projetoifsc.estagios.app.utils.HttpErrorMessages.TOO_MANY_REQUESTS_MSG;
import static com.github.projetoifsc.estagios.app.utils.HttpErrorMessages.UNAUTHORIZED_MSG;
import static com.github.projetoifsc.estagios.app.utils.swagger.SwaggerTags.*;

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
	@ApiResponses({
	    @ApiResponse(responseCode = "200"),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<Page<AreaDTO>> getAllAreas () {
		return service.getAll();
	}


	@PostMapping("/areas")
	@Operation(summary="Criar", description="Criar Área", tags={AREAS}, operationId="postArea")
	public ResponseEntity<Page<AreaDTO>> criarArea () {
		return service.getAll();
	}


	@GetMapping("/entidades/{id}/areas")
	@Operation(summary="Ver áreas de uma entidade", description="Ver todas as áreas criadas por uma entidade", tags={AREAS}, operationId="getAllAreasFromUser")
	public ResponseEntity<Page<AreaDTO>> areasCriadas () {
		return service.getAll();
	}

	@GetMapping("/areas/{id}")
	@Operation(summary="Ver", description="Ver uma área", tags={AREAS}, operationId="getArea")
	public ResponseEntity<Page<AreaDTO>> verArea () {
		return service.getAll();
	}


	@PutMapping("/areas/{id}")
	@Operation(summary="Editar", description="Editar uma área", tags={AREAS}, operationId="putArea")
	public ResponseEntity<Page<AreaDTO>> editarAreas () {
		return service.getAll();
	}


	@DeleteMapping("/areas/{id}")
	@Operation(summary="Deletar", description="Deletar uma área", tags={AREAS}, operationId="deleteArea")
	public ResponseEntity<Page<AreaDTO>> deletarAreas () {
		return service.getAll();
	}



//	@GetMapping("/mine")
//	@Operation(summary=GET_MINE_SUMMARY, description=GET_MINE_DESCRIPTION, tags={ AREAS, IES }, operationId=GET_MINE_ID)
//	@ApiResponses({
//	    @ApiResponse(responseCode = "200", content = {@Content(examples= { @ExampleObject(value = AREA_CONTENT_MSG) })} ),
//	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getAREAS", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}),
//	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
//	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
//	})
//	public List<String> getMyAreas (
//			@RequestHeader Long auth
//	) {
//		return null;
//	}
//
//
//
//	@PostMapping(value = "/mine", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML })
//	@Operation(summary=POST_SUMMARY, description=POST_DESCRIPTION, tags={ VAGAS, IES }, operationId=POST_ID)
//	@ApiResponses({
//	    @ApiResponse(responseCode = "201", content = {@Content(examples= { @ExampleObject(value = AREA_CONTENT_MSG) })} ),
//	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}),
//	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
//	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
//	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
//	})
//	public List<String> createAreas (
//			@RequestHeader Long auth,
//			@RequestBody List<String> areasList
//	) {
//		return null;
//	}
//
//
//
//	@PutMapping(value = "/mine", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML })
//	@Operation(summary=PUT_SUMMARY, description=PUT_DESCRIPTION, tags={ VAGAS, IES }, operationId=PUT_ID)
//	@ApiResponses({
//	    @ApiResponse(responseCode = "200", content = {@Content(examples= { @ExampleObject(value = AREA_CONTENT_MSG) })} ),
//	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}),
//	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
//	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
//	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
//	})
//	public List<String> updateAreas (
//			@RequestHeader Long auth,
//			@RequestBody List<String> areasList
//	) {
//		return null;
//	}
//
//
//
//	@DeleteMapping("/mine")
//	@Operation(summary=DELETE_SUMMARY, description=DELETE_DESCRIPTION, tags={ AREAS, IES }, operationId=DELETE_ID)
//	@ApiResponses({
//	    @ApiResponse(responseCode = "204"),
//	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
//	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
//	})
//	public List<String> deleteAreas (
//			@RequestHeader Long auth
//	) {
//		return null;
//	}
	
	
}
