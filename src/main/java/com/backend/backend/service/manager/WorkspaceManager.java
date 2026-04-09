package com.backend.backend.service.manager;

import com.backend.backend.dao.entities.Workspace;
import com.backend.backend.dao.repositories.WorkspaceRepository;
import com.backend.backend.dto.space.SpaceResponseDto;
import com.backend.backend.dto.workspace.WorkspaceDto;
import com.backend.backend.dto.workspace.WorkspaceRequestDto;
import com.backend.backend.dto.workspace.WorkspaceResponseDto;
import com.backend.backend.dto.workspaceMember.WorkspaceMemberDto;
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
    public WorkspaceResponseDto addWorkspace(WorkspaceRequestDto workspaceRequestDTO) {
        Workspace workspaceEntity = modelMapper.map(workspaceRequestDTO,Workspace.class);
        workspaceEntity.setCreatedAt(LocalDateTime.now());
        workspaceRepository.save(workspaceEntity);
        WorkspaceResponseDto workspaceResponseDTO = modelMapper.map(workspaceEntity, WorkspaceResponseDto.class);
        return workspaceResponseDTO;
    }

    @Override
    public WorkspaceResponseDto updateWorkspace(String id, WorkspaceRequestDto workspaceRequestDTO) {

        Workspace existingWorkspace = workspaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workspace introuvable"));

        Workspace updatedEntity = workspaceRepository.save(existingWorkspace);

        WorkspaceResponseDto workspaceResponseDTO = modelMapper.map(updatedEntity, WorkspaceResponseDto.class);

        return workspaceResponseDTO;
    }

    @Override
    public void deleteWorkspace(String workspaceId) {
        workspaceRepository.deleteById(workspaceId);
    }

    @Override
    public WorkspaceResponseDto getWorkspaceById(String id) {
        Workspace workspace = workspaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workspace introuvable"));

        return workspaceMapper.toDto(workspace);
    }

    @Override
    public List<WorkspaceResponseDto> getAllWorkspace() {
        return workspaceRepository.findAll().stream()
                .map(workspaceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<WorkspaceDto> getAllWorkspaceSummaries(int page, int size) {
        Page<Workspace> workspaces = workspaceRepository.findAllByOrderByIdDesc(PageRequest.of(page, size));

        return workspaces.map(w -> {
            WorkspaceDto dto = modelMapper.map(w, WorkspaceDto.class);

            if (w.getUser() != null) {
                dto.setOwnerName(w.getUser().getName());
            }

            if (w.getWorkspaceMembers() != null) {
                dto.setWorkspaceMembers(w.getWorkspaceMembers().stream()
                        .map(m -> {
                            WorkspaceMemberDto wmDto = new WorkspaceMemberDto();
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
                            SpaceResponseDto sDto = new SpaceResponseDto();
                            sDto.setId(s.getId());
                            sDto.setSpaceName(s.getName());
                            sDto.setColor(s.getColor());
                            sDto.setPrivate(s.isPrivate());
                            return sDto;
                        }).toList());
            }

            return dto;
        });
    }
}
