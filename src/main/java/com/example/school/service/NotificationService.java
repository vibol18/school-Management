package com.example.school.service;

import java.util.List;

import com.example.school.dto.NotificationRequest;
import com.example.school.dto.NotificationResponse;

public interface NotificationService {
    NotificationResponse create(NotificationRequest req);

    List<NotificationResponse> getAll();

    NotificationResponse markAsRead(Long id);

    void clearNotification(Long id);
}
