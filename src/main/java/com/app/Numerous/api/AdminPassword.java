package com.app.Numerous.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

public class AdminPassword {

    private final String adminPassword = "admin1234";
    @NonNull
    private final String PW;

    public AdminPassword(@JsonProperty("adminPw") String PW) { this.PW = PW; }

    public Boolean check(){ return adminPassword.equals(PW); }
}
