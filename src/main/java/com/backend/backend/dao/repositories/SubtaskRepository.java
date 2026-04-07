package com.backend.backend.dao.repositories;

import com.backend.backend.dao.entities.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubtaskRepository extends JpaRepository<Subtask,String> {
}
