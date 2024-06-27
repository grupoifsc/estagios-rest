package com.github.projetoifsc.estagios.app.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.github.projetoifsc.estagios.app.model.response.wrapper.ExceptionResponse;
import com.github.projetoifsc.estagios.app.security.ratelimit.RateLimitException;
import com.github.projetoifsc.estagios.core.implementation.InvalidReceiverException;
import com.github.projetoifsc.estagios.core.implementation.UnauthorizedAccessException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RateLimitException.class)
    public final ResponseEntity<ExceptionResponse> handleTooManyRequests(Exception ex, WebRequest webRequest) {
        return buildErrorResponseEntity(ex, webRequest.getDescription(false), HttpStatus.TOO_MANY_REQUESTS);
    }


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AuthenticationException.class, TokenExpiredException.class})
    public final ResponseEntity<ExceptionResponse> handleUnauthorized(Exception ex, WebRequest webRequest) {
        return buildErrorResponseEntity(ex, webRequest.getDescription(false),
                HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class, UnauthorizedAccessException.class})
    public final ResponseEntity<ExceptionResponse> handleForbidden(Exception ex, WebRequest webRequest) {
        return buildErrorResponseEntity(ex, webRequest.getDescription(false),
                HttpStatus.FORBIDDEN);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidException.class, InvalidReceiverException.class})
    public final ResponseEntity<ExceptionResponse> handleBadRequest(Exception ex, WebRequest webRequest) {
        return buildErrorResponseEntity(ex, webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class})
    public final ResponseEntity<ExceptionResponse> handleNotFound(Exception ex, WebRequest webRequest) {
        return buildErrorResponseEntity(ex, webRequest.getDescription(false),
                HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errorList);
        return handleExceptionInternal(ex, errorDetails, headers, errorDetails.status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errorList = List.of(ex.getHttpInputMessage().toString());

        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errorList);
        return handleExceptionInternal(ex, errorDetails, headers, errorDetails.status, request);
    }


    public ResponseEntity<ExceptionResponse> buildErrorResponseEntity(Exception ex, String details, HttpStatus code) {
        var exceptionResponse = new ExceptionResponse(
                code, ex.getMessage(), details
        );
        return new ResponseEntity<>(exceptionResponse,code);
    }


//    @Data
    public static class ErrorDetails {
        private HttpStatus status;
        private String message;
        private List<String> errors;

        public ErrorDetails(HttpStatus status, String message, List<String> errors) {
            super();
            this.status = status;
            this.message = message;
            this.errors = errors;
        }

        public ErrorDetails(HttpStatus status, String message, String error) {
            super();
            this.status = status;
            this.message = message;
            errors = Arrays.asList(error);
        }
    }


}
