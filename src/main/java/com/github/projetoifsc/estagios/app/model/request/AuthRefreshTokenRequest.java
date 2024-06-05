package com.github.projetoifsc.estagios.app.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthRefreshTokenRequest {

    @JsonProperty(value = "refresh_token")
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
