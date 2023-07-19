package com.example.org.oga.commonapi.events;

import com.example.org.oga.commonapi.enums.ReservationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor

public class ReservationCreatedEvent extends BaseEvent<String>{

    @Getter
    private Boolean valide;

    @Getter private int nbrPlace;
    @Getter private String idAnnonce;
    @Getter private String idConsommateur;
    @Getter private String idConducteurs;
    @Getter private ReservationStatus status;

    public ReservationCreatedEvent(String id  , int nbrPlace, Boolean valide, String idAnnonce, String idConsommateur, String idConducteurs, ReservationStatus status) {
        super(id);
        this.valide= valide;
        this.nbrPlace =nbrPlace;
        this.idAnnonce=idAnnonce;
        this.idConsommateur=idConsommateur;
        this.idConducteurs=idConducteurs;
        this.status = status;
    }


}
