package com.github.projetoifsc.estagios.app.exception;

import com.github.projetoifsc.estagios.core.implementation.InvalidReceiverException;
import com.github.projetoifsc.estagios.core.implementation.UnauthorizedAccessException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAll(Exception ex, WebRequest webRequest) {
        return buildResponseEntity(ex, webRequest, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> handleUnauthorized(Exception ex, WebRequest webRequest) {
        return buildResponseEntity(ex, webRequest, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({AccessDeniedException.class, UnauthorizedAccessException.class})
    public final ResponseEntity<ExceptionResponse> handleForbidden(Exception ex, WebRequest webRequest) {
        return buildResponseEntity(ex, webRequest, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({InvalidException.class, InvalidReceiverException.class})
    public final ResponseEntity<ExceptionResponse> handleBadRequest(Exception ex, WebRequest webRequest) {
        return buildResponseEntity(ex, webRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public final ResponseEntity<ExceptionResponse> handleNotFound(Exception ex, WebRequest webRequest) {
        return buildResponseEntity(ex, webRequest, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ExceptionResponse> buildResponseEntity(Exception ex, WebRequest webRequest, HttpStatusCode code) {
        var exceptionResponse = new ExceptionResponse(
                code,
                ex.getMessage(),
                webRequest.getDescription(false),
                new Date()
        );
        return new ResponseEntity<>(exceptionResponse,code);
    }


}
