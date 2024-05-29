package com.github.projetoifsc.estagios.app.controller;

import com.github.projetoifsc.estagios.app.security.UserPrincipal;
import com.github.projetoifsc.estagios.app.service.AuthenticationService;
import com.github.projetoifsc.estagios.app.utils.swagger.SwaggerTags;
import com.github.projetoifsc.estagios.app.model.request.LoginRequest;
import com.github.projetoifsc.estagios.app.model.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = SwaggerTags.BASE_URL + "/auth"
        //,
//        produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML,
//                MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS }
)
public class AuthController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/login")
    public LoginResponse login(
            @RequestBody @Validated LoginRequest loginRequest
    ) {
        return authenticationService.attemptLogin(loginRequest);
    }



    @GetMapping("/test")
    public String testAuth(@AuthenticationPrincipal UserPrincipal principal) {
        return "User Id: " + principal.getUserId()
                + " / Email: " + principal.getEmail();
    }

    @GetMapping("/admin")
    public String adminTest(@AuthenticationPrincipal UserPrincipal principal) {
        return "You are a admin!";
    }

}
