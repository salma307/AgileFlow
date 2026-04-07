package com.backend.backend.dto.Workspace;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceDTO {

    private String id;
    private String name;
    private String slug;

    private String ownerId;
    private String ownerName;

    private LocalDateTime createdAt;

}