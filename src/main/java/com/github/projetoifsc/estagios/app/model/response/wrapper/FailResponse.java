package com.github.projetoifsc.estagios.app.model.response.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Wrapper to build standardized JSend JSON fail response
 * <br><br>See project: <a href="https://github.com/omniti-labs/jsend">github.com/omniti-labs/jsend</a>
 */
@JsonPropertyOrder({"status", "data"})
public class FailResponse<T> implements JSendResponse{

    private final T data;

    public FailResponse(T data) {
        this.data = data;
    }

    @Override
    @JsonProperty("status")
    @Schema(example = "fail")
    public String getStatus() {
        return "fail";
    }

    @Override
    public T getData() {
        return data;
    }

}
