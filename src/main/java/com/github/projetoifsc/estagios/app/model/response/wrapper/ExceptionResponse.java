package com.github.projetoifsc.estagios.app.model.response.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * Wrapper to build standardized JSend JSON error response
 * <br><br>See project: <a href="https://github.com/omniti-labs/jsend">github.com/omniti-labs/jsend</a>
 */
@JsonPropertyOrder({"status", "code", "message", "data"})
public class ExceptionResponse implements JSendResponse {

    private final HttpStatus code;
    private final String message;
    private final Data data;

    public ExceptionResponse(HttpStatus code, String message, String details) {
        this.code = code;
        this.message = message;
        this.data = new Data(details, new Date());
    }


    @Override
    @JsonProperty("status")
    @Schema(example = "error")
    public String getStatus() {
        return "error";
    }

    public int getCode() {
        return code.value();
    }

    public String getMessage() {
        return message;
    }

    public Data getData() {
        return data;
    }

    public static class Data {

        private final String details;
        private final Date timestamp;

        private Data(String details, Date timestamp) {
            this.details = details;
            this.timestamp = timestamp;
        }

        public String getDetails() {
            return details;
        }

        public Date getTimestamp() {
            return timestamp;
        }

    }

}
