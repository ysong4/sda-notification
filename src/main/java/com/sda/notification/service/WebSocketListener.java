package com.sda.notification.service;

import com.sda.notification.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

@Slf4j
public class WebSocketListener implements Listener {
    private final int userId;

    private final SimpMessageSendingOperations messagingTemplate;

    public WebSocketListener(int userId, SimpMessageSendingOperations messagingTemplate) {
        this.userId = userId;
        this.messagingTemplate = messagingTemplate;
    }

    public void send(NotificationDto notificationDto) {
        log.info("WebsocketListener send(): {}", userId);

        messagingTemplate.convertAndSend("/topic/" + userId, notificationDto);
    }
}
