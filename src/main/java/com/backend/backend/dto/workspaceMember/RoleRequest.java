package com.backend.backend.dto.workspaceMember;

import com.backend.backend.dao.enums.WorkspaceRole;
import lombok.Data;

@Data
public class RoleRequest {
    private WorkspaceRole role;

}