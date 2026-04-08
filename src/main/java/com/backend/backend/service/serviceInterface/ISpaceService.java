package com.backend.backend.service.serviceInterface;

import com.backend.backend.dto.space.SpaceDto;

import java.util.List;

public interface ISpaceService {
    public SpaceDto addSpace(SpaceDto spaceDto);
    public SpaceDto updateSpace(SpaceDto spaceDto);
    public boolean deleteSpace(String id);

    public SpaceDto getSpaceByid(String spaceId);
    public List<SpaceDto> getSpaces();

    public List<SpaceDto> getSpaceByWorkspace(String workspaceid);

}
