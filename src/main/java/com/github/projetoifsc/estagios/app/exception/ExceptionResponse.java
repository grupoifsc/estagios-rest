package com.github.projetoifsc.estagios.app.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * Follows JSend Specification
 * https://github.com/omniti-labs/jsend
 */
public class ExceptionResponse implements Serializable {


    private final String status;
    private final HttpStatus code;
    private final String message;
    private final Data data;

    public ExceptionResponse(String status, HttpStatus code, String message, String details) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = new Data(details, new Date());
    }

    public String getStatus() {
        return status;
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
