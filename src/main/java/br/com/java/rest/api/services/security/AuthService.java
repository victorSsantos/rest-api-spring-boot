package br.com.java.rest.api.services.security;

import br.com.java.rest.api.repositories.security.UserRepository;
import br.com.java.rest.api.security.jwt.JwtTokenProvider;
import br.com.java.rest.api.services.dto.security.AccountCredentialsDTO;
import br.com.java.rest.api.services.dto.security.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository repository;

    public ResponseEntity<TokenDTO> signin(AccountCredentialsDTO data){
        try{
            var username = data.getUserName();
            var password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

            var user = repository.findByUserName(username);
            var tokenResponse = new TokenDTO();

            if(user.isPresent()){
                tokenResponse = jwtTokenProvider.createAccessToken(username, user.get().getRoles());
            }
            else{
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }

            return ResponseEntity.ok(tokenResponse);
        }
        catch(Exception e ){
            throw new BadCredentialsException("Invalid Username/Password supplied!");
        }
    }



}
