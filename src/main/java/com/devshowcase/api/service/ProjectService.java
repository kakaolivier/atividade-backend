package com.devshowcase.api.service;

import com.devshowcase.api.dto.request.CreateProjectRequest;
import com.devshowcase.api.dto.response.ProjectResponse;
import com.devshowcase.api.entity.Profile;
import com.devshowcase.api.entity.Project;
import com.devshowcase.api.entity.Technology;
import com.devshowcase.api.repository.ProfileRepository;
import com.devshowcase.api.repository.ProjectRepository;
import com.devshowcase.api.repository.TechnologyRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;
    private final TechnologyRepository technologyRepository;

    public ProjectService(
            ProjectRepository projectRepository,
            ProfileRepository profileRepository,
            TechnologyRepository technologyRepository) {

        this.projectRepository = projectRepository;
        this.profileRepository = profileRepository;
        this.technologyRepository = technologyRepository;
    }

    public ProjectResponse create(CreateProjectRequest request) {

        Profile profile = profileRepository.findById(request.getProfileId())
                .orElseThrow(() ->
                        new RuntimeException("Profile não encontrado")
                );

        Set<Technology> technologies = new HashSet<>();

        if (request.getTechnologyIds() != null) {
            technologies = new HashSet<>(
                    technologyRepository.findAllById(
                            request.getTechnologyIds()
                    )
            );
        }

        Project project = new Project(
                request.getTitle(),
                request.getDescription(),
                request.getUrl(),
                profile
        );

        project.setTechnologies(technologies);

        Project savedProject = projectRepository.save(project);

        return toResponse(savedProject);
    }

    public List<ProjectResponse> findAll() {

        return projectRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ProjectResponse toResponse(Project project) {

        Set<Long> technologyIds = project.getTechnologies()
                .stream()
                .map(Technology::getId)
                .collect(Collectors.toSet());

        return new ProjectResponse(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getUrl(),
                project.getProfile().getId(),
                technologyIds
        );
    }
}