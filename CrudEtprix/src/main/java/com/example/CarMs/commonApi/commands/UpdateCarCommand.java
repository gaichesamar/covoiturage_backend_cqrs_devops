package com.example.CarMs.commonApi.commands;
import lombok.Getter;
public class UpdateCarCommand extends BaseCommand<String>{
    @Getter private String marque;
    @Getter private String modele;
    @Getter private String immatriculation;
    @Getter private String couleur;
    @Getter private int nbrPlace;
    @Getter private String type;
    public UpdateCarCommand(String id, String marque, String modele, String immatriculation, String couleur, int nbrPlace, String type) {
        super(id);
        this.marque = marque;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.couleur = couleur;
        this.nbrPlace = nbrPlace;
        this.type = type;
    }


}
