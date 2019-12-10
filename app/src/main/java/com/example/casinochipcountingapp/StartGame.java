package com.example.casinochipcountingapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class StartGame extends AppCompatActivity {

    private Button startGame;
    private Button exitGame;
    private Button playerList;
    private EditText addAmount;
    private Button addAgree;
    private TextView addtextView;
    private TextView gameNumber;
    private AlertDialog adddialog;
    public static int playerChipAmount;
    public static int gamenumber = 1;
    private String gameId;
    private ArrayList<String> players;
    private String owner;
    public LinearLayout myGameHistory;
    public ScrollView myRecordScroll;
    public static ArrayList<String> historyList = new ArrayList<>();
    public static int amountChange;
    public static int currentRound = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startgame);
        gameNumber = findViewById(R.id.gameNumber);
        String gn = Integer.toString(gamenumber);
        gameNumber.setText("Game " + gn);
        startGame = findViewById(R.id.startgame);
        exitGame = findViewById(R.id.exitgame);
        playerList = findViewById(R.id.playerList);
        players = new ArrayList<>();
        TextView textView = findViewById(R.id.textView5);
        textView.setText(getIntent().getStringExtra("ID"));

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(StartGame.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_add, null);
                addAmount = mView.findViewById(R.id.addAmount);
                addAgree = mView.findViewById(R.id.addAgree);
                addtextView = mView.findViewById(R.id.addtextView);


                addAgree.setOnClickListener(unused -> addChips());

                mBuilder.setView(mView);
                AlertDialog k = mBuilder.create();
                adddialog = k;
                adddialog.show();
                addAgree.setOnClickListener(unused -> addChips());
            }

        });

        exitGame.setOnClickListener(unused -> exitGame());

        playerList.setOnClickListener(unused -> enterPlayerList());



    }
    public void startGame() {
        //Game g = (Game) getIntent().getSerializableExtra("game");
        //g.updateGameState(1);

        historyList.add(gamenumber - 1, "Game "  + gamenumber + "  " + amountChange);


        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }
    public void exitGame() {
        playerChipAmount = 0;
        gamenumber = 1;
        currentRound = 1;
        //Game g = (Game) getIntent().getSerializableExtra("game");
        //g.updateGameState(2);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void enterPlayerList() {
        Intent intent = new Intent(this, PlayerListInGame.class);
        intent.putStringArrayListExtra("list", players);
        startActivity(intent);
        finish();
    }

    private void addChips() {
        String typein = addAmount.getText().toString();
        if (isNumeric(typein)) {
            int addamount = Integer.parseInt(typein);
            playerChipAmount += addamount;
            Toast.makeText(StartGame.this,
                    "Add Successful",
                    Toast.LENGTH_SHORT).show();

            adddialog.cancel();
            startGame();


        } else {
            Toast.makeText(StartGame.this,
                    "Only Type In Number",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


}