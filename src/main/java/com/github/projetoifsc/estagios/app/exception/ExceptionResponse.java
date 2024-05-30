package com.github.projetoifsc.estagios.app.exception;

import org.springframework.http.HttpStatusCode;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {

    private final HttpStatusCode status;
    private final String message;
    private final String details;
    private final Date timestamp;


    public ExceptionResponse(HttpStatusCode status, String message, String details, Date timestamp) {
        this.status = status;
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }


    public int getStatus() {
        return status.value();
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

}
