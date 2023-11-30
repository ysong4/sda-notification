package com.sda.notification.controller;

import com.sda.notification.dto.UserConnectedDto;
import com.sda.notification.service.EventManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ConnectionController {

    private final EventManager eventManager;

    @MessageMapping("/user-connected")
    public void addUser(
            @Payload UserConnectedDto userConnectedDto,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("userId", userConnectedDto.getSenderId());

        log.info("user connected: {}", userConnectedDto.getSenderId());

        // subscribe to EventManager
        eventManager.subscribe(userConnectedDto.getSenderId());
    }
}
