package com.backend.backend.dao.repositories;

import com.backend.backend.dao.entities.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceRepository extends JpaRepository<Workspace,String> {

}
