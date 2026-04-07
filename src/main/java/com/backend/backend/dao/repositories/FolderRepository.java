package com.backend.backend.dao.repositories;

import com.backend.backend.dao.entities.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository  extends JpaRepository<String, Folder> {
}
