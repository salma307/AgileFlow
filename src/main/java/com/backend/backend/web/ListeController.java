package com.backend.backend.web;

import com.backend.backend.dto.liste.ListeRequestDto;
import com.backend.backend.dto.liste.ListeResponseDto;
import com.backend.backend.service.serviceInterface.IListeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listes")
@RequiredArgsConstructor
public class ListeController {

    private final IListeService listeService;

    @PostMapping
    public ResponseEntity<ListeResponseDto> addListe(@RequestBody ListeRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(listeService.addListe(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListeResponseDto> updateListe(
            @PathVariable String id,
            @RequestBody ListeRequestDto request) {
        return ResponseEntity.ok(listeService.updateListe(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListe(@PathVariable String id) {
        listeService.deleteListe(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListeResponseDto> getListeById(@PathVariable String id) {
        return ResponseEntity.ok(listeService.getListeById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ListeResponseDto>> getAllListes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortBy));
        return ResponseEntity.ok(listeService.getListes(pageable));
    }

    @GetMapping("/folder/{folderId}")
    public ResponseEntity<List<ListeResponseDto>> getListesByFolder(@PathVariable String folderId) {
        return ResponseEntity.ok(listeService.getListeByFolder(folderId));
    }

    @GetMapping("/sprint/{sprintId}")
    public ResponseEntity<List<ListeResponseDto>> getListesBySprint(@PathVariable String sprintId) {
        return ResponseEntity.ok(listeService.getListeBySprint(sprintId));
    }
}
