package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.model.request.RefreshTokenRequest;
import com.github.projetoifsc.estagios.app.security.JwtDecoder;
import com.github.projetoifsc.estagios.app.security.JwtIssuer;
import com.github.projetoifsc.estagios.app.security.JwtToPrincipalConverter;
import com.github.projetoifsc.estagios.app.security.UserPrincipal;
import com.github.projetoifsc.estagios.app.model.request.LoginRequest;
import com.github.projetoifsc.estagios.app.model.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtIssuer jwtIssuer;
    private final JwtDecoder jwtDecoder;
    private final JwtToPrincipalConverter jwtToPrincipalConverter;


    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, JwtIssuer jwtIssuer, JwtDecoder jwtDecoder, JwtToPrincipalConverter jwtToPrincipalConverter) {
        this.authenticationManager = authenticationManager;
        this.jwtIssuer = jwtIssuer;
        this.jwtDecoder = jwtDecoder;
        this.jwtToPrincipalConverter = jwtToPrincipalConverter;
    }

    public TokenResponse attemptLogin(LoginRequest loginRequest) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        var principal = (UserPrincipal) authentication.getPrincipal();

        return tokensForPrincipal(principal);
    }


    public TokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        return Optional.of(refreshTokenRequest.getRefreshToken())
                .map(jwtDecoder::decodeRefreshToken)
                .map(jwtToPrincipalConverter::convert)
                .map(this::tokensForPrincipal)
                .orElseThrow();
    }

    private TokenResponse tokensForPrincipal(UserPrincipal principal) {
        var roles = principal.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .toList();

        var accessToken = jwtIssuer.issueAccessToken(
                principal.getUserId(),
                principal.getEmail(),
                roles
        );

        var refreshToken = jwtIssuer.issueRefreshToken(principal.getUserId());

        var tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return tokenResponse;
    }

}
