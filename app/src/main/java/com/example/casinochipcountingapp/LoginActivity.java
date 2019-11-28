package com.example.casinochipcountingapp;

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
        typeinPassword = findViewById(R.id.typeinPassword);

    }
    public void loginIntoAccount() {
        //
        String email = typeinEmail.getText().toString();
        String password = typeinPassword.getText().toString();


    }
}
