package com.backend.backend.web;

import com.backend.backend.dto.space.SpaceRequestDto;
import com.backend.backend.dto.space.SpaceResponseDto;
import com.backend.backend.service.serviceInterface.ISpaceService;
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
@RequestMapping("/api/spaces")
@RequiredArgsConstructor
public class SpaceController {

    private final ISpaceService spaceService;

    @PostMapping
    public ResponseEntity<SpaceResponseDto> addSpace(@RequestBody SpaceRequestDto spaceDto) {
        SpaceResponseDto spaceDto1 = spaceService.addSpace(spaceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(spaceDto1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpaceResponseDto> updateSpace(@PathVariable String id, @RequestBody SpaceRequestDto spaceDto) {
        SpaceResponseDto updated = spaceService.updateSpace(id,spaceDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpace(@PathVariable String id) {
        spaceService.deleteSpace(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaceResponseDto> getSpaceById(@PathVariable String id) {
        SpaceResponseDto space = spaceService.getSpaceByid(id);
        return ResponseEntity.ok(space);
    }

    @GetMapping
    public ResponseEntity<Page<SpaceResponseDto>> getAllSpaces(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(spaceService.getSpaces(pageable));
    }

    @GetMapping("/workspace/{workspaceId}")
    public ResponseEntity<List<SpaceResponseDto>> getSpacesByWorkspace(@PathVariable String workspaceId) {
        List<SpaceResponseDto> spaces = spaceService.getSpaceByWorkspace(workspaceId);
        return ResponseEntity.ok(spaces);
    }
}
