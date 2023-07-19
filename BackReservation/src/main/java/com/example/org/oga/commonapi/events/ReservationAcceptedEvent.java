package com.example.org.oga.commonapi.events;

public class ReservationAcceptedEvent {
    private String reservationId;

    public ReservationAcceptedEvent(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservationId() {
        return reservationId;
    }
}