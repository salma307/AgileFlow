package com.backend.backend.dto.Workspace;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceResponseDTO {

    private String id;
    private String name;
    private String slug;
    private String ownerName;
}