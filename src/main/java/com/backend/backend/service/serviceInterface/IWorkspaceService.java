package com.backend.backend.service.serviceInterface;

import com.backend.backend.dao.entities.Workspace;
import com.backend.backend.dto.Workspace.WorkspaceDTO;
import com.backend.backend.dto.Workspace.WorkspaceRequestDTO;
import com.backend.backend.dto.Workspace.WorkspaceResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IWorkspaceService {
    public WorkspaceResponseDTO addWorkspace(WorkspaceRequestDTO workspaceRequestDTO);
    public WorkspaceResponseDTO updateWorkspace(String id,WorkspaceRequestDTO workspaceRequestDTO);
    public void deleteWorkspace(String workspaceId);

    public WorkspaceResponseDTO getWorkspaceById(String id);
    public List<WorkspaceResponseDTO> getAllWorkspace();
    public Page<WorkspaceDTO> getAllWorkspaceSummaries(int page, int size);
}