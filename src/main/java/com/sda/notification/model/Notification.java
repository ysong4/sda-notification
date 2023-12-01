package com.sda.notification.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class Notification {
    private int id;
    private NotificationType type;
    private String content;
    private int senderId;
    private int receiverId;
    private NotificationStatus status;
    private Timestamp createdAt;
}
