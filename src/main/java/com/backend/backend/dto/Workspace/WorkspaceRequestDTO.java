package com.backend.backend.dto.Workspace;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkspaceRequestDTO {
    private String name;
    private String slug;
}
