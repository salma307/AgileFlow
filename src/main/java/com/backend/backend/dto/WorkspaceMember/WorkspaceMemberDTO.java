package com.backend.backend.dto.WorkspaceMember;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkspaceMemberDTO {
    private String id;
    private String role;
    private String userName;
    private LocalDateTime joinedAt;
}
