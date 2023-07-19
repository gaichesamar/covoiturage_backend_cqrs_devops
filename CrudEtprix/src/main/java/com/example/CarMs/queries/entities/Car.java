package com.example.CarMs.queries.entities;

import com.example.CarMs.commonApi.enums.CarStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car {
@Id
private String id;
    private String marque;
    private String modele;
     private String immatriculation;
     private String couleur;
     private int nbrPlace;
     private String type;
     @Enumerated(EnumType.STRING)
    private CarStatus status;
}
