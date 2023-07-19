package com.covoiturage.reclamation.commandsApi.queries;

public class GetByUserId {
    private String idUser;
    public GetByUserId (String idUser) {
        this.idUser= idUser;
    }

    public String getIdUser() {
        return idUser;
    }

}