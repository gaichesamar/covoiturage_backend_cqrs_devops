package com.covoiturage.reclamation.query.entities;

import com.covoiturage.reclamation.commandsApi.enums.ReclamationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Reclamations")

public class Reclamation {
    @Id
    private String id;
    @Column(length = 500)
    private String sujet;
    @Column(length = 500)
    private String description;
    private  String idUser;
    @Enumerated(EnumType.STRING)
    private ReclamationStatus status;
}
