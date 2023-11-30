package com.sda.notification.service;

import com.sda.notification.dto.NotificationDto;

public interface Listener {
    public void send(NotificationDto notificationDto);
}
