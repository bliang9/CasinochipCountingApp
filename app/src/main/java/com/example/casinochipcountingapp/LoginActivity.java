package com.example.casinochipcountingapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private TextView typeinEmail;
    private TextView typeinPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = findViewById(R.id.login);
        login.setOnClickListener(unused -> loginIntoAccount());
        typeinEmail = findViewById(R.id.typeinEmail);
        typeinEmail.setTextColor(Color.GREEN);
        TextView email = findViewById(R.id.email);
        email.setTextColor(Color.GRAY);
        TextView password = findViewById(R.id.password);
        password.setTextColor(Color.GRAY);
        typeinPassword = findViewById(R.id.typeinPassword);
        typeinPassword.setTextColor(Color.GREEN);
        Button createAccount = findViewById(R.id.createAccount);
        createAccount.setOnClickListener(unused -> createAccount());
    }
    public void loginIntoAccount() {
        //
        String email = typeinEmail.getText().toString();
        String password = typeinPassword.getText().toString();
        if (email != null && password != null) {
            Intent intent = new Intent(this, JoinGameActivity.class);
            startActivity(intent);
            finish();
        }


    }
    public void createAccount() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
        finish();
    }
}
