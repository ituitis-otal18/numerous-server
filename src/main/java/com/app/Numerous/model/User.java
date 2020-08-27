package com.app.Numerous.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.util.UUID;

public class User {
    //Data
    private final UUID id;
    private final int number;
    @NonNull
    private final String mail;
    @NonNull
    private final String password;

    //Constructor
    public User(@JsonProperty("id") UUID id,
                 @JsonProperty("number") int number,
                 @JsonProperty("mail") String mail,
                 @JsonProperty("password") String password) {
        this.id = id;
        this.number = number;
        this.mail = mail;
        this.password = password;
    }

    //Getters
    public UUID getId() { return id; }
    public int getNumber() { return number; }
    public String getMail() { return mail; }
    public String getPassword() { return password; }
}
