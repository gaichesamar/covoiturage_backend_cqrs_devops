package com.example.org.oga.commonapi.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;



public class CreateReservationCommand {
    @Getter
    @TargetAggregateIdentifier
    private String id;
    @Getter
    private Boolean valide;
    @Getter private int nbrPlace;

    @Getter
    private String idAnnonce;
    @Getter
    private String idConsommateur;
    @Getter
    private String idConducteurs;

    public CreateReservationCommand(String id, int nbrPlace, Boolean valide, String idAnnonce, String idConsommateur, String idConducteurs) {
        this.id = id;
        this.nbrPlace=nbrPlace;
        this.valide = valide;
        this.idAnnonce = idAnnonce;
        this.idConsommateur = idConsommateur;
        this.idConducteurs = idConducteurs;
    }


}
