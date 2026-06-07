package com.example.school.controller;

import org.springframework.web.bind.annotation.*;

import com.example.school.entity.PredictionRequest;
import com.example.school.entity.PredictionResponse;
import com.example.school.service.AIService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AIController {

    private final AIService aiService;

    @PostMapping("/predict")
    public PredictionResponse predict(
            @RequestBody PredictionRequest request) {

        return aiService.predictStudent(request);
    }
}