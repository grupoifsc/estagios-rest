package com.github.projetoifsc.estagios.app.controller;

import com.github.projetoifsc.estagios.app.configs.OpenApiConfig;
import com.github.projetoifsc.estagios.app.model.SuccessResponseWrapper;
import com.github.projetoifsc.estagios.app.model.request.AuthRefreshTokenRequest;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.app.service.AuthenticationService;
import com.github.projetoifsc.estagios.app.model.request.AuthLoginRequest;
import com.github.projetoifsc.estagios.app.model.response.AuthTokenResponse;
import com.github.projetoifsc.estagios.app.utils.MediaTypes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.github.projetoifsc.estagios.app.configs.OpenApiConfig.AUTHORIZATION;

@RestController
@RequestMapping(
        value = OpenApiConfig.BASE_URL + "/auth",
        produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML,
                MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS }
)
public class AuthController {

    private final AuthenticationService authenticationService;


    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }



    @PostMapping(value = "/login")
    public ResponseEntity<SuccessResponseWrapper> login(
            @RequestBody @Validated AuthLoginRequest authLoginRequest
    ) {
        var token = authenticationService.attemptLogin(authLoginRequest);
        return new ResponseEntity<>(
                new SuccessResponseWrapper(token),
                HttpStatus.OK);
    }


    @PostMapping(value = "/token")
    public AuthTokenResponse refreshToken(
            @RequestBody @Validated AuthRefreshTokenRequest authRefreshTokenRequest
            ) {
        return authenticationService.refreshToken(authRefreshTokenRequest);
    }


    @GetMapping("/test")
    @Operation(security = {@SecurityRequirement(name = AUTHORIZATION)})
    public ResponseEntity<String> testAuth(@AuthenticationPrincipal UserPrincipal principal) {
        return ResponseEntity.ok(
                "User Id: " + principal.getId());
    }


    // TODO BugFix: Quando a autorização é com base em ROLES, o erro devolvido não passa pelo HandlerExceptionResolver e o json fica fora do padrão do restante da aplicação
    @GetMapping("/admin")
    @Operation(security = {@SecurityRequirement(name = AUTHORIZATION)})
    public String adminTest(@AuthenticationPrincipal UserPrincipal principal) {
        return "You are a admin!";
    }


}
