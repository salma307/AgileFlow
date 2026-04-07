package com.backend.backend.dao.repositories;

import com.backend.backend.dao.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<String, Tag> {
}
