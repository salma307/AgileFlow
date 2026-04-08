package com.backend.backend.mapper;

import com.backend.backend.dao.entities.Space;
import com.backend.backend.dto.space.SpaceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpaceMapper {
    @Autowired
    private ModelMapper modelMapper;

    public SpaceDto toDto (Space space) {
        if(space==null){
            return null;
        }
        SpaceDto spaceDto = modelMapper.map(space, SpaceDto.class);
        spaceDto.setSpaceName(space.getName());
        spaceDto.setWorkspaceid(space.getWorkspace().getId());
        spaceDto.setWorkspaceName(space.getWorkspace().getName());
        return spaceDto;
    }

    public Space toEntity(SpaceDto spaceDto) {
        if(spaceDto==null){
            return null;
        }
        Space space = modelMapper.map(spaceDto, Space.class);
        space.setName(spaceDto.getSpaceName());
        space.setFolders(null);
        space.setWorkspace(null);
        return space;
    }
}
