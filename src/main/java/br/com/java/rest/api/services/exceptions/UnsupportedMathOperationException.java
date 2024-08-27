package br.com.java.rest.api.services.exceptions;

public class UnsupportedMathOperationException extends RuntimeException {
    public UnsupportedMathOperationException(String message) {
        super(message);
    }
}
