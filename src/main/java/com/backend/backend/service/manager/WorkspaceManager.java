package com.backend.backend.service.manager;

import com.backend.backend.dao.entities.Workspace;
import com.backend.backend.dao.repositories.WorkspaceRepository;
import com.backend.backend.dto.Workspace.WorkspaceDTO;
import com.backend.backend.mapper.WorkspaceMapper;
import com.backend.backend.service.serviceInterface.IWorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkspaceManager implements IWorkspaceService{

    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMapper workspaceMapper;

    @Override
    public void addWorkspace(Workspace workspace) {
        workspaceRepository.save(workspace);
    }

    @Override
    public void updateWorkspace(Workspace workspace) {
        if (workspaceRepository.existsById(workspace.getId())) {
            workspaceRepository.save(workspace);
        } else {
            throw new RuntimeException("Workspace non trouvé pour la mise à jour");
        }
    }

    @Override
    public void deleteWorkspace(String workspaceId) {
        workspaceRepository.deleteById(workspaceId);
    }

    @Override
    public WorkspaceDTO getWorkspaceById(String id) {
        Workspace workspace = workspaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workspace introuvable"));

        return workspaceMapper.toDto(workspace);
    }

    @Override
    public List<WorkspaceDTO> getAllWorkspace() {
        return workspaceRepository.findAll().stream()
                .map(workspaceMapper::toDto)
                .collect(Collectors.toList());
    }
}
