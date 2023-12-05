package com.sda.notification.service;

import com.sda.notification.dto.NotificationDto;
import com.sda.notification.vo.NotificationVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import java.sql.Timestamp;

@Slf4j
public class WebSocketListener implements Listener {
    private final int userId;

    private final SimpMessageSendingOperations messagingTemplate;

    public WebSocketListener(int userId, SimpMessageSendingOperations messagingTemplate) {
        this.userId = userId;
        this.messagingTemplate = messagingTemplate;
    }

    public void send(NotificationDto dto) {
        log.info("WebsocketListener send(): {}", userId);

        NotificationVo vo = NotificationVo.builder()
                .senderId(dto.getSenderId())
                .receiverId((dto.getReceiverId()))
                .type(dto.getType())
                .content(dto.getContent())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();

        messagingTemplate.convertAndSend("/topic/" + userId, vo);
    }
}
