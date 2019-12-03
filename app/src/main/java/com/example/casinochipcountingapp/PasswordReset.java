package com.example.casinochipcountingapp;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PasswordReset extends AppCompatActivity {
    private ProgressBar progressBar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);
        TextView email = findViewById(R.id.email);
        email.setTextColor(Color.YELLOW);
        email.setTextSize(20);
        EditText typeEmail = findViewById(R.id.typeEmail);
        typeEmail.getBackground().mutate().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        typeEmail.setTextColor(Color.GREEN);
        Button forgetPassword = findViewById(R.id.resetPassword);
        forgetPassword.setOnClickListener(unused -> forgetPassword());
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }
    public void forgetPassword() {
        EditText typeEmail = findViewById(R.id.typeEmail);
        if (typeEmail.getText().toString() == null || typeEmail.getText().toString().length() == 0) {
            TextView errorMessageNoEmail = findViewById(R.id.errorMessageNoEmail);
            errorMessageNoEmail.setTextColor(Color.RED);
            errorMessageNoEmail.setTextSize(20);
            errorMessageNoEmail.setText("Please type in email!");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        FirebaseAuth.getInstance().sendPasswordResetEmail(typeEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBar.setVisibility(View.INVISIBLE);
                if (task.isSuccessful()) {
                    Toast.makeText(PasswordReset.this, "Successfully sent! Check your email for instructions to reset!",Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(PasswordReset.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
