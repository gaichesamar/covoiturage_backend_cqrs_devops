package com.example.CarMs.commonApi.events;

import com.example.CarMs.commonApi.enums.CarStatus;
import lombok.Getter;

public class CarUpdatedEvent extends BaseEvent<String> {

    @Getter private String marque;
    @Getter private String modele;
    @Getter private String immatriculation;
    @Getter private String couleur;
    @Getter private int nbrPlace;
    @Getter private String type;
    @Getter private CarStatus status;


    public CarUpdatedEvent(String id, String marque, String modele, String immatriculation, String couleur, int nbrPlace, String type, CarStatus status) {
        super(id);
        this.marque = marque;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.couleur = couleur;
        this.nbrPlace = nbrPlace;
        this.type = type;
        this.status = status;
    }
}
