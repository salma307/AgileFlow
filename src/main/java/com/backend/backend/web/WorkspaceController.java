package com.backend.backend.web;

import com.backend.backend.dto.Workspace.WorkspaceDTO;
import com.backend.backend.service.serviceInterface.IWorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {

}