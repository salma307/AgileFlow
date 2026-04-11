package com.backend.backend.service.serviceInterface;

import com.backend.backend.dto.liste.ListeRequestDto;
import com.backend.backend.dto.liste.ListeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IListeService {
    ListeResponseDto addListe(ListeRequestDto request);
    ListeResponseDto updateListe(String id,ListeRequestDto request);
    boolean deleteListe(String id);
    ListeResponseDto getListeById(String id);
    Page<ListeResponseDto> getListes(Pageable pageable);
    List<ListeResponseDto> getListeByFolder(String folderId);
    List<ListeResponseDto> getListeBySprint(String sprintId);

}
