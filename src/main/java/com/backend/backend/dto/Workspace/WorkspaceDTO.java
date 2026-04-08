package com.backend.backend.dto.workspace;

import com.backend.backend.dao.entities.Space;
import com.backend.backend.dao.entities.WorkspaceMember;
import com.backend.backend.dto.space.SpaceDto;
import com.backend.backend.dto.workspaceMember.WorkspaceMemberDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceDto {
    private String id;
    private String name;
    private String slug;

    private String ownerId;
    private String ownerName;

    private LocalDateTime createdAt;

    private List<WorkspaceMemberDto> workspaceMembers;
    private List<SpaceDto> spaces;

}
