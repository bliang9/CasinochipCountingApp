package com.example.casinochipcountingapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class TwoFactorAuthActivity extends AppCompatActivity {
    private String phoneNumber;
    private EditText typeVerificationCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twofactorauth);
        System.out.println(FirebaseAuth.getInstance().getCurrentUser() != null);
        phoneNumber = getIntent().getStringExtra("Phone Number");
        typeVerificationCode = findViewById(R.id.typeVerificationCode);

    }
    public void verificationCode() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                90,
                TimeUnit.SECONDS,
                this, callBack);
    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks callBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            if (phoneAuthCredential.getSmsCode().equals(typeVerificationCode.getText().toString())) {
                //
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
        }
    };

}
