package com.example.notification.service;

import com.example.notification.entitie.Notification;
import com.example.notification.repositorie.NotificationRepository;
import com.example.org.oga.commonapi.events.ReservationCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.time.LocalDateTime;

public class ReservationConsumer {
    @Autowired
    NotificationRepository notificationRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationConsumer.class);
    @KafkaListener(topics = "Reservation",groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ReservationCreatedEvent event){
        LOGGER.info(event.getIdAnnonce().toString());



        Notification notifications = new Notification();
        notifications.setMessage("Nouvelle reservation de covoiturage pour ce annonce :  - " + event.getIdAnnonce());
        notifications.setCreatedAt(LocalDateTime.now());

        notificationRepository.save(notifications);

    }
}
