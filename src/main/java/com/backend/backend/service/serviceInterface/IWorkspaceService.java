package com.backend.backend.service.serviceInterface;

import com.backend.backend.dao.entities.Workspace;
import com.backend.backend.dto.WorkspaceDTO;

import java.util.List;

public interface IWorkspaceService {
    public void addWorkspace(Workspace workspace);
    public void updateWorkspace(Workspace workspace);
    public void deleteWorkspace(String workspaceId);

    public WorkspaceDTO getWorkspaceById(String id);
    public List<WorkspaceDTO> getAllWorkspace();
}