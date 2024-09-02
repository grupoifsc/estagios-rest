package com.github.projetoifsc.estagios.app.controller.orgs;

import com.github.projetoifsc.estagios.app.configs.OpenApiConfig;
import com.github.projetoifsc.estagios.app.model.response.wrapper.SuccessResponse;
import com.github.projetoifsc.estagios.app.model.response.AuthToken;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.app.service.AuthenticationService;
import com.github.projetoifsc.estagios.app.model.request.AuthLoginRequest;
import com.github.projetoifsc.estagios.app.service.ResponseEntityService;
import com.github.projetoifsc.estagios.app.utils.MediaTypes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.github.projetoifsc.estagios.app.configs.OpenApiConfig.AUTH;
import static com.github.projetoifsc.estagios.app.configs.OpenApiConfig.AUTHORIZATION;

@RestController
@RequestMapping(
        value = OpenApiConfig.BASE_URL + "/auth",
        produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML,
                MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS }
)
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final ResponseEntityService responseEntity;

    public AuthenticationController(AuthenticationService authenticationService, ResponseEntityService responseEntity) {
        this.authenticationService = authenticationService;
        this.responseEntity = responseEntity;
    }


    @PostMapping(value = "/login")
    @Operation(tags = AUTH)
    public ResponseEntity<SuccessResponse<AuthToken>> login(
            @RequestBody @Validated AuthLoginRequest authLoginRequest
    ) {
        return responseEntity.successResponse(
                authenticationService.attemptLogin(authLoginRequest)
        );
    }


    @PostMapping(value = "/refresh_token")
    @Operation(tags = AUTH)
    public ResponseEntity<SuccessResponse<AuthToken>> refreshToken(
            @RequestBody @Validated AuthToken authRefreshTokenRequest
            ) {
        return responseEntity.successResponse(
                authenticationService.refreshToken(authRefreshTokenRequest)
        );
    }


}
