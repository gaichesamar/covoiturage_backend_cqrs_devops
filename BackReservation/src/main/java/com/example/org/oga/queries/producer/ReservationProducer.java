package com.example.org.oga.queries.producer;

import com.example.org.oga.commonapi.events.ReservationCreatedEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;



@Service
 public class ReservationProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationCreatedEvent.class);
    private NewTopic topic;
    private KafkaTemplate<String, ReservationCreatedEvent> kafkaTemplate;
    public  ReservationProducer (NewTopic topic , KafkaTemplate<String, ReservationCreatedEvent > kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(ReservationCreatedEvent event) {
        LOGGER.info(String.format("ask for leave  => %s", event.toString()));
        // create Message
        Message<ReservationCreatedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "Reservation")
                .build();
        kafkaTemplate.send(message);

    }
}