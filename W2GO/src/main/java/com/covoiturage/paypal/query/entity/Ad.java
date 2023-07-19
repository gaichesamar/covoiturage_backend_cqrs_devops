package com.covoiturage.paypal.query.entity;

import com.covoiturage.paypal.commonApi.Enum.AdStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ad {
    @Id
    private String id;
    private long prix;
    private String nom;
    private String prenom;
    private String email;
    private long telephone;


    @Enumerated(EnumType.STRING)
    private AdStatus status;


}
