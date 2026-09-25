package com.devshowcase.api.dto.response;

public class FeedbackResponse {

    private Long id;
    private String author;
    private String comment;
    private Integer rating;
    private Long projectId;

    public FeedbackResponse(Long id, String author, String comment, Integer rating, Long projectId) {
        this.id = id;
        this.author = author;
        this.comment = comment;
        this.rating = rating;
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getComment() {
        return comment;
    }

    public Integer getRating() {
        return rating;
    }

    public Long getProjectId() {
        return projectId;
    }
}