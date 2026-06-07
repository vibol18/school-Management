package com.example.school.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.school.dto.NotificationRequest;
import com.example.school.dto.NotificationResponse;
import com.example.school.entity.Notification;
import com.example.school.repository.NotificationRepository;
import com.example.school.service.NotificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public NotificationResponse create(NotificationRequest req) {

        Notification notification = Notification.builder()
                .title(req.getTitle())
                .message(req.getMessage())
                .createdAt(LocalDateTime.now())
                .isRead(false)
                .build();

        notificationRepository.save(notification);

        NotificationResponse response = mapToResponse(notification);

        // SEND REALTIME TO FRONTEND
        messagingTemplate.convertAndSend(
                "/topic/notifications",
                response);

        return response;
    }

    @Override
    public List<NotificationResponse> getAll() {

        return notificationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public NotificationResponse markAsRead(Long id) {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setRead(true);

        notificationRepository.save(notification);

        return mapToResponse(notification);
    }

    @Override
    public void clearNotification(Long id) {

        notificationRepository.deleteById(id);
    }

    private NotificationResponse mapToResponse(Notification notification) {

        return NotificationResponse.builder()
                .id(notification.getId())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .isRead(notification.isRead())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}