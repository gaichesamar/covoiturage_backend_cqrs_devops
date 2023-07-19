package com.covoiturage.paypal.commande.service;

import com.covoiturage.paypal.commonApi.Dt.CreatePaymentCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CreatePaymentCommandHandler {

    private final CommandGateway commandGateway;

    @Autowired
    public CreatePaymentCommandHandler(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> handle(CreatePaymentCommand command) {

        return commandGateway.send(command);
    }
}

