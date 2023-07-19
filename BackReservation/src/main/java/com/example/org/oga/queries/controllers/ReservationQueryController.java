package com.example.org.oga.queries.controllers;

import com.example.org.oga.commonapi.query.GetAllResrvationsQuery;
import com.example.org.oga.commonapi.query.GetReservationByIdQuery;
import com.example.org.oga.queries.entities.Reservation;
import com.example.org.oga.queries.repository.ReservationRepository;
import com.example.org.oga.queries.services.ReservationServiceHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/query/resrvations")
public class ReservationQueryController {
    private QueryGateway queryGateway;
    private ReservationServiceHandler reservationServiceHandler;
    private ReservationRepository reservationRepository;

    @GetMapping("/GetAllReservations")
    public List<Reservation> reservationList() {
        List<Reservation> reponse = queryGateway.query(new GetAllResrvationsQuery(), ResponseTypes.multipleInstancesOf(Reservation.class)).join();
        return reponse;
    }

    @GetMapping("GetResrvationById/{id}")
        public Reservation getReservation (@PathVariable String id){
            return queryGateway.query(new GetReservationByIdQuery(id), ResponseTypes.instanceOf(Reservation.class)).join();
        }
    @GetMapping("/reservation/{id}")
    public List<Reservation> geByIdConducteur(@PathVariable String id) {
        return  reservationServiceHandler.findByidConsommateur(id);
    }
@GetMapping("/reservationAnnonce/{id}")
public List<Reservation>  findByidAnnonce(@PathVariable String id) {
        return  reservationServiceHandler.findByidAnnonce(id);
    }
    }



