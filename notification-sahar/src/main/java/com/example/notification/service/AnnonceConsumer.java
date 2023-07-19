package com.example.notification.service;


import com.example.commonApi.events.AdCreatedEvent;
import com.example.notification.entitie.Notification;
import com.example.notification.repositorie.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnnonceConsumer {
    @Autowired
    NotificationRepository notificationRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(AnnonceConsumer.class);
    @KafkaListener(topics = "Annonce",groupId = "${spring.kafka.consumer.group-id}")
    public void consume(AdCreatedEvent event){
        LOGGER.info(event.getLieuArr().toString());



        Notification notifications = new Notification();
        notifications.setMessage("Nouvelle annonce de covoiturage : Lieu de départ - " + event.getLieuDep() + ", Lieu d'arrivée - " + event.getLieuArr());
        notifications.setCreatedAt(LocalDateTime.now());

        notificationRepository.save(notifications);

    }
}