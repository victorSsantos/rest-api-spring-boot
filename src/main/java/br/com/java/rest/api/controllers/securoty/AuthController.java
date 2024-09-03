package br.com.java.rest.api.controllers.securoty;

import br.com.java.rest.api.services.dto.security.AccountCredentialsDTO;
import br.com.java.rest.api.services.security.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Authenticates a user and returns a token")
    @GetMapping(value = "/signin")
    public ResponseEntity signIn(@RequestParam(value = "username", defaultValue = "") String username,
                                 @RequestParam(value = "password", defaultValue = "") String password){

        if(username.isBlank() || password.isBlank()) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid clint request.");
        var token = authService.signin(new AccountCredentialsDTO(username,password));
        if(token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid clint request.");

        return token;
    }

}
