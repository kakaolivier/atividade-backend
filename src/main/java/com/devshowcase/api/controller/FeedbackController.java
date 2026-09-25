package com.devshowcase.api.controller;

import com.devshowcase.api.dto.request.CreateFeedbackRequest;
import com.devshowcase.api.dto.response.FeedbackResponse;
import com.devshowcase.api.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects/{projectId}/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<FeedbackResponse> create(
            @PathVariable Long projectId,
            @Valid @RequestBody CreateFeedbackRequest request
    ) {
        FeedbackResponse response = feedbackService.create(projectId, request);
        return ResponseEntity.ok(response);
    }
}