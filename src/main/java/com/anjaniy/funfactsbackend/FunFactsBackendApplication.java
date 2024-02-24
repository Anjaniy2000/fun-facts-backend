package com.anjaniy.funfactsbackend;

import com.anjaniy.funfactsbackend.models.entities.UserRole;
import com.anjaniy.funfactsbackend.repositories.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class FunFactsBackendApplication implements CommandLineRunner {

    private final UserRoleRepository userRoleRepository;

    public FunFactsBackendApplication(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(FunFactsBackendApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        if(userRoleRepository.findByName("ADMIN").isEmpty()) {
            userRoleRepository.save(new UserRole("ADMIN"));
        }

        if(userRoleRepository.findByName("MODERATOR").isEmpty()) {
            userRoleRepository.save(new UserRole("MODERATOR"));
        }

        if(userRoleRepository.findByName("USER").isEmpty()) {
            userRoleRepository.save(new UserRole("USER"));
        }

    }
}
