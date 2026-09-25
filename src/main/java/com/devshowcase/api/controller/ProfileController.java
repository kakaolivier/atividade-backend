package com.devshowcase.api.controller;

import com.devshowcase.api.dto.request.CreateProfileRequest;
import com.devshowcase.api.dto.response.ProfileResponse;
import com.devshowcase.api.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<ProfileResponse> create(
            @Valid @RequestBody CreateProfileRequest request) {

        ProfileResponse response = profileService.create(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> findById(
            @PathVariable Long id) {

        ProfileResponse response = profileService.findById(id);

        return ResponseEntity.ok(response);
    }
}