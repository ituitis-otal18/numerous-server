package com.app.Numerous.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.UUID;

public class Tweet {
    //Data
    private final UUID id;
    @NonNull
    private final UUID user;
    @NonNull
    private final int user_num;
    @NonNull
    private final String text;
    private final String date;

    //Constructor
    public Tweet(@JsonProperty("id") UUID id,
                 @JsonProperty("user") UUID user,
                 @JsonProperty("number") int user_num,
                 @JsonProperty("text") String text,
                 @JsonProperty("date") String date) {
        this.id = id;
        this.user = user;
        this.user_num = user_num;
        this.text = text;
        this.date = date;
    }

    //Getters
    public UUID getId() { return id; }
    public UUID getUser() { return user; }
    public int getUserNum() { return user_num; }
    public String getText() { return text; }
    public String getDate() { return date; }
}
