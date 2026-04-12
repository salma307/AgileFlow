package com.backend.backend.dto.workspaceMember;

import com.backend.backend.dao.enums.WorkspaceRole;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkspaceMemberRequestDto {
    private WorkspaceRole role;
    private String userId;
    private String workspaceId;
    private LocalDateTime joinedAt;
}
