package com.github.projetoifsc.estagios.app.model.response.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Wrapper to build standardized JSend JSON success response
 * <br><br>See project: <a href="https://github.com/omniti-labs/jsend">github.com/omniti-labs/jsend</a>
 */
@JsonPropertyOrder({"status", "data"})
public class SuccessResponse<T> implements JSendResponse {

    private final T data;

    public SuccessResponse(T data) {
        this.data = data;
    }

    @JsonProperty("status")
    @Schema(example = "success")
    public String getStatus() {
        return "success";
    }

    public T getData() {
        return data;
    }

}
