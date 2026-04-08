package com.backend.backend.service.manager;

import com.backend.backend.dao.entities.Space;
import com.backend.backend.dao.entities.Workspace;
import com.backend.backend.dao.repositories.SpaceRepository;
import com.backend.backend.dao.repositories.WorkspaceRepository;
import com.backend.backend.dto.space.SpaceDto;
import com.backend.backend.mapper.SpaceMapper;
import com.backend.backend.service.serviceInterface.ISpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpaceManager implements ISpaceService {

    private final SpaceRepository spaceRepository;
    private final SpaceMapper spaceMapper;
    private final WorkspaceRepository workspaceRepository;

    @Override
    public SpaceDto addSpace(SpaceDto spaceDto) {
        Space space = spaceMapper.toEntity(spaceDto);
        Workspace workspace=workspaceRepository.findById(spaceDto.getWorkspaceid()).orElseThrow(()->new IllegalArgumentException("Workspace not found"));
        space.setWorkspace(workspace);
        Space savedSpace = spaceRepository.save(space);
        return spaceMapper.toDto(savedSpace);

    }

    @Override
    public SpaceDto updateSpace(SpaceDto spaceDto) {
        Space space=spaceRepository.findById(spaceDto.getId()).orElseThrow(()->new IllegalArgumentException("Space not found"));
        space.setName(spaceDto.getSpaceName());
        space.setColor(spaceDto.getColor());
        space.setPrivate(spaceDto.isPrivate());
        if(spaceDto.getWorkspaceid()!=null){
            Workspace workspace=workspaceRepository.findById(spaceDto.getWorkspaceid()).orElseThrow(()->new IllegalArgumentException("Workspace not found"));
            space.setWorkspace(workspace);
        }
        Space savedSpace = spaceRepository.save(space);
        return spaceMapper.toDto(savedSpace);
    }

    @Override
    public boolean deleteSpace(String id) {
        if(id==null){
            return false;
        }
        spaceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Space not found"));
        spaceRepository.deleteById(id);
        return true;
    }

    @Override
    public SpaceDto getSpaceByid(String spaceId) {
        Space space=spaceRepository.findById(spaceId).orElseThrow(()->new IllegalArgumentException("Space not found"));

        return spaceMapper.toDto(space);
    }

    @Override
    public List<SpaceDto> getSpaces() {
        return spaceRepository.findAll().stream().map(spaceMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<SpaceDto> getSpaceByWorkspace(String workspaceid) {
        Workspace workspace=workspaceRepository.findById(workspaceid).orElseThrow(()->new IllegalArgumentException("Workspace not found"));
        return spaceRepository.findByWorkspace(workspace).stream().map(spaceMapper::toDto).collect(Collectors.toList());
    }
}
