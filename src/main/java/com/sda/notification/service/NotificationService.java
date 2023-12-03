package com.sda.notification.service;

import com.sda.notification.dto.NotificationDto;
import com.sda.notification.model.Notification;
import com.sda.notification.model.NotificationStatus;

import java.util.List;

public interface NotificationService {
    public void create(NotificationDto notificationDto);

    public List<Notification> getAll();

    public List<Notification> getAllByReceiverId(int receiverId);

    public List<Notification> getAllCreated();

    public List<Notification> getAllCreatedByReceiverId(int receiverId);

    public void updateStatusByChat(int senderId, int receiverId, NotificationStatus status);
}
