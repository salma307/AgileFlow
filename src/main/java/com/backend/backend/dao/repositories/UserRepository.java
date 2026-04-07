package com.backend.backend.dao.repositories;

import com.backend.backend.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
