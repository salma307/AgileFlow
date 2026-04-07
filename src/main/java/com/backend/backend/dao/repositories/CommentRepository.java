package com.backend.backend.dao.repositories;

import com.backend.backend.dao.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,String> {
}
