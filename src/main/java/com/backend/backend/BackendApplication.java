package com.backend.backend;

import com.backend.backend.dao.entities.*;
import com.backend.backend.dao.repositories.*;
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
	                        WorkspaceRepository workspaceRepository,
	                        SpaceRepository spaceRepository,
	                        FolderRepository folderRepository,
	                        PasswordEncoder passwordEncoder) {
		return args -> {
			// 1. Créer les utilisateurs
			User admin = createUserIfMissing(userRepository, passwordEncoder,
					"admin@ensam-casa.ma", "Admin", "System", "ADMIN", "admin1234");

			createUserIfMissing(userRepository, passwordEncoder,
					"prof@ensam-casa.ma", "Prof", "Demo", "USER", "prof1234");

			// 2. Créer un Workspace pour l'admin
			Workspace defaultWorkspace = createWorkspaceIfMissing(workspaceRepository, admin,
					"Mon Workspace Admin", "admin-workspace");

			// 3. Créer un Space dans ce Workspace
			Space mainSpace = createSpaceIfMissing(spaceRepository, defaultWorkspace,
					"Projets 2026", false);

			// 4. Créer un Folder dans ce Space
			createFolderIfMissing(folderRepository, mainSpace, "Cours Maintenance", false);

			System.out.println(">>> [DataSeed] Hiérarchie de test créée avec succès !");
		};
	}

	// --- HELPER METHODS ---

	private User createUserIfMissing(UserRepository repo, PasswordEncoder encoder, String email,
	                                 String fName, String lName, String role, String pass) {
		return repo.findByEmail(email).orElseGet(() -> {
			User user = new User();
			user.setEmail(email);
			user.setFirstName(fName);
			user.setLastName(lName);
			user.setName(fName + " " + lName);
			user.setRole(role);
			user.setPassword(encoder.encode(pass));
			user.setCreatedAt(LocalDateTime.now());
			System.out.println("Seed: Utilisateur créé -> " + email);
			return repo.save(user);
		});
	}

	private Workspace createWorkspaceIfMissing(WorkspaceRepository repo, User owner, String name, String slug) {
		return repo.findByName(name).orElseGet(() -> {
			Workspace ws = new Workspace();
			ws.setName(name);
			ws.setSlug(slug);
			ws.setUser(owner);
			ws.setCreatedAt(LocalDateTime.now());
			System.out.println("Seed: Workspace créé -> " + name);
			return repo.save(ws);
		});
	}

	private Space createSpaceIfMissing(SpaceRepository repo, Workspace ws, String name, boolean isPrivate) {
		// On cherche par nom ET par workspace pour éviter les doublons
		return repo.findByName(name).orElseGet(() -> {
			Space space = new Space();
			space.setName(name);
			space.setPrivate(isPrivate);
			space.setWorkspace(ws);
			System.out.println("Seed: Space créé -> " + name);
			return repo.save(space);
		});
	}

	private void createFolderIfMissing(FolderRepository repo, Space space, String name, boolean isHidden) {
		if (repo.findByName(name).isEmpty()) {
			Folder folder = new Folder();
			folder.setName(name);
			folder.setHidden(isHidden);
			folder.setSpace(space);
			repo.save(folder);
			System.out.println("Seed: Folder créé -> " + name);
		}
	}
}