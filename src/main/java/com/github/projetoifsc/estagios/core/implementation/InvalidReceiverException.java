package com.github.projetoifsc.estagios.core.implementation;

public class InvalidReceiverException extends IllegalArgumentException {

    public InvalidReceiverException(String message) {
        super(message);
    }

    public InvalidReceiverException(String message, Throwable cause) {
        super(message, cause);
    }

}
