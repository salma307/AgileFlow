package com.backend.backend.mapper;

import com.backend.backend.dao.entities.User;
import com.backend.backend.dao.entities.Workspace;
import com.backend.backend.dao.repositories.UserRepository;
import com.backend.backend.dto.workspace.WorkspaceRequestDto;
import com.backend.backend.dto.workspace.WorkspaceResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class WorkspaceMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Workspace requesttoEntity(WorkspaceRequestDto dto) {
        if (dto == null) return null;
        return modelMapper.map(dto, Workspace.class);
    }

    public WorkspaceResponseDto toResponseDto(Workspace workspace) {
        if (workspace == null) return null;

        WorkspaceResponseDto dto = modelMapper.map(workspace, WorkspaceResponseDto.class);

        dto.setOwnerName(workspace.getUser().getName());

        return dto;
    }
}