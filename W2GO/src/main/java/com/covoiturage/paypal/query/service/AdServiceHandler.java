package com.covoiturage.paypal.query.service;

import com.covoiturage.paypal.commonApi.Event.AdCreatedEvent;
import com.covoiturage.paypal.commonApi.Event.AdValidatedEvent;
import com.covoiturage.paypal.query.entity.Ad;
import com.covoiturage.paypal.query.repositories.AdRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@AllArgsConstructor
@Slf4j
@Transactional
@Service
public class AdServiceHandler {
    private AdRepository adRepository;

    @EventHandler
    public void on(AdCreatedEvent event) {
        log.info("************************");
        log.info("AdCreatedEvent received");
        Ad ad = new Ad();
        ad.setId(event.getId());
        ad.setPrix(event.getPrix());
        ad.setNom(event.getNom());
        ad.setPrenom(event.getPrenom());
        ad.setEmail(event.getEmail());
        ad.setTelephone(event.getTelephone());
        ad.setStatus(event.getStatus());

        adRepository.save(ad);
    }

    @EventHandler
    public void on(AdValidatedEvent event) {
        log.info("************************");
        log.info("ValidationEvent received");
        Ad ad = adRepository.findById(event.getId()).get();
        ad.setId(event.getId());
        ad.setStatus(event.getStatus());
        adRepository.save(ad);
    }
}
