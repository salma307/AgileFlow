package com.backend.backend.dao.repositories;

import com.backend.backend.dao.entities.SpaceMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceMemberRepository extends JpaRepository<SpaceMember,String> {
}
