package br.com.java.rest.api.config;

import br.com.java.rest.api.model.entities.Person;
import br.com.java.rest.api.model.enums.Gender;
import br.com.java.rest.api.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfigDB implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepo;

    @Override
    public void run(String... args) throws Exception {

        personRepo.deleteAll();

        Person p1 = new Person(null, "John", "Green", "190 Blackwood St.", Gender.Male);
        Person p2 = new Person(null, "Susy", "Rose", "320 Sunnysea St.", Gender.Female);

        personRepo.saveAll(Arrays.asList(p1,p2));
    }
}
