package com.sda.notification.repository;

import com.sda.notification.model.Notification;
import com.sda.notification.model.NotificationStatus;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NotificationRepository {
    @Select("SELECT * FROM notifications")
    public List<Notification> findAll();

    @Select("SELECT * FROM notifications WHERE receiver_id = #{receiverId}")
    public List<Notification> findAllByReceiverId(long receiverId);

    @Select("SELECT * FROM notifications WHERE status = 'CREATED'")
    public List<Notification> findAllCreated();

    @Select("SELECT * FROM notifications WHERE receiver_id = #{receiverId} AND status = 'CREATED'")
    public List<Notification> findAllCreatedByReceiverId(long receiverId);

    @Insert("INSERT INTO notifications (type, content, sender_id, receiver_id, status) VALUES (#{type}, #{content}, #{senderId}, #{receiverId}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(Notification notification);

    @Update("UPDATE notifications SET status = #{status} WHERE id = #{notificationId}")
    public void updateStatusById(long notificationId, NotificationStatus status);
}
