package com.example.KudiSave.infrastructure.output.persistence.entity;


import com.example.KudiSave.domain.models.enums.NotificationDeliveryType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document
@Setter
@Getter
public class NotificationEntity {

    @Id
    private String notificationId ;
    private Long userId;
    private String title;
    private String messageBody;
    private NotificationDeliveryType notificationDeliveryMode;
    private Date createdAt;
    private Date sentAt;

}
