package com.example.school.service;

import java.util.List;

import com.example.school.dto.NotificationRequest;
import com.example.school.dto.NotificationResponse;

public interface NotificationService {
    NotificationResponse create(NotificationRequest req);

    NotificationResponse markAsRead(Long

    
    void clearNotification(Long id);

    
