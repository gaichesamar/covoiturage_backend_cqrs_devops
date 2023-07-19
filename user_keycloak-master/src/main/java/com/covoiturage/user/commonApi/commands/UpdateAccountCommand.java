package com.covoiturage.user.commonApi.commands;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class UpdateAccountCommand extends BaseCommand<String> {
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String firstname;
    @Getter
    @Setter
    private String lastname;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String Adress;
    @Getter @Setter private Date DateOfBirth;
    @Getter @Setter private String Telephone;
    @Getter
    @Setter
    private String photo;
    @Getter
    @Setter
    private Integer note;


    public UpdateAccountCommand(String id, String username, String firstname, String lastname, String email, String password, Date DateOfBirth, String Adress, String Telephone, String photo,Integer note) {
        super(id);
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.DateOfBirth = DateOfBirth;
        this.Adress = Adress;
        this.Telephone = Telephone;
        this.photo = photo;
        this.note=note;
    }


}

