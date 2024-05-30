package com.github.projetoifsc.estagios.app.security.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("security.jwt")
public class JwtProperties {

    private String accessTokenSecretKey;
    private int accessTokenExpirationMinutes;
    private String refreshTokenSecretKey;
    private int refreshTokenExpirationMinutes;


    public String getAccessTokenSecretKey() {
        return accessTokenSecretKey;
    }


    public void setAccessTokenSecretKey(String accessTokenSecretKey) {
        this.accessTokenSecretKey = accessTokenSecretKey;
    }


    public int getAccessTokenExpirationMinutes() {
        return accessTokenExpirationMinutes;
    }


    public void setAccessTokenExpirationMinutes(int accessTokenExpirationMinutes) {
        this.accessTokenExpirationMinutes = accessTokenExpirationMinutes;
    }


    public String getRefreshTokenSecretKey() {
        return refreshTokenSecretKey;
    }


    public void setRefreshTokenSecretKey(String refreshTokenSecretKey) {
        this.refreshTokenSecretKey = refreshTokenSecretKey;
    }


    public int getRefreshTokenExpirationMinutes() {
        return refreshTokenExpirationMinutes;
    }


    public void setRefreshTokenExpirationMinutes(int refreshTokenExpirationMinutes) {
        this.refreshTokenExpirationMinutes = refreshTokenExpirationMinutes;
    }


}
