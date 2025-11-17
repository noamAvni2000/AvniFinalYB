package com.example.avnifinalyb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Usernames")
public class  Usernames{
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String username;

    private String password;

    public Usernames(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Usernames() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
