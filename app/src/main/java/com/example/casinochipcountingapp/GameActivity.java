package com.example.casinochipcountingapp;

import android.content.Intent;
import android.graphics.Color;
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

import java.util.ArrayList;

public class GameActivity extends StartGame {

    private int currentRound = 1;
    private ArrayList<String> recordList;
    private TextView playerAmount;
    private Button exit;
    private Button nextRound;
    private Button record;
    private Button exitAgree;
    private Button exitCancel;
    private Button confirmReady;
    private Button cancelReady;
    private Button newGame;
    private Button newGameExit;
    private TextView readyNext;
    private TextView exitAlert;
    private TextView gameFinished;
    private TextView gameNumber;
    private AlertDialog nextdialog;
    private AlertDialog exitdialog;
    private AlertDialog newgamedialog;


    private TextView betTextView;
    private EditText editBet;
    private Button betAgree;
    private Button fold;
    private Button check;
    private Button call;
    private Button allin;
    private AlertDialog betdialog;

    public int betAmount;
    public static int amountStart;
    public static int amountEnd;
    public static int amountChange;


    /**
     *
     * @param savedInstanceState
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        amountStart = playerChipAmount;
        String amount = Integer.toString(playerChipAmount);
        ((TextView) findViewById(R.id.playerAmount)).setText(amount);
        nextRound = findViewById(R.id.nextRound);
        record = findViewById(R.id.record);

        exit = findViewById(R.id.exit);

        exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(GameActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_exitwarning, null);
                exitAlert = mView.findViewById(R.id.exitAlert);
                exitAgree = mView.findViewById(R.id.exitagree);
                exitCancel = mView.findViewById(R.id.exitcancel);

                exitAgree.setOnClickListener(unused -> exitGame());
                exitCancel.setOnClickListener(unused -> {
                    exitdialog.cancel();
                });

                mBuilder.setView(mView);
                AlertDialog k = mBuilder.create();
                exitdialog = k;
                exitdialog.show();
            }

        });

        record.setOnClickListener(unused -> visitRecord());


        nextRound.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(GameActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_nextround, null);
                readyNext = mView.findViewById(R.id.readyNext);
                confirmReady = mView.findViewById(R.id.confirmReady);
                cancelReady = mView.findViewById(R.id.cancelReady);
                countRound();
                mBuilder.setView(mView);
                AlertDialog k = mBuilder.create();
                nextdialog = k;
                nextdialog.show();
            }

        });



    }

    private void visitRecord() {
        Intent intent = new Intent(this, GameRecordList.class);
        startActivity(intent);
        finish();

    }


    private void countRound() {
        confirmReady.setOnClickListener(unused -> {
            if (currentRound < 4) {
                currentRound++;
                String round = Integer.toString(currentRound);
                ((TextView) findViewById(R.id.roundNumber)).setText(round);
                Toast.makeText(GameActivity.this,
                        "READY!",
                        Toast.LENGTH_SHORT).show();
                nextdialog.cancel();
                nextRound();

            } else {
                Toast.makeText(GameActivity.this,
                        "Game Finished!",
                        Toast.LENGTH_SHORT).show();
                nextdialog.cancel();
                amountEnd = playerChipAmount;
                amountChange = amountEnd - amountStart;

                newGame();
            }
        });
        cancelReady.setOnClickListener(unused -> {
            Toast.makeText(GameActivity.this,
                    "NOT READY",
                    Toast.LENGTH_SHORT).show();
            nextdialog.cancel();
        });

    }

    private void nextRound() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(GameActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_bet, null);
        editBet = mView.findViewById(R.id.editBet);
        betTextView = mView.findViewById(R.id.betTextView);
        betAgree = mView.findViewById(R.id.betAgree);
        fold = mView.findViewById(R.id.fold);
        check = mView.findViewById(R.id.check);
        call = mView.findViewById(R.id.call);
        allin = mView.findViewById(R.id.allin);

        betAgree.setOnClickListener(unused -> betAmount());
        fold.setOnClickListener(unused -> foldChoice());
        check.setOnClickListener(unused -> checkChoice());
        call.setOnClickListener(unused -> callChoice());
        allin.setOnClickListener(unused -> allinChoice());



        mBuilder.setView(mView);
        AlertDialog k = mBuilder.create();
        betdialog = k;
        betdialog.show();
    }

    private void betAmount() {
        String typein = editBet.getText().toString();
        if (isNumeric(typein)) {
            int betamount = Integer.parseInt(typein);
            playerChipAmount -= betamount;
            String bet = Integer.toString(playerChipAmount);
            ((TextView) findViewById(R.id.playerAmount)).setText(bet);

            Toast.makeText(GameActivity.this,
                    "Bet Successful",
                    Toast.LENGTH_SHORT).show();

            betdialog.cancel();


        } else {
            Toast.makeText(GameActivity.this,
                    "Only Type In Number",
                    Toast.LENGTH_SHORT).show();
        }

    }

    private void foldChoice() {
        betdialog.cancel();
        newGame();
    }

    private void checkChoice() {
        String bet = Integer.toString(playerChipAmount);
        ((TextView) findViewById(R.id.playerAmount)).setText(bet);

    }

    private void callChoice() {
        String bet = Integer.toString(playerChipAmount);
        ((TextView) findViewById(R.id.playerAmount)).setText(bet);
    }

    private void allinChoice() {
        if (playerChipAmount > 0) {
            betAmount = playerChipAmount;
            playerChipAmount = 0;
            String bet = Integer.toString(playerChipAmount);
            ((TextView) findViewById(R.id.playerAmount)).setText(bet);
            betdialog.cancel();
        } else {
            Toast.makeText(GameActivity.this,
                    "Not Enough Chips",
                    Toast.LENGTH_SHORT).show();
            betdialog.cancel();
        }
    }



    private void newGame() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(GameActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_continuenewgame, null);
        gameFinished = mView.findViewById(R.id.gameFinished);
        newGame = mView.findViewById(R.id.newGame);
        newGameExit = mView.findViewById(R.id.newGameExit);

        newGame.setOnClickListener(unused -> continueNewGame());
        newGameExit.setOnClickListener(unused -> exitGame());

        mBuilder.setView(mView);
        AlertDialog k = mBuilder.create();
        newgamedialog = k;
        newgamedialog.show();
    }

    private void continueNewGame() {
        gamenumber++;
        Intent intent = new Intent(this, StartGame.class);
        startActivity(intent);
        finish();
    }



}
