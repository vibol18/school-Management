package com.example.school.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.school.entity.PredictionRequest;
import com.example.school.entity.PredictionResponse;

@Service
public class AIService {

        private final WebClient webClient = WebClient.create("http://127.0.0.1:5000");

        public PredictionResponse predictStudent(
                        PredictionRequest request) {

                Map response = webClient.post()
                                .uri("/predict")
                                .bodyValue(request)
                                .retrieve()
                                .bodyToMono(Map.class)
                                .block();

                return new PredictionResponse(
                                response.get("prediction").toString());
        }
}