package com.example.CarMs.commands.controllers;
import com.example.CarMs.commonApi.commands.AddCarCommand;
import com.example.CarMs.commonApi.commands.RemoveCarCommand;
import com.example.CarMs.commonApi.commands.UpdateCarCommand;
import com.example.CarMs.commonApi.dtos.AddCarRequestDTO;
import com.example.CarMs.commonApi.dtos.UpdateCarRequestDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
@RestController
@AllArgsConstructor
@RequestMapping(path = "/commands/car")
@CrossOrigin(origins = "*")
public class CarCommandController {
    private EventStore eventStore;
    private CommandGateway commandGateway;
    @PostMapping(path = "/Add")
    public  CompletableFuture<String> AddCar(@RequestBody AddCarRequestDTO request){
        CompletableFuture<String> commandResponse =commandGateway.send(new AddCarCommand(
        UUID.randomUUID().toString(),
        request.getMarque(),
        request.getModele(),
        request.getImmatriculation(),
        request.getCouleur(),
        request.getNbrPlace(),
        request.getType(),
                request.getIdUser()
               ));
    return commandResponse;
    }
    @PutMapping(path = "/update/{CarId}")
    public CompletableFuture<String>updateCar(@PathVariable(name = "CarId", required = false) String id, @RequestBody UpdateCarRequestDTO request) {

        CompletableFuture<String> commandResponse =commandGateway.send(new UpdateCarCommand(
                id,
                request.getMarque(),
                request.getModele(),
                request.getImmatriculation(),
                request.getCouleur(),
                request.getNbrPlace(),
                request.getType()
        ));
        return commandResponse.exceptionally(ex ->{
            throw new RuntimeException("");
        });
    }
    @DeleteMapping(path = "/delete/{CarId}")
    public ResponseEntity<String> RemoveCar(@PathVariable String CarId) {
        try {
            commandGateway.send(new RemoveCarCommand(CarId));
            return ResponseEntity.ok("");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }
    @ExceptionHandler()
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR
        );
        return entity;
    }
    @GetMapping("/eventStore/{CarId}")
    public Stream eventStore(@PathVariable String CarId){
        return eventStore.readEvents(CarId).asStream();
    }
}
