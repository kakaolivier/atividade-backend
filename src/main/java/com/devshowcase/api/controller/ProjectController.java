package com.devshowcase.api.controller;

import com.devshowcase.api.dto.request.CreateProjectRequest;
import com.devshowcase.api.dto.response.ProjectResponse;
import com.devshowcase.api.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


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

    @PutMapping("/{id}/upvote")
    public ResponseEntity<ProjectResponse> upvote(@PathVariable Long id) {

        ProjectResponse response = projectService.upvote(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ProjectResponse>> findAll(
            @RequestParam(required = false) Long technologyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<ProjectResponse> response = projectService.findAll(technologyId, pageable);

        return ResponseEntity.ok(response);
    }
}