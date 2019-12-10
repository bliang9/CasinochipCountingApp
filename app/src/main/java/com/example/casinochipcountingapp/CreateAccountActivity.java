package com.example.casinochipcountingapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.zzy;
import com.google.firebase.auth.zzz;
import com.hbb20.CountryCodePicker;

import java.util.List;

public class CreateAccountActivity extends AppCompatActivity {
    private EditText typefirstName;
    private EditText typeMiddleName;
    private EditText typelastName;
    private EditText typeEmail;
    private EditText typePassword;
    private EditText typeConfirmPassword;
    private Button cancelCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);
        //first name;
        TextView firstName = findViewById(R.id.firstName);
        typefirstName = findViewById(R.id.typeFirstName);
        typefirstName.setTextSize(20);
        typefirstName.getBackground().mutate().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        typefirstName.setTextColor(Color.BLACK);
        //middle name
        TextView middleName = findViewById(R.id.middleName);
        typeMiddleName = findViewById(R.id.typeMiddleName);
        typeMiddleName.setTextSize(20);
        typeMiddleName.getBackground().mutate().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        typeMiddleName.setTextColor(Color.BLACK);
        //lastName
        TextView lastName = findViewById(R.id.lastName);
        typelastName = findViewById(R.id.typeLastName);
        typelastName.setTextSize(20);
        typelastName.getBackground().mutate().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        typelastName.setTextColor(Color.BLACK);
        //email
        TextView email = findViewById(R.id.email);
        typeEmail = findViewById(R.id.typeinEmail);
        typeEmail.setTextSize(20);
        typeEmail.getBackground().mutate().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        typeEmail.setTextColor(Color.BLACK);
        //phone number
        //password
        TextView password = findViewById(R.id.password);
        typePassword = findViewById(R.id.typeinPassword);
        typePassword.setTextSize(20);
        typePassword.getBackground().mutate().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        typePassword.setTextColor(Color.BLACK);
        //confirm password
        TextView confirmedpassword = findViewById(R.id.confirmPassword);
        typeConfirmPassword= findViewById(R.id.typeinConfirmPassword);
        typeConfirmPassword.setTextSize(20);
        typeConfirmPassword.getBackground().mutate().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        typeConfirmPassword.setTextColor(Color.BLACK);
        //create account button
        Button createAccount = findViewById(R.id.createAccount1);
        createAccount.setOnClickListener(unused -> createAccount());
        cancelCreateAccount = findViewById(R.id.cancelCreateAccount);
        cancelCreateAccount.setOnClickListener(unused -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });


    }

    public void createAccount() {
        boolean allFilledIn = true;
        TextView errorMesFirstName = findViewById(R.id.errorMessagefirstName);
        if (typefirstName.getText().toString() == null
            || typefirstName.getText().toString().length() == 0) {
            errorMesFirstName.setTextSize(20);
            errorMesFirstName.setTextColor(Color.RED);
            errorMesFirstName.setText("Enter First Name!");
            allFilledIn = false;
        }
        TextView errorMesLastName = findViewById(R.id.errorMessageLastName);
        if (typelastName.getText().toString() == null
                || typelastName.getText().toString().length() == 0) {
            errorMesLastName.setTextSize(20);
            errorMesLastName.setTextColor(Color.RED);
            errorMesLastName.setText("Enter Last Name!");
            allFilledIn = false;
        }
        TextView errorMesemail = findViewById(R.id.errorMessageEmail);
        if (typeEmail.getText().toString() == null
                || typeEmail.getText().toString().length() == 0) {
            errorMesemail.setTextSize(20);
            errorMesemail.setTextColor(Color.RED);
            errorMesemail.setText("Enter Email!");
            allFilledIn = false;
        }

        String passWord = typePassword.getText().toString();
        TextView errorMesPassword = findViewById(R.id.errorMessagePassword);
        TextView errorMesConfirmPassword = findViewById(R.id.errorMessageConfirmPassword);
        if (passWord == null || passWord.length() == 0) {
            errorMesPassword.setTextSize(20);
            errorMesPassword.setTextColor(Color.RED);
            errorMesPassword.setText("Enter Password!");
            allFilledIn = false;
        } else if (typeConfirmPassword.getText().toString() == null
                || typeConfirmPassword.getText().toString().length() == 0) {
            errorMesConfirmPassword.setTextSize(20);
            errorMesConfirmPassword.setTextColor(Color.RED);
            errorMesConfirmPassword.setText("Please confirm password!");
            allFilledIn = false;
        } else if (!typeConfirmPassword.getText().toString().equals(passWord)) {
            errorMesConfirmPassword.setTextSize(20);
            errorMesConfirmPassword.setTextColor(Color.RED);
            errorMesConfirmPassword.setText("Original Password and Confirmed password don't equal!");
            allFilledIn = false;
        } else if (!checkPasswordLength(passWord)) {
            errorMesConfirmPassword.setTextSize(20);
            errorMesConfirmPassword.setTextColor(Color.RED);
            errorMesConfirmPassword.setText("Password must be at least 6 characters!");
            allFilledIn = false;
        }
        boolean loggedIn = false;
        //retrieved from https://firebase.google.com/docs/auth/admin/manage-users
        if (allFilledIn) {
            String name = "";
            if (typeMiddleName.getText().toString() != null) {
                name = typefirstName.getText().toString() +
                        typeMiddleName.getText().toString() +
                        typelastName.getText().toString();
            } else {
                name = typefirstName.getText().toString() +
                        typelastName.getText().toString();
            }
            final String fullName = name;
            //send email  verification
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Creating account");
            progressDialog.show();
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateAccountActivity.this);
            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    builder.create().dismiss();
                }
            });
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(typeEmail.getText().toString(), typeConfirmPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(fullName).build();
                        FirebaseAuth.getInstance().getCurrentUser().updateProfile(profileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            progressDialog.dismiss();
                                            if (task.isSuccessful()) {
                                                Intent intent = new Intent(CreateAccountActivity.this, JoinGameActivity.class);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                builder.setMessage(task.getException().getMessage());
                                                builder.create().show();
                                            }
                                        }
                                    });
                                } else {
                                    progressDialog.dismiss();
                                    builder.setMessage(task.getException().getMessage());
                                    builder.create().show();
                                }
                            }
                         });
                    } else {
                        progressDialog.dismiss();
                        builder.setMessage(task.getException().getMessage());
                        builder.create().show();
                    }
                }
            });

        }
    }
    public boolean checkPasswordLength(String password) {
        if (password.length() >= 6) {
            return true;
        }
        return false;
    }
}
