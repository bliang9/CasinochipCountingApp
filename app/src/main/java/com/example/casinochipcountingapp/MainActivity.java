package com.example.casinochipcountingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    public StorageReference everythingRef;
    public StorageReference allRoomIDRef;
    public FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storage = FirebaseStorage.getInstance();
        Button enter = findViewById(R.id.enter);
        everythingRef = storage.getReference();
        allRoomIDRef = everythingRef.child("allRoomID");
        enter.setOnClickListener(unused -> enterGame());
    }
    public void enterGame() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
