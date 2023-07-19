package com.example.org.oga.commands.aggregates;

import com.example.org.oga.commonapi.commands.AcceptReservationCommand;
import com.example.org.oga.commonapi.commands.CreateReservationCommand;
import com.example.org.oga.commonapi.commands.DeleteReservationCommand;
import com.example.org.oga.commonapi.commands.RejectReservationCommand;
import com.example.org.oga.commonapi.enums.ReservationStatus;
import com.example.org.oga.commonapi.events.*;
import com.example.org.oga.queries.repository.ReservationRepository;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

@Aggregate
public class ReservationAggregate {
    @AggregateIdentifier
private String reservationId;
  private Boolean valide;
  private int nbrPlace;
    private ReservationStatus status;
  private  String idAnnonce;
  private  String idConducteurs;
  private String idConsommateur;
  @Autowired
  private ReservationRepository reservationRepository;

    public ReservationAggregate(){
        //Required by AXON
    }
  @CommandHandler


  public ReservationAggregate(CreateReservationCommand command) {
    //Required by Axon
    AggregateLifecycle.apply(new ReservationCreatedEvent(
            command.getId(),
            command.getNbrPlace(),
            command.getValide(), command.getIdAnnonce(),
            command.getIdConsommateur(),
            command.getIdConducteurs(),
            ReservationStatus.CREATED));
  }
  @EventSourcingHandler
  public void on(ReservationCreatedEvent event)
  {
    this.reservationId=event.getId();

    this.valide=event.getValide();
    this.idAnnonce=event.getIdAnnonce();
    this.idConsommateur= event.getIdConsommateur();
    this.idConducteurs=event.getIdConducteurs();
    this.nbrPlace=event.getNbrPlace();
    this.status=ReservationStatus.CREATED;
    AggregateLifecycle.apply(new ReservationActivatedEvent(
            event.getId(),
            ReservationStatus.CREATED
    ));
  }

  @EventSourcingHandler
  public void on(ReservationActivatedEvent event){
    this.status=event.getStatus();
  }


  @CommandHandler
  public void accepterReservation(AcceptReservationCommand command) {
    if (status == ReservationStatus.CREATED) {
      AggregateLifecycle.apply(new ReservationAcceptedEvent(reservationId));
    }
  }

  @EventSourcingHandler
  public void on(ReservationAcceptedEvent event) {
  }

  @CommandHandler
  public void refuserReservation(RejectReservationCommand command) {
    if (status == ReservationStatus.CREATED) {
      AggregateLifecycle.apply(new ReservationRejectedEvent(reservationId));
    }
  }

  @EventSourcingHandler
  public void on(ReservationRejectedEvent event) {
  }



  @CommandHandler
  public void handle(DeleteReservationCommand command) {
    if (this.status == ReservationStatus.CANCELED) {
      throw new IllegalStateException(" Reservation is already deleted");
    }
    AggregateLifecycle.apply(new CancelResrvationEvent.getId());
  }
  @EventSourcingHandler
  public void on(CancelResrvationEvent event) {
    AggregateLifecycle.markDeleted();
  }


}

