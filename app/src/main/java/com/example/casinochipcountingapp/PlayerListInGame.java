package com.example.casinochipcountingapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PlayerListInGame extends AppCompatActivity {
    private List<String> players;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playerlist);
        players = new ArrayList<String>();
        ArrayList<String> players = getIntent().getStringArrayListExtra("list");
        for (int i = 0; i < players.size(); i++) {
            TextView view = new TextView(this);
            view.setText(players.get(i));
            view.setTextColor(Color.GREEN);
            view.setTextSize(20);
        }

    }
}
