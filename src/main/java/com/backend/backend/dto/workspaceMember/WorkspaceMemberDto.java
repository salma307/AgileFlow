package com.backend.backend.dto.workspaceMember;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkspaceMemberDto {
    private String id;
    private String role;
    private String userName;
    private LocalDateTime joinedAt;
}
