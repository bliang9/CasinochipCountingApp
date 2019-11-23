package com.example.casinochipcountingapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JoinGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joingame);
        final TextView typeGameId = findViewById(R.id.typeGameId);
        Button joinGame = findViewById(R.id.joinGame);
        joinGame.setOnClickListener(unused -> joinTheGame(typeGameId.getText().toString()));
        Button createGame = findViewById(R.id.createGame);
    }
    public void joinTheGame(String gameId) {
        if (!gameId.equals("")) {

        }
    }
}
