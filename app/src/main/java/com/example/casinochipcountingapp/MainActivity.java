package com.example.casinochipcountingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView welcomeToCasinoChip = findViewById(R.id.welcomeToCasinochip);
        welcomeToCasinoChip.setTextColor(Color.GREEN);
        Button enter = findViewById(R.id.enter);
        enter.setOnClickListener(unused -> enterGame());
    }
    public void enterGame() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
