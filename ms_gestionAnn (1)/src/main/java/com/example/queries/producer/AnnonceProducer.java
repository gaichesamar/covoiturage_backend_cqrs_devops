package com.example.queries.producer;

import com.example.commonApi.events.AdCreatedEvent;
import org.apache.kafka.clients.admin.NewTopic;

        import org.slf4j.LoggerFactory;
        import org.springframework.kafka.core.KafkaTemplate;
        import org.springframework.kafka.support.KafkaHeaders;
        import org.springframework.messaging.Message;
        import org.springframework.messaging.support.MessageBuilder;
        import org.springframework.stereotype.Service;
        import org.slf4j.Logger;


@Service
public class AnnonceProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdCreatedEvent.class);
    private  NewTopic topic;
    private KafkaTemplate<String, AdCreatedEvent> kafkaTemplate;
    public  AnnonceProducer (NewTopic topic , KafkaTemplate<String,AdCreatedEvent > kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(AdCreatedEvent event) {
        LOGGER.info(String.format("ask for leave  => %s", event.toString()));
        // create Message
        Message<AdCreatedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "Annonce")
                .build();
        kafkaTemplate.send(message);

    }
}