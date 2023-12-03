package com.sda.notification.repository;

import com.sda.notification.model.Notification;
import com.sda.notification.model.NotificationStatus;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NotificationRepository {
    @Select("SELECT * FROM notifications ORDER BY created_at DESC")
    public List<Notification> findAll();

    @Select("SELECT * FROM notifications WHERE receiver_id = #{receiverId} ORDER BY created_at DESC")
    public List<Notification> findAllByReceiverId(long receiverId);

    @Select("SELECT * FROM notifications WHERE status = 'CREATED' ORDER BY created_at DESC")
    public List<Notification> findAllCreated();

    @Select("SELECT * FROM notifications WHERE receiver_id = #{receiverId} AND status = 'CREATED' ORDER BY created_at DESC")
    public List<Notification> findAllCreatedByReceiverId(long receiverId);

    @Insert("INSERT INTO notifications (type, content, sender_id, receiver_id, status, created_at) " +
            "VALUES (#{type}, #{content}, #{senderId}, #{receiverId}, #{status}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(Notification notification);

    @Update("UPDATE notifications SET status = #{status} WHERE receiver_id = #{receiverId} AND sender_id = #{senderId}")
    public void updateStatusById(long senderId, long receiverId, NotificationStatus status);
}
