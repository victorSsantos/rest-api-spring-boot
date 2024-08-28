package br.com.java.rest.api.services.exceptions;

public class DataIntegrityException extends RuntimeException {
    public DataIntegrityException(String message) {
        super(message);
    }
}
