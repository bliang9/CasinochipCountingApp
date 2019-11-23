package com.example.casinochipcountingapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView welcomeToCasinoChip = findViewById(R.id.welcomeToCasinochip);
        Button enter = findViewById(R.id.enter);
    }
}
