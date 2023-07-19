package com.example.org.oga.commands.controllers;


import com.example.org.oga.commonapi.commands.AcceptReservationCommand;
import com.example.org.oga.commonapi.commands.CreateReservationCommand;
import com.example.org.oga.commonapi.commands.DeleteReservationCommand;
import com.example.org.oga.commonapi.commands.RejectReservationCommand;
import com.example.org.oga.commonapi.dtos.CreateReservationRequestDTO;


import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin (origins = "*")
@RequestMapping(path="/commands/reservation")
@AllArgsConstructor
public class ReservationCommandController {
    private CommandGateway commandGateway;

    private EventStore eventStore;

    @PostMapping(path = "/create")
    public CompletableFuture<String> createReservation(@RequestBody CreateReservationRequestDTO request) {
        CompletableFuture<String> commandeResponse = commandGateway.send(new CreateReservationCommand(
                UUID.randomUUID().toString(),
                request.getNbrPlace(),
                request.getValide(),
                request.getIdAnnonce(),
                request.getIdConsommateur(),
                request.getIdConducteurs()
        ));
        return commandeResponse;
    }
    @GetMapping("/{reservationId}/accept")
    public void acceptReservation(@PathVariable String reservationId) {

        AcceptReservationCommand command = new AcceptReservationCommand(reservationId);
        commandGateway.sendAndWait(command);
    }

    @GetMapping("/{reservationId}/reject")
    public void rejectReservation(@PathVariable String reservationId) {
        RejectReservationCommand command = new RejectReservationCommand(reservationId);
        commandGateway.sendAndWait(command);
    }

    @DeleteMapping("/{reservationId}")
    public void deleteReservation(@PathVariable String reservationId) {
        DeleteReservationCommand command = new DeleteReservationCommand(reservationId);
        commandGateway.sendAndWait(command);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        ResponseEntity<String> entity = new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }
    @GetMapping("/eventStore/{reservationId}")
    public java.util.stream.Stream eventStore(@PathVariable String reservationId) {
        return eventStore.readEvents(reservationId).asStream();
    }




}
