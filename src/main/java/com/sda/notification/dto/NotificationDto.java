package com.sda.notification.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    @NotNull(message = "type is null")
    private NotificationType type;

    @NotBlank(message = "content is blank")
    private String content;

    @NotNull(message = "receiverId is blank")
    private int receiverId;

    @NotNull(message = "senderId is blank")
    private int senderId;
}
