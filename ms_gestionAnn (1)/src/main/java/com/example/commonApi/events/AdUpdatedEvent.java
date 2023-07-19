package com.example.commonApi.events;

import com.example.commonApi.enums.AdStatus;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AdUpdatedEvent extends baseEvent<String> {

    @Getter private String description;
    @Getter private LocalDate dateDep;
    @Getter private LocalDate dateArr;

    @Getter private String heureDep;
    @Getter private String heureArr;
    @Getter private BigDecimal prixPlace;
    @Getter private String lieuDep;
    @Getter private int nbrPlace;
    @Getter private String lieuArr;
    @Getter private boolean animaux_de_companie;
    @Getter private boolean cigarette ;
    @Getter private boolean aller_retour;
    @Getter private String descriptionvoyage;

    @Getter private AdStatus adStatus;

    public AdUpdatedEvent(String id, String description, LocalDate dateDep, LocalDate dateArr, String heureDep, String heureArr, BigDecimal prixPlace, String lieuDep, int nbrPlace, String lieuArr, boolean animaux_de_companie, boolean cigarette, boolean aller_retour, String descriptionvoyage, AdStatus adStatus) {
        super(id);
        this.description = description;
        this.dateDep = dateDep;
        this.dateArr = dateArr;
        this.heureDep = heureDep;
        this.heureArr = heureArr;
        this.prixPlace = prixPlace;
        this.lieuDep = lieuDep;
        this.nbrPlace = nbrPlace;
        this.lieuArr = lieuArr;
        this.animaux_de_companie = animaux_de_companie;
        this.cigarette = cigarette;
        this.aller_retour = aller_retour;
        this.descriptionvoyage=descriptionvoyage;
        this.adStatus = adStatus;
    }
}
