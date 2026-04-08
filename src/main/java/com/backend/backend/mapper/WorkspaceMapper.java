package com.backend.backend.mapper;

import com.backend.backend.dao.entities.Workspace;
import com.backend.backend.dto.WorkspaceDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class WorkspaceMapper {
    @Autowired
    private ModelMapper modelMapper;

    // Entity to DTO
    public WorkspaceDTO toDto(Workspace workspace) {
        if (workspace == null) return null;
        return modelMapper.map(workspace, WorkspaceDTO.class);
    }

    // DTO to Entity
    public Workspace toEntity(WorkspaceDTO workspaceDTO) {
        if (workspaceDTO == null) return null;
        Workspace workspace = modelMapper.map(workspaceDTO, Workspace.class);

        workspace.setSpaces(null);
        workspace.setWorkspaceMembers(null);

        return workspace;
    }
}
