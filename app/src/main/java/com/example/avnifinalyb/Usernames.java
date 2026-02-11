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

    private int aiWinAmount;

    private int gamesPlayed;

    private int guessAmount;

    private double avgGuessAmount;

    public Usernames(String username, String password) {
        this.username = username;
        this.password = password;
        this.winAmount=0;
        this.avgGuessAmount=0;
        this.aiWinAmount=0;
        this.gamesPlayed=0;
        this.guessAmount=0;
    }

    public Usernames(String username, String password, int winAmount, int aiWinAmount, int gamesPlayed, int guessAmount) {
        this.username = username;
        this.password = password;
        this.winAmount=winAmount;
        this.aiWinAmount=aiWinAmount;
        this.gamesPlayed=gamesPlayed;
        this.guessAmount=guessAmount;
        if(this.gamesPlayed!=0)
            this.avgGuessAmount=this.guessAmount/this.gamesPlayed;
        else
            this.avgGuessAmount=0;
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

    public int getAiWinAmount() {
        return aiWinAmount;
    }

    public void setAiWinAmount(int aiWinAmount) {
        this.aiWinAmount = aiWinAmount;
    }

    public double getAvgGuessAmount(){
        return avgGuessAmount;
    }

    public void setAvgGuessAmount(double avgGuessAmount){
        this.avgGuessAmount=avgGuessAmount;
    }

    public int getGamesPlayed(){
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed){
        this.gamesPlayed=gamesPlayed;
    }

    public int getGuessAmount(){
        return guessAmount;
    }

    public void setGuessAmount(int guessAmount){
        this.guessAmount=guessAmount;
    }
}
