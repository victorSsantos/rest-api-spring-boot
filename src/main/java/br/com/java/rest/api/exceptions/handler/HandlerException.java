package br.com.java.rest.api.exceptions.handler;

import br.com.java.rest.api.exceptions.InvalidJwtAuthException;
import br.com.java.rest.api.exceptions.StandardException;
import br.com.java.rest.api.exceptions.DataIntegrityException;
import br.com.java.rest.api.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardException> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardException err = new StandardException(Instant.now(), status.value() , status, "Resource not found!!", request.getServletPath());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardException> dataIntegrity(DataIntegrityException e,  HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardException err = new StandardException(Instant.now(), status.value() , status, e.getMessage(), request.getServletPath());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidJwtAuthException.class)
    public ResponseEntity<StandardException> invalidJwtAuthentication(InvalidJwtAuthException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardException err = new StandardException(Instant.now(), status.value() , status, e.getMessage(), request.getServletPath());
        return ResponseEntity.status(status).body(err);
    }
}
