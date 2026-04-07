package com.backend.backend.dao.repositories;

import com.backend.backend.dao.entities.WorkspaceMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceMemberRepository extends JpaRepository<WorkspaceMember,String> {
}
