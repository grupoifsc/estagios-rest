package com.github.projetoifsc.estagios.app.security.auth;

import com.github.projetoifsc.estagios.app.model.request.RefreshTokenRequest;
import com.github.projetoifsc.estagios.app.model.request.LoginRequest;
import com.github.projetoifsc.estagios.app.model.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtIssuer jwtIssuer;
    private final JwtDecoder jwtDecoder;
    private final JwtToPrincipalConverter jwtToPrincipalConverter;
    private final PasswordEncoder passwordEncoder;
    private final IAuthenticationDAO authenticationDB;

    private final String ADMIN_EMAIL = "admin@teste.com";
    private final String USER_EMAIL = "user@teste.com";


    public AuthenticationService(AuthenticationManager authenticationManager, JwtIssuer jwtIssuer, JwtDecoder jwtDecoder, JwtToPrincipalConverter jwtToPrincipalConverter, PasswordEncoder passwordEncoder, IAuthenticationDAO authenticationDB) {
        this.authenticationManager = authenticationManager;
        this.jwtIssuer = jwtIssuer;
        this.jwtDecoder = jwtDecoder;
        this.jwtToPrincipalConverter = jwtToPrincipalConverter;
        this.passwordEncoder = passwordEncoder;
        this.authenticationDB = authenticationDB;
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

        return generateTokensForPrincipal(principal);
    }


    public TokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        return Optional.of(refreshTokenRequest.getRefreshToken())
                .map(jwtDecoder::decodeRefreshToken)
                .map(jwtToPrincipalConverter::convert)
                .map(this::generateTokensForPrincipal)
                .orElseThrow();
    }


    private TokenResponse generateTokensForPrincipal(UserPrincipal principal) {
        var roles = principal.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .toList();

        var accessToken = jwtIssuer.issueAccessToken(
                principal.getId(),
                roles
        );

        var refreshToken = jwtIssuer.issueRefreshToken(principal.getId());

        var tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return tokenResponse;
    }


    public Optional<CustomUserDetails> findByUsername(String username) {
        if(username.equalsIgnoreCase(ADMIN_EMAIL) || username.equalsIgnoreCase(USER_EMAIL))
            return findMockedByEmail(username);
        return authenticationDB.findByUsername(username);
    }


    public Optional<CustomUserDetails> findMockedByEmail(String email) {
        if(email.equalsIgnoreCase(ADMIN_EMAIL)) {
            var password = passwordEncoder.encode("senha");
            var user = new UserPrincipal("1", ADMIN_EMAIL, password, List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
            return Optional.of(user);
        }
        var password = passwordEncoder.encode("senha");
        var user = new UserPrincipal("99", USER_EMAIL, password, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        return Optional.of(user);
    }


}
