package com.example.CarMs.commonApi.query;

public class GetCarByIdUser {
    private String idUser;

    public GetCarByIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdUser() {
        return idUser;
    }
}