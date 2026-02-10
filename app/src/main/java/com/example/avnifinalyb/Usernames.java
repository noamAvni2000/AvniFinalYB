package com.example.avnifinalyb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Usernames")
public class  Usernames{
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String username;

    private String password;

    private int winAmount;

    private double avgGuessAmount;

    public Usernames(String username, String password) {
        this.username = username;
        this.password = password;
        this.winAmount=0;
        this.avgGuessAmount=0;
    }

    public Usernames(String username, String password, int winAmount, double avgGuessAmount) {
        this.username = username;
        this.password = password;
        this.winAmount=winAmount;
        this.avgGuessAmount=avgGuessAmount;
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

    public int getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(int winAmount) {
        this.winAmount = winAmount;
    }

    public double getAvgGuessAmount(){
        return avgGuessAmount;
    }

    public void setAvgGuessAmount(double avgGuessAmount){
        this.avgGuessAmount=avgGuessAmount;
    }
}
