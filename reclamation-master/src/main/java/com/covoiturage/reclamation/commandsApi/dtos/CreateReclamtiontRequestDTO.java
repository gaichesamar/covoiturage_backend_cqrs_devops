package com.covoiturage.reclamation.commandsApi.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReclamtiontRequestDTO {
    private String sujet;
    private String description;
    private  String idUser;
}
