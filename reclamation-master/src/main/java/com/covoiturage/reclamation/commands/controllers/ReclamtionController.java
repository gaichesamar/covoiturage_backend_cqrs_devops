package com.covoiturage.reclamation.commands.controllers;
import com.covoiturage.reclamation.commandsApi.commands.CreateReclamationCommand;
import com.covoiturage.reclamation.commandsApi.dtos.CreateReclamtiontRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
@RestController
@CrossOrigin(origins = "*")

@RequestMapping(path = "/commands/Reclamation")
public class ReclamtionController {
    private CommandGateway commandGateway;
    private EventStore eventStore;
    private final QueryGateway queryGateway;

    public ReclamtionController(CommandGateway commandGateway, EventStore eventStore, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
        this.queryGateway = queryGateway;
    }
    @PostMapping(path = "/create")
    public  CompletableFuture<String> createReclamation(@RequestBody CreateReclamtiontRequestDTO request) {
        CompletableFuture<String> commandResponse =commandGateway.send(new CreateReclamationCommand(
                UUID.randomUUID().toString(),
                request.getSujet(),
                request.getDescription(),
                request.getIdUser()
        ) ) ;
        return commandResponse;

    }
    @ExceptionHandler()
    public ResponseEntity<String> exceptionHandler(Exception exception)
    {
        ResponseEntity<String> entity= new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return entity;
    }
    @GetMapping("/eventStore/{ReclamationId}")
    public Stream eventStore(@PathVariable String ReclamationId){
        return (Stream)eventStore.readEvents(ReclamationId).asStream();
    }
}
