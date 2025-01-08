package com.example.KudiSave.domain.models;

import com.example.KudiSave.domain.models.enums.NotificationDeliveryType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
public class Notification {
    @Id
   private String notificationId ;
   private String userId;
   private String title;
   private String messageBody;
   private NotificationDeliveryType notificationDeliveryMode;
   private Date createdAt;
   private Date sentAt;

}
