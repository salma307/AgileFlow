package com.backend.backend.dao.repositories;

import com.backend.backend.dao.entities.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment,String> {
}
