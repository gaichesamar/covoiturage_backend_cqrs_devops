package com.covoiturage.paypal.commonApi.Dt;

import lombok.*;

@Data
@AllArgsConstructor
public class createAdRequestDTO {

    private long prix;
    private String nom;
    private String prenom;
    private String email;
    private long telephone;
}

