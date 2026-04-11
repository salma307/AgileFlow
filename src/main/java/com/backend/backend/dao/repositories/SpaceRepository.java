package com.backend.backend.dao.repositories;

import com.backend.backend.dao.entities.Space;
import com.backend.backend.dao.entities.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpaceRepository extends JpaRepository<Space,String> {
    List<Space> findByWorkspace(Workspace workspace);

    Optional<Space> findByName(String name);
}
