package com.github.projetoifsc.estagios.app.security.ratelimit;

public class RateLimitException extends RuntimeException {
    public RateLimitException(String message) {
        super(message);
    }

}
