package com.example.casinochipcountingapp;

import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;
import java.util.List;

public class Game implements Serializable {
    public String gameId;
    public String owner;
    public List<String> players;
    public int gameState;
    public Game(String gameId, int gameState, List<String> players, String owner) {
        this.gameId = gameId;
        this.owner = owner;
        this.players = players;
        this.gameState = gameState;
    }
    //if playing, gameState is 1. Started and setting up, gamestate is 0. Ended, gamestate is 2.
    public void updateGameState(int gameState) {
        this.gameState = gameState;
    }
}
