package com.backend.backend.dto.workspace;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceResponseDto {

    private String id;
    private String name;
    private String slug;
    private String ownerName;
}