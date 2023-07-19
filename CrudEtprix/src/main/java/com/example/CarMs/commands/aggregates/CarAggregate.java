package com.example.CarMs.commands.aggregates;

import com.example.CarMs.commonApi.commands.AddCarCommand;
import com.example.CarMs.commonApi.commands.RemoveCarCommand;
import com.example.CarMs.commonApi.commands.UpdateCarCommand;
import com.example.CarMs.commonApi.enums.CarStatus;
import com.example.CarMs.commonApi.events.CarAddedEvent;
import com.example.CarMs.commonApi.events.CarRemovedEvent;
import com.example.CarMs.commonApi.events.CarUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;


@Aggregate
public class CarAggregate {
    @AggregateIdentifier
   String CarId;
    String marque;
   String modele;
    String immatriculation;
     String couleur;
    int nbrPlace;
    String type;
   CarStatus status;

    public CarAggregate() {
        //Required by Axon
    }
    @CommandHandler
    public CarAggregate(AddCarCommand command) {
        //Required by Axon
        AggregateLifecycle.apply(new CarAddedEvent(
                command.getId(),
                command.getMarque(),
                command.getModele(),
                command.getImmatriculation(),
                command.getCouleur(),
                command.getNbrPlace(),
                command.getType(),
                command.getIdUser(),
                CarStatus.ADDED                ));
    }
    @EventSourcingHandler
    public void on(CarAddedEvent event)
    {
    this.CarId=event.getId();
    this.marque=event.getMarque();
    this.modele=event.getModele();
    this.immatriculation= event.getImmatriculation();
    this.couleur=event.getCouleur();
    this.nbrPlace =event.getNbrPlace();
    this.type=event.getType();
    this.status=CarStatus.ADDED;

    }


    @CommandHandler
    public void handle(UpdateCarCommand command) {
        AggregateLifecycle.apply(new CarUpdatedEvent(
                command.getId(),
                command.getMarque(),
                command.getModele(),
                command.getImmatriculation(),
                command.getCouleur(),
                command.getNbrPlace(),
                command.getType(),
                CarStatus.UPDATED
        ));

}
    @EventSourcingHandler
    public void on(CarUpdatedEvent event){
        this.CarId=event.getId();
        this.marque=event.getMarque();
        this.modele=event.getModele();
        this.immatriculation= event.getImmatriculation();
        this.couleur=event.getCouleur();
        this.nbrPlace =event.getNbrPlace();
        this.type=event.getType();
        this.status=CarStatus.UPDATED;

    }


    @CommandHandler
    public void handle(RemoveCarCommand command) {
        if (this.status == CarStatus.DELETED) {
            throw new IllegalStateException("Car is already deleted");
        }
        AggregateLifecycle.apply(new CarRemovedEvent(command.getId()));
    }
    @EventSourcingHandler
    public void on(CarRemovedEvent event) {
            AggregateLifecycle.markDeleted();
        }
    }


