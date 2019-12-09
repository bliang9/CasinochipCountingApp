package com.example.casinochipcountingapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.JsonObject;
//

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JoinGameActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private FirebaseDatabase firebaseDatabase;
    private List<DatabaseReference> keys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joingame);
        firebaseDatabase = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(JoinGameActivity.this);
        TextView gameID = findViewById(R.id.gameId);
        gameID.setTextColor(Color.GRAY);
        TextView typeGameId = findViewById(R.id.typeGameID);
        typeGameId.setTextColor(Color.GREEN);
        Button joinGame = findViewById(R.id.joinGame);
        joinGame.setOnClickListener(unused -> joinTheGame(typeGameId.getText().toString()));
        Button createGame = findViewById(R.id.createGame);
        createGame.setOnClickListener(unused -> createGame());
        keys = new ArrayList<>();
    }
    public void joinTheGame(String gameId) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gameToStart = firebaseDatabase.getReference("gamesToStart");
        if (!gameId.equals("")) {
            progressDialog.setMessage("Joining Game");
            progressDialog.show();
           if (gameToStart.child("gameID").equals(gameId)) {

           }
        } else {
            TextView errorMessage = findViewById(R.id.errorMessage);
            errorMessage.setText("Type In Game ID");
            errorMessage.setTextColor(Color.RED);
            errorMessage.setTextSize(20);
        }
    }
    public void createGame() {
        progressDialog.setMessage("Creating game");
        progressDialog.show();
        AlertDialog.Builder dialog = new AlertDialog.Builder(JoinGameActivity.this);
        dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        String id = UUID.randomUUID().toString();
        progressDialog.dismiss();
        if (id == null) {
            dialog.setMessage("Unable to Create Game");
            dialog.create().show();
        }
        //retrieved from https://www.youtube.com/watch?v=i_GuZ_6ZRJM
        Intent intent = new Intent(JoinGameActivity.this, StartGame.class);
        intent.putExtra("ID", id);
        List<String> players = new ArrayList<>();
        players.add(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        Game game = new Game(id, 0, players, FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        intent.putExtra("game", game);
        firebaseDatabase.getReference("gamesToStart").push().setValue(game);
        startActivity(intent);
        finish();
    }

}
