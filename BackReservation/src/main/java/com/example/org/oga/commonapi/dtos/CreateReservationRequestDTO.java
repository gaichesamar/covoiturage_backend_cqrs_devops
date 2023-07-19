package com.example.org.oga.commonapi.dtos;

import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CreateReservationRequestDTO {

    private Boolean valide;
    private int nbrPlace;
    private String idAnnonce;
    private String idConsommateur;
   private String idConducteurs;


}
