package com.github.projetoifsc.estagios.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder({"status", "data"})
public class SuccessResponseWrapper {

    private final Serializable data;

    public SuccessResponseWrapper(Serializable data) {
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
