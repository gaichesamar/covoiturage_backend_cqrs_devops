package com.covoiturage.paypal.commonApi.commands;


import lombok.Getter;


public class CreateAdCommand extends baseCommand<String> {
    @Getter private long prix;
    @Getter private String nom;
    @Getter private String prenom;
    @Getter private String email;
    @Getter private long telephone;
    public CreateAdCommand(String id, Long prix, String nom, String prenom , String email, long telephone)
    {
        super(id);
        this.prix = prix;
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
        this.telephone=telephone;

    }
}

