package com.backend.backend.mapper;

import com.backend.backend.dao.entities.Workspace;
import com.backend.backend.dto.workspace.WorkspaceResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class WorkspaceMapper {
    @Autowired
    private ModelMapper modelMapper;

    // Entity to DTO
    public WorkspaceResponseDto toDto(Workspace workspace) {
        if (workspace == null) return null;
        return modelMapper.map(workspace, WorkspaceResponseDto.class);
    }

    // DTO to Entity
    public Workspace toEntity(WorkspaceResponseDto workspaceDTO) {
        if (workspaceDTO == null) return null;
        Workspace workspace = modelMapper.map(workspaceDTO, Workspace.class);

        workspace.setSpaces(null);
        workspace.setWorkspaceMembers(null);

        return workspace;
    }
}
