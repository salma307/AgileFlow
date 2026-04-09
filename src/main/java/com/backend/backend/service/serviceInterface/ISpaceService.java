package com.backend.backend.service.serviceInterface;

import com.backend.backend.dto.space.SpaceRequestDto;
import com.backend.backend.dto.space.SpaceResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ISpaceService {
    public SpaceResponseDto addSpace(SpaceRequestDto spaceDto);
    public SpaceResponseDto updateSpace(String id,SpaceRequestDto spaceDto);
    public boolean deleteSpace(String id);

    public SpaceResponseDto getSpaceByid(String spaceId);
    Page<SpaceResponseDto> getSpaces(Pageable pageable);
    public List<SpaceResponseDto> getSpaceByWorkspace(String workspaceid);

}
