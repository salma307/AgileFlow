package com.backend.backend;

import com.backend.backend.dao.entities.Folder;
import com.backend.backend.dao.entities.Space;
import com.backend.backend.dao.entities.User;
import com.backend.backend.dao.entities.Workspace;
import com.backend.backend.dao.repositories.FolderRepository;
import com.backend.backend.dao.repositories.SpaceRepository;
import com.backend.backend.dao.repositories.UserRepository;
import com.backend.backend.dao.repositories.WorkspaceRepository;
import com.backend.backend.dto.folder.FolderResponseDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class BackendApplication {

	private final WorkspaceRepository workspaceRepository;
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	public BackendApplication(WorkspaceRepository workspaceRepository) {
		this.workspaceRepository = workspaceRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner start(UserRepository userRepository,
	                        PasswordEncoder passwordEncoder, FolderRepository folderRepository, SpaceRepository spaceRepository) {
		return args -> {
			createUserIfMissing(userRepository, passwordEncoder,
					"admin@ensam-casa.ma", "Admin", "System", "ADMIN", "admin1234");
			createUserIfMissing(userRepository, passwordEncoder,
					"prof@ensam-casa.ma", "Prof", "Demo", "USER", "prof1234");
			createUserIfMissing(userRepository, passwordEncoder,
					"student@ensam-casa.ma", "Student", "Demo", "USER", "student1234");
//			createFolderIfMissing(folderRepository,spaceRepository,userRepository,"CRUD",false);
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
//	private void createFolderIfMissing(FolderRepository folderRepository,
//	                                   SpaceRepository spaceRepository,
//									   UserRepository userRepository,
//	                                   String folderName,
//	                                   boolean isHidden) {
//
//		User user = userRepository.findByName("admin")
//				.orElseGet(() -> {
//					User s = new User();
//					s.setEmail("admin@ensam-casa.ma");
//					s.setFirstName("Admin");
//					s.setLastName("System");
//					s.setName("ADMIN");
//					s.setPassword("admin1234");
//
//					return userRepository.save(s);
//				});
//
//		Workspace workspace = workspaceRepository.findByName("DefaultWorkspace")
//				.orElseGet(() -> {
//					Workspace s = new Workspace();
//					s.setName("DefaultWorkspace");
//					s.setSlug("DefaultSlug");
//					s.setCreatedAt(LocalDateTime.now());
//					s.setUser(user);
//
//				return workspaceRepository.save(s);
//				});
//		// Récupère un space existant ou crée-en un
//		Space space = spaceRepository.findByName("DefaultSpace")
//				.orElseGet(() -> {
//					Space s = new Space();
//					s.setName("DefaultSpace");
//					s.setPrivate(false); // exemple
//					s.setWorkspace(workspace); // si tu as besoin d'un workspace
//					return spaceRepository.save(s);
//				});
//
//		Folder folder = new Folder();
//		folder.setName(folderName);
//		folder.setHidden(isHidden);
//		folder.setSpace(space);
//
//		folderRepository.save(folder);
//	}
}
