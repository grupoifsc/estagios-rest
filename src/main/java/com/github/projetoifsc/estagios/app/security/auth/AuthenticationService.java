package com.github.projetoifsc.estagios.app.security.auth;

import com.github.projetoifsc.estagios.app.model.request.AuthRefreshTokenRequest;
import com.github.projetoifsc.estagios.app.model.request.AuthLoginRequest;
import com.github.projetoifsc.estagios.app.model.response.AuthTokenResponse;
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

    public AuthenticationService(AuthenticationManager authenticationManager, JwtIssuer jwtIssuer, JwtDecoder jwtDecoder, JwtToPrincipalConverter jwtToPrincipalConverter) {
        this.authenticationManager = authenticationManager;
        this.jwtIssuer = jwtIssuer;
        this.jwtDecoder = jwtDecoder;
        this.jwtToPrincipalConverter = jwtToPrincipalConverter;
    }


    public AuthTokenResponse attemptLogin(AuthLoginRequest authLoginRequest) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authLoginRequest.getEmail(),
                        authLoginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        var principal = (UserPrincipal) authentication.getPrincipal();

        return generateTokensForPrincipal(principal);
    }


    public AuthTokenResponse refreshToken(AuthRefreshTokenRequest authRefreshTokenRequest) {
        return Optional.of(authRefreshTokenRequest.getRefreshToken())
                .map(jwtDecoder::decodeRefreshToken)
                .map(jwtToPrincipalConverter::convert)
                .map(this::generateTokensForPrincipal)
                .orElseThrow();
    }


    private AuthTokenResponse generateTokensForPrincipal(UserPrincipal principal) {
        var roles = principal.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .toList();

        var accessToken = jwtIssuer.issueAccessToken(
                principal.getId(),
                roles
        );

        var refreshToken = jwtIssuer.issueRefreshToken(principal.getId(), roles);

        var tokenResponse = new AuthTokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return tokenResponse;
    }

}
