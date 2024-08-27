package br.com.java.rest.api.controllers.exceptions.handler;

import br.com.java.rest.api.controllers.exceptions.StandardException;
import br.com.java.rest.api.services.exceptions.UnsupportedMathOperationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UnsupportedMathOperationException.class)
    public ResponseEntity<StandardException> unsupportedMathOperation(UnsupportedMathOperationException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardException err = new StandardException(Instant.now(), status.value() , status, e.getMessage(), request.getServletPath());
        return ResponseEntity.status(status).body(err);
    }

}
