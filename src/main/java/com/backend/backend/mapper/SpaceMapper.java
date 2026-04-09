package com.backend.backend.mapper;

import com.backend.backend.dao.entities.Space;
import com.backend.backend.dto.space.SpaceRequestDto;
import com.backend.backend.dto.space.SpaceResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpaceMapper {
    @Autowired
    private ModelMapper modelMapper;

    public SpaceResponseDto toDto (Space space) {
        if(space==null){
            return null;
        }
        SpaceResponseDto spaceDto = modelMapper.map(space, SpaceResponseDto.class);
        spaceDto.setSpaceName(space.getName());
        spaceDto.setWorkspaceid(space.getWorkspace().getId());
        spaceDto.setWorkspaceName(space.getWorkspace().getName());
        spaceDto.setAdminid(space.getUser().getId());
        spaceDto.setAdminName(space.getUser().getName());
        return spaceDto;
    }

    public Space toEntity(SpaceRequestDto requestDto) {
        if(requestDto==null){
            return null;
        }
        Space space = modelMapper.map(requestDto, Space.class);
        space.setName(requestDto.getName());
        space.setFolders(null);
        space.setWorkspace(null);
        space.setUser(null);
        space.setSpaceMembers(null);
        return space;
    }
}
