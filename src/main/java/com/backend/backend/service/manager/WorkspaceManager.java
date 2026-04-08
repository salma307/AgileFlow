package com.backend.backend.service.manager;

import com.backend.backend.dao.entities.Workspace;
import com.backend.backend.dao.repositories.WorkspaceRepository;
import com.backend.backend.dto.Space.SpaceDTO;
import com.backend.backend.dto.Workspace.WorkspaceDTO;
import com.backend.backend.dto.Workspace.WorkspaceResponseDTO;
import com.backend.backend.dto.Workspace.WorkspaceRequestDTO;
import com.backend.backend.dto.WorkspaceMember.WorkspaceMemberDTO;
import com.backend.backend.mapper.WorkspaceMapper;
import com.backend.backend.service.serviceInterface.IWorkspaceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkspaceManager implements IWorkspaceService{

    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMapper workspaceMapper;
    private final ModelMapper modelMapper;

    @Override
    public WorkspaceResponseDTO addWorkspace(WorkspaceRequestDTO workspaceRequestDTO) {
        Workspace workspaceEntity = modelMapper.map(workspaceRequestDTO,Workspace.class);
        workspaceEntity.setCreatedAt(LocalDateTime.now());
        workspaceRepository.save(workspaceEntity);
        WorkspaceResponseDTO workspaceResponseDTO = modelMapper.map(workspaceEntity,WorkspaceResponseDTO.class);
        return workspaceResponseDTO;
    }

    @Override
    public WorkspaceResponseDTO updateWorkspace(String id, WorkspaceRequestDTO workspaceRequestDTO) {

        Workspace existingWorkspace = workspaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workspace introuvable"));

        Workspace updatedEntity = workspaceRepository.save(existingWorkspace);

        WorkspaceResponseDTO workspaceResponseDTO = modelMapper.map(updatedEntity,WorkspaceResponseDTO.class);

        return workspaceResponseDTO;
    }

    @Override
    public void deleteWorkspace(String workspaceId) {
        workspaceRepository.deleteById(workspaceId);
    }

    @Override
    public WorkspaceResponseDTO getWorkspaceById(String id) {
        Workspace workspace = workspaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workspace introuvable"));

        return workspaceMapper.toDto(workspace);
    }

    @Override
    public List<WorkspaceResponseDTO> getAllWorkspace() {
        return workspaceRepository.findAll().stream()
                .map(workspaceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<WorkspaceDTO> getAllWorkspaceSummaries(int page, int size) {
        Page<Workspace> workspaces = workspaceRepository.findAllByOrderByIdDesc(PageRequest.of(page, size));

        return workspaces.map(w -> {
            WorkspaceDTO dto = modelMapper.map(w, WorkspaceDTO.class);

            if (w.getUser() != null) {
                dto.setOwnerName(w.getUser().getName());
            }

            if (w.getWorkspaceMembers() != null) {
                dto.setWorkspaceMembers(w.getWorkspaceMembers().stream()
                        .map(m -> {
                            WorkspaceMemberDTO wmDto = new WorkspaceMemberDTO();
                            wmDto.setId(m.getId());
                            wmDto.setRole(m.getRole().name());
                            wmDto.setJoinedAt(m.getJoinedAt());
                            if (m.getUser() != null) {
                                wmDto.setUserName(m.getUser().getName());
                            }
                            return wmDto;
                        }).toList());
            }

            // 5. Mapping des Spaces
            if (w.getSpaces() != null) {
                dto.setSpaces(w.getSpaces().stream()
                        .map(s -> {
                            SpaceDTO sDto = new SpaceDTO();
                            sDto.setId(s.getId());
                            sDto.setName(s.getName());
                            sDto.setColor(s.getColor());
                            sDto.setPrivate(s.isPrivate());
                            return sDto;
                        }).toList());
            }

            return dto;
        });
    }
}
