package com.sda.notification.controller;

import com.sda.notification.dto.NotificationDto;
import com.sda.notification.model.NotificationStatus;
import com.sda.notification.service.EventManager;
import com.sda.notification.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
@Slf4j
@RequiredArgsConstructor
public class NotificationController {

    private final EventManager eventManager;

    private final NotificationService notificationService;

    @PostMapping("")
    public ResponseEntity<?> receiveNotification(
            @Valid @RequestBody NotificationDto notification
    ) {
        log.info("Received a new notification request to {}", notification.getReceiverId());

        eventManager.notify(notification.getReceiverId(), notification);

        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<?> getAllNotifications(@RequestParam(required = false) Integer receiverId, @RequestParam(required = false) NotificationStatus status) {
        if (receiverId == null) {
            if (status == NotificationStatus.CREATED) {
                return ResponseEntity.ok(notificationService.getAllCreated());
            } else {
                return ResponseEntity.ok(notificationService.getAll());
            }
        } else {
            if (status == NotificationStatus.CREATED) {
                return ResponseEntity.ok(notificationService.getAllCreatedByReceiverId(receiverId));
            } else {
                return ResponseEntity.ok(notificationService.getAllByReceiverId(receiverId));
            }
        }
    }

    @PatchMapping("")
    public ResponseEntity<?> updateNotificationStatusById(
            @RequestParam Integer senderId, @RequestParam Integer receiverId) {
        log.info("Fetch notifications by receiverId");

        notificationService.updateStatusByChat(senderId, receiverId, NotificationStatus.VIEWED);

        return ResponseEntity.ok().build();
    }
}
