package com.backend.backend.dao.repositories;

import com.backend.backend.dao.entities.Space;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceRepository extends JpaRepository<String, Space> {
}
