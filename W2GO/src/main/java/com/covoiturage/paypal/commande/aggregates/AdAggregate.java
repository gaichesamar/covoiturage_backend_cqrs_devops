package com.covoiturage.paypal.commande.aggregates;

import com.covoiturage.paypal.commonApi.Enum.AdStatus;
import com.covoiturage.paypal.commonApi.Event.AdCreatedEvent;
import com.covoiturage.paypal.commonApi.Event.AdValidatedEvent;
import com.covoiturage.paypal.commonApi.commands.CreateAdCommand;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
@Aggregate
@AllArgsConstructor
public class AdAggregate {
    @AggregateIdentifier
    private String adId;
    private long prix;
    private String nom;
    private String prenom;
    private String email;
    private long telephone;
    private AdStatus status;

    public AdAggregate() {
        //Required by Axon
    }

    @CommandHandler
    public AdAggregate(CreateAdCommand createAnnounceCommand) {
        //Required by Axon
        AggregateLifecycle.apply(new AdCreatedEvent(
                createAnnounceCommand.getId(),
                createAnnounceCommand.getPrix(),
                createAnnounceCommand.getNom(),
                createAnnounceCommand.getPrenom(),
                createAnnounceCommand.getEmail(),
                createAnnounceCommand.getTelephone(),
                AdStatus.CREATED));
    }

    @EventSourcingHandler
    public void on(AdCreatedEvent event) {
        this.adId = event.getId();
        this.prix = event.getPrix();
        this.nom = event.getNom();
        this.prenom = event.getPrenom();
        this.email = event.getEmail();
        this.telephone = event.getTelephone();
        this.status = AdStatus.CREATED;
        AggregateLifecycle.apply(new AdValidatedEvent(
                event.getId(),
                AdStatus.VALIDATED
        ));
    }

    @EventSourcingHandler
    public void on(AdValidatedEvent event) {
        this.status = event.getStatus();
    }
}
