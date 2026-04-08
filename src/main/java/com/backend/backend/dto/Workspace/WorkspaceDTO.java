package com.backend.backend.dto.Workspace;

import com.backend.backend.dao.entities.Space;
import com.backend.backend.dao.entities.WorkspaceMember;
import com.backend.backend.dto.Space.SpaceDTO;
import com.backend.backend.dto.WorkspaceMember.WorkspaceMemberDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class WorkspaceDTO {

    private String id;
    private String name;
    private String slug;

    private String ownerName;

    private LocalDateTime createdAt;

    private List<WorkspaceMemberDTO> workspaceMembers;
    private List<SpaceDTO> spaces;

}
