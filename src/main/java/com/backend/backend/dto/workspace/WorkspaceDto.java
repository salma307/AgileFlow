package com.backend.backend.dto.workspace;


import com.backend.backend.dto.space.SpaceDto;
import com.backend.backend.dto.workspaceMember.WorkspaceMemberDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class WorkspaceDto {

    private String id;
    private String name;
    private String slug;

    private String ownerName;

    private LocalDateTime createdAt;

    private List<WorkspaceMemberDTO> workspaceMembers;
    private List<SpaceDto> spaces;

}
