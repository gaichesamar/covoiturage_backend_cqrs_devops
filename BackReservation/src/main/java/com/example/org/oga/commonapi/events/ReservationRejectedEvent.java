package com.example.org.oga.commonapi.events;

public class ReservationRejectedEvent {
    private String reservationId;

    public ReservationRejectedEvent(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservationId() {
        return reservationId;
    }
}