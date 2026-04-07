package com.backend.backend.dao.repositories;

import com.backend.backend.dao.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<String, Notification> {
}
