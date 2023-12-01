package com.sda.notification.service;

import com.sda.notification.dto.NotificationDto;
import com.sda.notification.model.Notification;
import com.sda.notification.model.NotificationStatus;
import com.sda.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void create(NotificationDto dto) {
        Notification notification = Notification.builder()
                .status(NotificationStatus.CREATED)
                .type(dto.getType())
                .content(dto.getContent())
                .senderId(dto.getSenderId())
                .receiverId(dto.getReceiverId())
                .build();

        notificationRepository.insert(notification);
    }

    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    public List<Notification> getAllByReceiverId(int receiverId) {
        return notificationRepository.findAllByReceiverId(receiverId);
    }

    public List<Notification> getAllCreated() {
        return notificationRepository.findAllCreated();
    }

    public List<Notification> getAllCreatedByReceiverId(int receiverId) {
        return notificationRepository.findAllCreatedByReceiverId(receiverId);
    }

    public void updateStatusById(int notificationId, NotificationStatus status) {
        notificationRepository.updateStatusById(notificationId, status);
    }
}
