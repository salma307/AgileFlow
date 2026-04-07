package com.backend.backend;

import com.backend.backend.dao.entities.User;
import com.backend.backend.dao.entities.Workspace;
import com.backend.backend.dao.entities.WorkspaceMember;
import com.backend.backend.dao.enums.WorkspaceRole;
import com.backend.backend.dao.repositories.UserRepository;
import com.backend.backend.dao.repositories.WorkspaceMemberRepository;
import com.backend.backend.dao.repositories.WorkspaceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner start(UserRepository userRepository,
	                        WorkspaceRepository workspaceRepository,
	                        WorkspaceMemberRepository memberRepository) {
		return args -> {
			System.out.println("========== DÉBUT DU TEST JPA ==========");

			// 1. Création d'un Utilisateur
			User admin = new User();
			admin.setName("Ilyass");
			admin.setEmail("ilyass@ensam.ma");
			admin.setCreatedAt(LocalDateTime.now());
			userRepository.save(admin);
			System.out.println("Utilisateur créé avec ID: " + admin.getId());


			// 2. Création d'un Workspace
			Workspace ws = new Workspace();
			ws.setName("Projet IA");
			ws.setSlug("projet-ia-" + System.currentTimeMillis());
			ws.setCreatedAt(LocalDateTime.now());
			ws.setUser(admin);
			workspaceRepository.save(ws);
			System.out.println("Workspace créé: " + ws.getName());

			// 3. Ajout d'un membre (Liaison)
			WorkspaceMember member = new WorkspaceMember();
			member.setUser(admin);
			member.setWorkspace(ws);
			member.setRole(WorkspaceRole.ADMIN);
			member.setJoinedAt(LocalDateTime.now());
			memberRepository.save(member);
			System.out.println("Lien WorkspaceMember créé avec succès !");

			System.out.println("========== TEST RÉUSSI AVEC SUCCÈS ==========");
		};
	}
}