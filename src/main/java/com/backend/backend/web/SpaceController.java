package com.backend.backend.web;

import com.backend.backend.dto.space.SpaceDto;
import com.backend.backend.service.serviceInterface.ISpaceService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<SpaceDto> addSpace(@RequestBody SpaceDto spaceDto) {
        SpaceDto spaceDto1 = spaceService.addSpace(spaceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(spaceDto1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpaceDto> updateSpace(@PathVariable String id, @RequestBody SpaceDto spaceDto) {
        spaceDto.setId(id);
        SpaceDto updated = spaceService.updateSpace(spaceDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpace(@PathVariable String id) {
        spaceService.deleteSpace(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaceDto> getSpaceById(@PathVariable String id) {
        SpaceDto space = spaceService.getSpaceByid(id);
        return ResponseEntity.ok(space);
    }

    @GetMapping
    public ResponseEntity<List<SpaceDto>> getAllSpaces() {
        List<SpaceDto> spaces = spaceService.getSpaces();
        return ResponseEntity.ok(spaces);
    }

    @GetMapping("/workspace/{workspaceId}")
    public ResponseEntity<List<SpaceDto>> getSpacesByWorkspace(@PathVariable String workspaceId) {
        List<SpaceDto> spaces = spaceService.getSpaceByWorkspace(workspaceId);
        return ResponseEntity.ok(spaces);
    }
}
