package com.example.school.controller;

import com.example.school.dto.NotificationRequest;
import com.example.school.dto.NotificationResponse;
import com.example.school.service.NotificationService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(
            NotificationService notificationService) {

        this.notificationService = notificationService;
    }

    @PostMapping
    public NotificationResponse create(
            @RequestBody NotificationRequest request) {

        return notificationService.create(request);
    }

    @GetMapping
    public List<NotificationResponse> getAll() {

        return notificationService.getAll();
    }

    @PutMapping("/{id}/read")
    public NotificationResponse markAsRead(
            @PathVariable Long id) {

        return notificationService.markAsRead(id);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        notificationService.clearNotification(id);
    }
}