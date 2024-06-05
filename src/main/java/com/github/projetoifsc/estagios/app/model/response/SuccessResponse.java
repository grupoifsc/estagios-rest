package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder({"status", "data"})
public class SuccessResponse {

    private final Serializable data;

    public SuccessResponse(Serializable data) {
        this.data = data;
    }

    @JsonProperty("status")
    public String getStatus() {
        return "success";
    }

    public Serializable getData() {
        return data;
    }

}
