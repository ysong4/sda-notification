package com.sda.notification.vo;

import com.sda.notification.model.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class NotificationVo {
    private NotificationType type;

    private String content;

    private int receiverId;

    private int senderId;

    private Timestamp createdAt;
}
