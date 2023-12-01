package com.sda.notification.service;

import com.sda.notification.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class EventManager {
    private final Map<Integer, Listener> listeners;

    private final NotificationService notificationService;

    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public EventManager(NotificationService notificationService, SimpMessageSendingOperations messagingTemplate) {
        this.listeners = new HashMap<>();
        this.notificationService = notificationService;
        this.messagingTemplate = messagingTemplate;
    }

    public void subscribe(int userId) {
        WebSocketListener webSocketListener = new WebSocketListener(userId, messagingTemplate);

        listeners.put(userId, webSocketListener);

        log.info("Subscribe to eventManager: {}", userId);
    }

    public void unsubscribe(int userId) {
        listeners.remove(userId);

        log.info("Unsubscribe to eventManager: {}", userId);
    }

    public void notify(int receiverId, NotificationDto notificationDto) {
        if (listeners.containsKey(receiverId)) {
            listeners.get(receiverId).send(notificationDto);
        } else {
            log.info("user is offline, let's store it to DB!");

            notificationService.create(notificationDto);
        }
    }
}
