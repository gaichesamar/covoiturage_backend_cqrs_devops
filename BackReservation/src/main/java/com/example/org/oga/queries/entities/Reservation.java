package com.example.org.oga.queries.entities;

import com.example.org.oga.commonapi.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "reservationAnnonce")
public class Reservation {
    @Id
    private String id;
    private Boolean valide;
    private int nbrPlace;
    private  String idAnnonce;
    private  String idConducteurs;
    private String idConsommateur;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}
