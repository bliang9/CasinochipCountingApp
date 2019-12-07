package com.example.casinochipcountingapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class GameRecordList extends GameActivity {

    private Button back;
    private ScrollView myRecordScroll;
    public static LinearLayout myGameHistory;
    private TextView historyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recordlist);
        myGameHistory = findViewById(R.id.myGameHistory);
        View viewmyrecord = getLayoutInflater().inflate(R.layout.chunk_gamehistory, myGameHistory, false);
        myRecordScroll = viewmyrecord.findViewById(R.id.myRecordScroll);
        String addTo = "GAME " + gamenumber + "       " + amountChange ;
        historyText = viewmyrecord.findViewById(R.id.history);
        historyText.setText(addTo);

        myGameHistory.addView(viewmyrecord);
        /*
         */



        back = findViewById(R.id.back);
        back.setOnClickListener(unused -> {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
            finish();
        });

    }
}
