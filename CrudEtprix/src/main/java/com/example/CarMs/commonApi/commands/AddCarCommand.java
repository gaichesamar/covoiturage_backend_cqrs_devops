package com.example.CarMs.commonApi.commands;

import lombok.Getter;

public class AddCarCommand extends BaseCommand<String>{

    @Getter private String marque;
    @Getter private String modele;
    @Getter private String immatriculation;
    @Getter private String couleur;
    @Getter private int nbrPlace;
    @Getter private String type;
    @Getter private String idUser;


    public AddCarCommand(String id, String marque, String modele, String immatriculation, String couleur, int nbrPlace, String type,String idUser) {
        super(id);
        this.marque = marque;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.couleur = couleur;
        this.nbrPlace = nbrPlace;
        this.type = type;
        this.idUser=idUser;
    }
}
