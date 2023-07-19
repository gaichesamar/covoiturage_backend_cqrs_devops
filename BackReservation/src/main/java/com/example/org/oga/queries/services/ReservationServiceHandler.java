package com.example.org.oga.queries.services;


import com.example.org.oga.commonapi.enums.ReservationStatus;
import com.example.org.oga.commonapi.events.ReservationAcceptedEvent;
import com.example.org.oga.commonapi.events.ReservationCreatedEvent;
import com.example.org.oga.commonapi.events.ReservationRejectedEvent;
import com.example.org.oga.commonapi.query.GetAllResrvationsQuery;
import com.example.org.oga.commonapi.query.GetReservationByIdQuery;
import com.example.org.oga.queries.entities.Reservation;
import com.example.org.oga.queries.producer.ReservationProducer;
import com.example.org.oga.queries.repository.ReservationRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Transactional
@Service
public class ReservationServiceHandler {
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationProducer reservationProducer;
    @EventHandler
    public void on(ReservationCreatedEvent event){
        log.info("**************************");
        log.info("RservationCreatedEvent received");
        Reservation reservation = new Reservation();
        reservation.setId(event.getId());
        reservation.setValide(event.getValide());
        reservation.setIdAnnonce(event.getIdAnnonce());
        reservation.setIdConsommateur(event.getIdConsommateur());
        reservation.setIdConducteurs(event.getIdConducteurs());
        reservation.setNbrPlace(event.getNbrPlace());
        reservation.setStatus(event.getStatus());
        reservationRepository.save(reservation);
        reservationProducer.sendMessage(event);

    }

    @QueryHandler
    public List<Reservation> on (GetAllResrvationsQuery query)
    {
        return reservationRepository.findAll();
    }
    @QueryHandler
    public Reservation on (GetReservationByIdQuery query)
    {
        return reservationRepository.findById(query.getId()).get();
    }
    @EventHandler
    public void on(ReservationAcceptedEvent event) {
        log.info("**************************");
        log.info("ReservationAcceptedEvent received");
        Optional<Reservation> optionalReservation = reservationRepository.findById(event.getReservationId());
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setStatus(ReservationStatus.ACCEPTED);

            reservationRepository.save(reservation);
        }
    }

    @EventHandler
    public void on(ReservationRejectedEvent event) {
        log.info("**************************");
        log.info("ReservationRejectedEvent received");
        Optional<Reservation> optionalReservation = reservationRepository.findById(event.getReservationId());
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setStatus(ReservationStatus.REJECTED);
            reservationRepository.save(reservation);
        }
    }

    public List<Reservation> findByidConsommateur(String id) {
        return reservationRepository.findByidConsommateur(id);
    }
    public List<Reservation> findByidAnnonce(String id) {
        return reservationRepository.findByidAnnonce(id);
    }


}
