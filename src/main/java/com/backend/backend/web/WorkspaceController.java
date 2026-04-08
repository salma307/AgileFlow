package com.backend.backend.web;

import com.backend.backend.dto.workspace.WorkspaceDto;
import com.backend.backend.dto.workspace.WorkspaceRequestDto;
import com.backend.backend.dto.workspace.WorkspaceResponseDto;
import com.backend.backend.service.manager.WorkspaceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {

    @Autowired
    private WorkspaceManager workspaceManager;

    @GetMapping
    public Page<WorkspaceDto> getWorkspaces(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return workspaceManager.getAllWorkspaceSummaries(page,size);
    }

    @PostMapping
    public WorkspaceResponseDto createNewCourse(@RequestBody WorkspaceRequestDto body) {
        WorkspaceResponseDto responseDTO = workspaceManager.addWorkspace(body);
        return responseDTO;
    }

    @DeleteMapping("{/id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkspace(@PathVariable String workspaceId){
        workspaceManager.deleteWorkspace(workspaceId);
    }

    @PutMapping("/{id}")
    public WorkspaceResponseDto updateWorkspace(
            @PathVariable String id,
            @RequestBody WorkspaceRequestDto body) {
        return workspaceManager.updateWorkspace(id, body);
    }




}