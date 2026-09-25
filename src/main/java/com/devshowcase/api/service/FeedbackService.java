package com.devshowcase.api.service;

import com.devshowcase.api.dto.request.CreateFeedbackRequest;
import com.devshowcase.api.dto.response.FeedbackResponse;
import com.devshowcase.api.entity.Feedback;
import com.devshowcase.api.entity.Project;
import com.devshowcase.api.exception.ResourceNotFoundException;
import com.devshowcase.api.repository.FeedbackRepository;
import com.devshowcase.api.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ProjectRepository projectRepository;

    public FeedbackService(
            FeedbackRepository feedbackRepository,
            ProjectRepository projectRepository
    ) {
        this.feedbackRepository = feedbackRepository;
        this.projectRepository = projectRepository;
    }

    public FeedbackResponse create(Long projectId, CreateFeedbackRequest request) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado"));

        Feedback feedback = new Feedback(
                request.getAuthor(),
                request.getComment(),
                request.getRating(),
                project
        );

        Feedback savedFeedback = feedbackRepository.save(feedback);

        updateProjectAverageRating(project);

        return new FeedbackResponse(
                savedFeedback.getId(),
                savedFeedback.getAuthor(),
                savedFeedback.getComment(),
                savedFeedback.getRating(),
                project.getId()
        );
    }

    private void updateProjectAverageRating(Project project) {

        List<Feedback> feedbacks = feedbackRepository.findAll()
                .stream()
                .filter(feedback -> feedback.getProject().getId().equals(project.getId()))
                .toList();

        double average = feedbacks.stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0.0);

        project.setAverageRating(average);

        projectRepository.save(project);
    }
}