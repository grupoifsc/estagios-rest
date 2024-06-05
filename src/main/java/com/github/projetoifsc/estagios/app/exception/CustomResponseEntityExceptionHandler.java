package com.github.projetoifsc.estagios.app.exception;

import com.github.projetoifsc.estagios.app.security.ratelimit.RateLimitException;
import com.github.projetoifsc.estagios.core.implementation.InvalidReceiverException;
import com.github.projetoifsc.estagios.core.implementation.UnauthorizedAccessException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final Map<Class<?>, HttpStatus> exceptionsStatusCodes = new HashMap<>(
            Map.ofEntries(
                    Map.entry(Exception.class, HttpStatus.INTERNAL_SERVER_ERROR),
                    Map.entry(RateLimitException.class, HttpStatus.TOO_MANY_REQUESTS),
                    Map.entry(AuthenticationException.class, HttpStatus.UNAUTHORIZED),
                    Map.entry(AccessDeniedException.class, HttpStatus.FORBIDDEN),
                    Map.entry(InvalidException.class, HttpStatus.BAD_REQUEST),
                    Map.entry(EntityNotFoundException.class, HttpStatus.NOT_FOUND)
            )
    );

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAll(Exception ex, WebRequest webRequest) {
        return buildErrorResponseEntity(ex, webRequest.getDescription(false), exceptionsStatusCodes.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    public final ResponseEntity<ExceptionResponse> handleAll(Exception ex, String description) {
        return buildErrorResponseEntity(ex, description, exceptionsStatusCodes.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR));
    }


    @ExceptionHandler(RateLimitException.class)
    public final ResponseEntity<ExceptionResponse> handleTooManyRequests(Exception ex, WebRequest webRequest) {
        return buildErrorResponseEntity(ex, webRequest.getDescription(false), HttpStatus.TOO_MANY_REQUESTS);
    }


    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> handleUnauthorized(Exception ex, WebRequest webRequest) {
        return buildErrorResponseEntity(ex, webRequest.getDescription(false), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({AccessDeniedException.class, UnauthorizedAccessException.class})
    public final ResponseEntity<ExceptionResponse> handleForbidden(Exception ex, WebRequest webRequest) {
        return buildErrorResponseEntity(ex, webRequest.getDescription(false), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({InvalidException.class, InvalidReceiverException.class})
    public final ResponseEntity<ExceptionResponse> handleBadRequest(Exception ex, WebRequest webRequest) {
        return buildErrorResponseEntity(ex, webRequest.getDescription(false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public final ResponseEntity<ExceptionResponse> handleNotFound(Exception ex, WebRequest webRequest) {
        return buildErrorResponseEntity(ex, webRequest.getDescription(false), HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<ExceptionResponse> buildErrorResponseEntity(Exception ex, String details, HttpStatus code) {
        var exceptionResponse = new ExceptionResponse(
                "error",
                code,
                ex.getMessage(),
                details
        );
        return new ResponseEntity<>(exceptionResponse,code);
    }


}
