package com.example.casinochipcountingapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class JoinGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joingame);
        TextView gameID = findViewById(R.id.gameId);
        gameID.setTextColor(Color.GRAY);
        TextView typeGameId = findViewById(R.id.typeGameID);
        typeGameId.setTextColor(Color.GREEN);
        Button joinGame = findViewById(R.id.joinGame);
        joinGame.setOnClickListener(unused -> createGame());
        Button createGame = findViewById(R.id.createGame);
        createGame.setOnClickListener(unused -> createGame());
    }
    public void joinTheGame(String gameId) {
        if (!gameId.equals("")) {
            Intent intent = new Intent(this, GameActivity.class);
            //
        }
    }
    public void createGame() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }

}
