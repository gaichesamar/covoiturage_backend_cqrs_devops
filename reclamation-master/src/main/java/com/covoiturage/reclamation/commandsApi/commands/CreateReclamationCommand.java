package com.covoiturage.reclamation.commandsApi.commands;

import lombok.Getter;

public class CreateReclamationCommand extends baseCommand<String> {

    @Getter
    private String sujet;
    @Getter
    private String description;
    @Getter private String idUser;



    public CreateReclamationCommand(String id,String sujet,String description,String idUser) {
        super(id);
        this.sujet=sujet;
        this.description=description;
        this.idUser=idUser;
    }
}
