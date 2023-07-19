package com.example.org.oga.commonapi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RejectReservationCommand {
    @TargetAggregateIdentifier

    private String reservationId;

    public RejectReservationCommand(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservationId() {
        return reservationId;
    }
}