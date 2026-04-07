package com.backend.backend.dao.repositories;

import com.backend.backend.dao.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<String, Task> {
}
