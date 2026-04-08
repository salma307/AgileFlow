package com.backend.backend.service.serviceInterface;

import com.backend.backend.dto.workspace.WorkspaceDto;
import com.backend.backend.dto.workspace.WorkspaceRequestDto;
import com.backend.backend.dto.workspace.WorkspaceResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IWorkspaceService {
    public WorkspaceResponseDto addWorkspace(WorkspaceRequestDto workspaceRequestDTO);
    public WorkspaceResponseDto updateWorkspace(String id, WorkspaceRequestDto workspaceRequestDTO);
    public void deleteWorkspace(String workspaceId);

    public WorkspaceResponseDto getWorkspaceById(String id);
    public List<WorkspaceResponseDto> getAllWorkspace();
    public Page<WorkspaceDto> getAllWorkspaceSummaries(int page, int size);
}