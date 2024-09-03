package br.com.java.rest.api.services.security;

import br.com.java.rest.api.model.security.User;
import br.com.java.rest.api.repositories.security.UserRepository;
import br.com.java.rest.api.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService implements UserDetailsService {

    private final Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository repo;

    public UserService(UserRepository repository) {
        repo = repository;
    }

    public User findById(Long id){
        return repo.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Finding one user by name " + username + "!");
        return repo.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found!"));
    }
}
