package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.security.JwtIssuer;
import com.github.projetoifsc.estagios.app.security.UserPrincipal;
import com.github.projetoifsc.estagios.app.model.request.LoginRequest;
import com.github.projetoifsc.estagios.app.model.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtIssuer jwtIssuer;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, JwtIssuer jwtIssuer) {
        this.authenticationManager = authenticationManager;
        this.jwtIssuer = jwtIssuer;
    }

    public LoginResponse attemptLogin(LoginRequest loginRequest) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        var principal = (UserPrincipal) authentication.getPrincipal();

        var roles = principal.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .toList();

        var token = jwtIssuer.issue(
                principal.getUserId(),
                principal.getEmail(),
                roles
        );

        var loginResponse = new LoginResponse();
        loginResponse.setAccessToken(token);
        return loginResponse;
    }


}
