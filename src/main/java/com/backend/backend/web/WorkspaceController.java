package com.backend.backend.web;

import com.backend.backend.dto.Workspace.WorkspaceDTO;
import com.backend.backend.dto.Workspace.WorkspaceRequestDTO;
import com.backend.backend.dto.Workspace.WorkspaceResponseDTO;
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
    public Page<WorkspaceDTO> getWorkspaces(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return workspaceManager.getAllWorkspaceSummaries(page,size);
    }

    @PostMapping
    public WorkspaceResponseDTO createNewCourse(@RequestBody WorkspaceRequestDTO body) {
        WorkspaceResponseDTO responseDTO = workspaceManager.addWorkspace(body);
        return responseDTO;
    }

    @DeleteMapping("{/id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkspace(@PathVariable String workspaceId){
        workspaceManager.deleteWorkspace(workspaceId);
    }

    @PutMapping("/{id}")
    public WorkspaceResponseDTO updateWorkspace(
            @PathVariable String id,
            @RequestBody WorkspaceRequestDTO body) {
        return workspaceManager.updateWorkspace(id, body);
    }




}