package com.sda.notification.config;

import com.sda.notification.service.EventManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final EventManager eventManager;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        Integer userId = (Integer) headerAccessor.getSessionAttributes().get("userId");

        if (userId != null) {
            log.info("user disconnected: {}", userId);

            eventManager.unsubscribe(userId);
        }
    }

}
