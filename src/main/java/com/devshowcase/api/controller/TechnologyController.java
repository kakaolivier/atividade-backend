package com.devshowcase.api.controller;

import com.devshowcase.api.dto.request.CreateTechnologyRequest;
import com.devshowcase.api.dto.response.TechnologyResponse;
import com.devshowcase.api.service.TechnologyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController {

    private final TechnologyService technologyService;

    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @PostMapping
    public ResponseEntity<TechnologyResponse> create(
            @Valid @RequestBody CreateTechnologyRequest request) {

        TechnologyResponse response = technologyService.create(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TechnologyResponse>> findAll() {

        List<TechnologyResponse> response =
                technologyService.findAll();

        return ResponseEntity.ok(response);
    }
}