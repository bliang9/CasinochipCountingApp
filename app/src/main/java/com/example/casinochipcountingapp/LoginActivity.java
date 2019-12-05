package com.example.casinochipcountingapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private TextView typeinEmail;
    private TextView typeinPassword;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = findViewById(R.id.login);
        login.setOnClickListener(unused -> loginIntoAccount());
        Button forgetPassword = findViewById(R.id.forgetPassword);
        forgetPassword.setOnClickListener(unused -> forgetPassword());
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
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        TextView error = findViewById(R.id.errorMessage);
        error.setVisibility(View.INVISIBLE);
    }
    public void forgetPassword() {
        Intent intent = new Intent(LoginActivity.this, PasswordReset.class);
        startActivity(intent);
        finish();
    }
    public void loginIntoAccount() {
        //
        String email = typeinEmail.getText().toString();
        String password = typeinPassword.getText().toString();
        TextView errorMessage = findViewById(R.id.errorMessage);
        errorMessage.setTextSize(25);
        errorMessage.setTextColor(Color.RED);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging in");
        if (email != null && password != null && email.length() != 0 && password.length() != 0) {
            progressDialog.show();
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
                    alertDialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.create().dismiss();
                        }
                    });
                    if (task.isSuccessful()) {
                        alertDialog.setMessage("Successfully Logged In!");
                        alertDialog.create().show();
                        System.out.println(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
                        System.out.println(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                        Intent intent = new Intent(LoginActivity.this, TwoFactorAuthActivity.class);
                        intent.putExtra("Phone Number", FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
                        startActivity(intent);
                        finish();
                        //User current = (User) user;
                    } else {
                        alertDialog.setMessage(task.getException().getMessage());
                        alertDialog.create().show();
                    }
                }
            });
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
