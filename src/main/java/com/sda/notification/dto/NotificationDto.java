package com.sda.notification.dto;

import com.sda.notification.controller.NotificationType;
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

    @NotBlank(message = "receiverId is blank")
    private String receiverId;

    @NotBlank(message = "senderId is blank")
    private String senderId;
}
