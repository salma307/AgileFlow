package com.backend.backend.dao.entities;

import com.backend.backend.dao.enums.SpaceRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "space_member")
public class SpaceMember {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(nullable = false, updatable = false)

    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpaceRole role;

    @Column(nullable = false)
    private LocalDateTime joinedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id",nullable = false)
    private Space space;
}
