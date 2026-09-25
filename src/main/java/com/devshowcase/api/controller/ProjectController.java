package com.devshowcase.api.controller;

import com.devshowcase.api.dto.request.CreateProjectRequest;
import com.devshowcase.api.dto.response.ProjectResponse;
import com.devshowcase.api.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> create(
            @Valid @RequestBody CreateProjectRequest request) {

        ProjectResponse response = projectService.create(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> findAll() {

        List<ProjectResponse> response =
                projectService.findAll();

        return ResponseEntity.ok(response);
    }
}