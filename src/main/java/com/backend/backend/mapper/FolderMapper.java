package com.backend.backend.mapper;


import com.backend.backend.dao.entities.Folder;
import com.backend.backend.dao.repositories.SpaceRepository;
import com.backend.backend.dto.folder.FolderRequestDto;
import com.backend.backend.dto.folder.FolderResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FolderMapper {

    private final SpaceRepository spaceRepository;
    private ModelMapper modelMapper;

    public FolderMapper(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    public FolderResponseDto toResponseDto(Folder folder){

        FolderResponseDto folderResponseDto = modelMapper.map(folder, FolderResponseDto.class);

        return folderResponseDto;

    }

    public Folder toEntity(FolderRequestDto folderRequestDto){

        Folder folder = modelMapper.map(folderRequestDto,Folder.class);

        folder.setSpace(spaceRepository.getById(folderRequestDto.getSpaceId()));

        folder.setListes(null);

        return folder;
    }

}
