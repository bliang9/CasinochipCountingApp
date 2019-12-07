package com.example.casinochipcountingapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;


import org.json.JSONObject;

import java.util.UUID;

public class JoinGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joingame);
        getActionBar().setTitle("Casino Chip Counting!");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        TextView gameID = findViewById(R.id.gameId);
        gameID.setTextColor(Color.GRAY);
        TextView typeGameId = findViewById(R.id.typeGameID);
        typeGameId.setTextColor(Color.GREEN);
        Button joinGame = findViewById(R.id.joinGame);
        joinGame.setOnClickListener(unused -> createGame());
        Button createGame = findViewById(R.id.createGame);
        createGame.setOnClickListener(unused -> createGame());
    }
    public void joinTheGame(String gameId) {
        if (!gameId.equals("")) {
            //
        }
    }
    public void createGame() {
        String id = UUID.randomUUID().toString();
        String url = "https:// 10.192.2.108/getinfo.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Intent intent = new Intent(JoinGameActivity.this, GameActivity.class);
                        intent.putExtra("Game ID", id);
                        startActivity(intent);
                        finish();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error

            }
        });
        requestQueue.add(jsonObjectRequest);

        Intent intent = new Intent(JoinGameActivity.this, GameActivity.class);
        intent.putExtra("game ID", id);
        startActivity(intent);
        finish();
    }

}
