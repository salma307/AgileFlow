package com.backend.backend.dto.folder;

import com.backend.backend.dao.entities.Liste;
import com.backend.backend.dto.liste.ListeResponseDto;

import java.util.List;

public class FolderResponseDto {

    private String name;
    private boolean isHidden;
    private List<ListeResponseDto> listes;
}
