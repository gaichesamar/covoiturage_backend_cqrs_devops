package com.covoiturage.reclamation.query.service;

import com.covoiturage.reclamation.commandsApi.events.ReclamationCreatedEvent;
import com.covoiturage.reclamation.commandsApi.events.ReclamationWaitingEvent;
import com.covoiturage.reclamation.commandsApi.queries.GetAllReclamationQuery;
import com.covoiturage.reclamation.commandsApi.queries.GetReclamationByIdQuery;
import com.covoiturage.reclamation.query.entities.Reclamation;
import com.covoiturage.reclamation.query.producer.ReclamationProducer;
import com.covoiturage.reclamation.query.repositories.ReclamationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Transactional
@Service
public class ReclamationServiceHandler {
    private ReclamationRepository reclamationRepository;
@Autowired
private ReclamationProducer reclamationProducer;





    @EventHandler
    public void on(ReclamationCreatedEvent event) {
        log.info("************************");
        log.info("ReclamationCreatedEvent received");

        Reclamation reclamation = new Reclamation();
        reclamation.setId(event.getId());
        reclamation.setSujet(event.getSujet());
        reclamation.setDescription(event.getDescription());
        reclamation.setStatus(event.getStatus());
        reclamation.setIdUser(event.getIdUser());


        reclamationRepository.save(reclamation);
        reclamationProducer.sendMessage(event);

    }

    @EventHandler
    public void on(ReclamationWaitingEvent event){
        log.info("************************");
        log.info("ReclamationWaitingEvent received");
        Reclamation  reclamation = reclamationRepository.findById(event.getId()).get();
        reclamation.setId(event.getId());
        reclamation.setStatus(event.getStatus());
        reclamationRepository.save(reclamation);


    }
    @QueryHandler
    public List<Reclamation> on (GetAllReclamationQuery query) {
        return reclamationRepository.findAll();
    }
    @QueryHandler
    public Reclamation on (GetReclamationByIdQuery query) {
        return  reclamationRepository.findById(query.getId()).get();
    }
    public List<Reclamation> getAdsByUserId(String userId) {
        return reclamationRepository.findByIdUser(userId);
    }
}