package com.example.casinochipcountingapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);
        Button createAccount = findViewById(R.id.createAccount);
        createAccount.setOnClickListener(unused -> createAccount());
    }
    public void createAccount() {
        TextView firstName1 = findViewById(R.id.email)
        EditText firstName = findViewById(R.id.typeFirstName);
        EditText middleName = findViewById(R.id.typeMiddleName);
        EditText lastName = findViewById(R.id.typeLastName);
        EditText email = findViewById(R.id.typeinEmail);
        EditText phoneNumber = findViewById(R.id.typeinPhoneNumber);
        EditText password = findViewById(R.id.typeinPassword);
        EditText confirmPassword = findViewById(R.id.typeinConfirmPassword)
    }
}
