package com.covoiturage.reclamation.query.producer;

import com.covoiturage.reclamation.commandsApi.events.ReclamationCreatedEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;






@Service
public class ReclamationProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReclamationCreatedEvent.class);
    private NewTopic topic;
    private KafkaTemplate<String, ReclamationCreatedEvent> kafkaTemplate;
    public  ReclamationProducer (NewTopic topic , KafkaTemplate<String,ReclamationCreatedEvent > kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(ReclamationCreatedEvent event) {
        LOGGER.info(String.format("ask for leave  => %s", event.toString()));
        // create Message
        Message<ReclamationCreatedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "Reclamation")
                .build();
        kafkaTemplate.send(message);

    }
}