package com.example.notification.service;

import com.covoiturage.reclamation.commandsApi.events.ReclamationCreatedEvent;
import com.example.notification.entitie.Notification;
import com.example.notification.repositorie.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.time.LocalDateTime;

public class ReclamationConsumer {
    @Autowired
    NotificationRepository notificationRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReclamationConsumer.class);
    @KafkaListener(topics = "Reclamation",groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ReclamationCreatedEvent event){
        LOGGER.info(event.getSujet().toString());



        Notification notifications = new Notification();
        notifications.setMessage("Nouvelle reclamation de covoiturage : Sujet - " + event.getSujet() + ", Description - ");
        notifications.setCreatedAt(LocalDateTime.now());

        notificationRepository.save(notifications);

    }
}
