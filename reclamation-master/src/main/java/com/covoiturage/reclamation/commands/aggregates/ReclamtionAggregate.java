package com.covoiturage.reclamation.commands.aggregates;

import com.covoiturage.reclamation.commandsApi.commands.CreateReclamationCommand;
import com.covoiturage.reclamation.commandsApi.enums.ReclamationStatus;
import com.covoiturage.reclamation.commandsApi.events.ReclamationCreatedEvent;
import com.covoiturage.reclamation.commandsApi.events.ReclamationWaitingEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
@Aggregate
public class ReclamtionAggregate {

    @AggregateIdentifier
   String ReclamtionId;
  ReclamationWaitingEvent event;
    String sujet;
   String description;
 ReclamationStatus status;


    public ReclamtionAggregate() {
    }


    @CommandHandler
    public ReclamtionAggregate (CreateReclamationCommand createReclamationCommand) {
        if ((createReclamationCommand.getSujet() == null) ||
                (createReclamationCommand.getDescription() == null)) {
            throw new RuntimeException("Inputs should not be null");
        }
        AggregateLifecycle.apply(new ReclamationCreatedEvent(
                createReclamationCommand.getId(),
                createReclamationCommand.getSujet(),
                createReclamationCommand.getDescription(),
                createReclamationCommand.getIdUser(),
                ReclamationStatus.CREATED

        ));
    }

    @EventSourcingHandler
    public void on(ReclamationCreatedEvent event) {
        this.ReclamtionId= event.getId();
        this.sujet = event.getSujet();
        this.description = event.getDescription();
        this.status = ReclamationStatus.CREATED;
        AggregateLifecycle.apply(new ReclamationWaitingEvent(
                event.getId(),
                ReclamationStatus.RECIVED
        ));
    }
    @EventSourcingHandler
    public void on(ReclamationWaitingEvent event) {
        this.status = event.getStatus();
    }

}
