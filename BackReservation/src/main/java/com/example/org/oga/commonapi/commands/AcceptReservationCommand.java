package com.example.org.oga.commonapi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class AcceptReservationCommand {
    @TargetAggregateIdentifier

    private String reservationId;

    public AcceptReservationCommand(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservationId() {
        return reservationId;
    }
}