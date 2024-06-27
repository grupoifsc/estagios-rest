package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AuthToken implements Serializable {

    @JsonProperty(value = "access_token", access = JsonProperty.Access.READ_ONLY)
    private String accessToken;

    @JsonProperty(value = "refresh_token")
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
