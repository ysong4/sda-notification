package com.sda.notification.controller;

import com.sda.notification.dto.NotificationDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@Slf4j
@RequiredArgsConstructor
public class NotificationController {

    private final SimpMessageSendingOperations messagingTemplate;

    @PostMapping("")
    public void receiveNotification(
            @Valid @RequestBody NotificationDto notification
    ) {
        log.info("Received a new notification request to {}", notification.getReceiverId());

        // TODO:
        // If user is online, send through websocket
        // If user is offline, store to DB

        messagingTemplate.convertAndSend("/topic/" + notification.getReceiverId(), notification);
    }
}
