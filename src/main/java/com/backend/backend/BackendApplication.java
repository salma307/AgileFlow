package com.backend.backend;

import com.backend.backend.dao.entities.User;
import com.backend.backend.dao.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner start(UserRepository userRepository,
	                        PasswordEncoder passwordEncoder) {
		return args -> {
			createUserIfMissing(userRepository, passwordEncoder,
					"admin@ensam-casa.ma", "Admin", "System", "ADMIN", "admin1234");
			createUserIfMissing(userRepository, passwordEncoder,
					"prof@ensam-casa.ma", "Prof", "Demo", "USER", "prof1234");
			createUserIfMissing(userRepository, passwordEncoder,
					"student@ensam-casa.ma", "Student", "Demo", "USER", "student1234");

			System.out.println("[DataSeed] Vérification des 3 users de base terminée.");
		};
	}

	private void createUserIfMissing(UserRepository userRepository,
	                                 PasswordEncoder passwordEncoder,
	                                 String email,
	                                 String firstName,
	                                 String lastName,
	                                 String role,
	                                 String rawPassword) {
		if (userRepository.existsByEmail(email)) {
			return;
		}

		User user = new User();
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setName(firstName + " " + lastName);
		user.setRole(role);
		user.setPassword(passwordEncoder.encode(rawPassword));
		user.setCreatedAt(LocalDateTime.now());
		userRepository.save(user);
	}
}
