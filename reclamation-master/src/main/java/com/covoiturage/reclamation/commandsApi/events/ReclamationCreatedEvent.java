package com.covoiturage.reclamation.commandsApi.events;

import com.covoiturage.reclamation.commandsApi.enums.ReclamationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor

public class ReclamationCreatedEvent extends BaseEvent<String> {
    @Getter
    private String sujet;
    @Getter
    private String description;
    @Getter private String idUser;

    @Getter
    private ReclamationStatus status;

    public ReclamationCreatedEvent(String id,String sujet,String description,String idUser,ReclamationStatus status){
        super(id);
        this.sujet=sujet;
        this.description=description;
        this.idUser=idUser;
        this.status=status;
    }
}
