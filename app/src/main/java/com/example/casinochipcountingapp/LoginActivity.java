package com.example.casinochipcountingapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
        typeinEmail.getBackground().mutate().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        typeinEmail.setTextColor(Color.GREEN);
        TextView email = findViewById(R.id.email);
        email.setTextColor(Color.YELLOW);
        TextView password = findViewById(R.id.password);
        password.setTextColor(Color.YELLOW);
        typeinPassword = findViewById(R.id.typeinPassword);
        typeinPassword.getBackground().mutate().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        typeinPassword.setTextColor(Color.GREEN);
        Button createAccount = findViewById(R.id.createAccount);
        createAccount.setOnClickListener(unused -> createAccount());
        TextView error = findViewById(R.id.errorMessage);
        error.setVisibility(View.INVISIBLE);
    }
    public void loginIntoAccount() {
        //
        String email = typeinEmail.getText().toString();
        String password = typeinPassword.getText().toString();
        TextView errorMessage = findViewById(R.id.errorMessage);
        errorMessage.setTextSize(25);
        errorMessage.setTextColor(Color.RED);
        if (email != null && password != null && email.length() != 0 && password.length() != 0) {
            //
        } else if ((email == null && password == null) || (email.length() == 0 && password.length() == 0)) {
            errorMessage.setText("Please type in email and password!");
            errorMessage.setVisibility(View.VISIBLE);
            return;
        } else if (email == null || email.length() == 0) {
            errorMessage.setText("Please type in email!");
            errorMessage.setVisibility(View.VISIBLE);
            return;
        } else if (password == null || password.length() == 0) {
            errorMessage.setText("Please type in password!");
            errorMessage.setVisibility(View.VISIBLE);
            return;
        }


    }
    public void createAccount() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
        finish();
    }
}
