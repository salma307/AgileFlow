package com.backend.backend.service.manager;


import com.backend.backend.dao.entities.Folder;
import com.backend.backend.dao.entities.Workspace;
import com.backend.backend.dao.repositories.FolderRepository;
import com.backend.backend.dao.repositories.SpaceRepository;
import com.backend.backend.dto.folder.FolderRequestDto;
import com.backend.backend.dto.folder.FolderResponseDto;
import com.backend.backend.mapper.FolderMapper;
import com.backend.backend.service.serviceInterface.IFolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderManager implements IFolderService {

    private final FolderMapper folderMapper;
    private final FolderRepository folderRepository;
    private final SpaceRepository spaceRepository;


    @Override
    public FolderResponseDto addFolder(FolderRequestDto folderRequestDTO) {
        Folder folder = folderMapper.toEntity(folderRequestDTO);

        folderRepository.save(folder);

        FolderResponseDto folderResponseDto = folderMapper.toResponseDto(folder);

        return folderResponseDto;

    }

    @Override
    public FolderResponseDto updateFolder(String id, FolderRequestDto folderRequestDTO) {

        Folder existingFolder = folderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Folder introuvable"));

        existingFolder.setName(folderRequestDTO.getName());
        existingFolder.setHidden(folderRequestDTO.isHidden());

        Folder updatedFolder = folderRepository.save(existingFolder);

        FolderResponseDto folderResponseDto = folderMapper.toResponseDto(updatedFolder);

        return folderResponseDto;

    }

    @Override
    public void deleteFolder(String folderId) {

    }

    @Override
    public FolderResponseDto getFolderById(String id) {
        return null;
    }

    @Override
    public List<FolderResponseDto> getAllFolder() {
        return List.of();
    }
}
