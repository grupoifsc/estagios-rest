package com.github.projetoifsc.estagios.app.security.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class JwtIssuer {

    private final JwtProperties jwtProperties;

    public JwtIssuer(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String issueAccessToken(String userId, List<String> roles) {
        return JWT.create()
                .withSubject(userId)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(Duration.of(jwtProperties.getAccessTokenExpirationMinutes(), ChronoUnit.MINUTES)))
                .withClaim("a", roles)
                .sign(Algorithm.HMAC256(jwtProperties.getAccessTokenSecretKey()));
    }


    public String issueRefreshToken(String userId, List<String> roles) {
        return JWT.create()
                .withSubject(userId)
                .withJWTId(userId + Instant.now().toString())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(Duration.of(jwtProperties.getRefreshTokenExpirationMinutes(), ChronoUnit.MINUTES)))
                .withClaim("a", roles)
                .sign(Algorithm.HMAC256(jwtProperties.getRefreshTokenSecretKey()));
    }


}
