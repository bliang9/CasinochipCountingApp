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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recordlist);
        myGameHistory = findViewById(R.id.myGameHistory);
        int count = 0;
        for (String a : historyList) {
            historyList.set(count, "Game " + (count + 1) + "   " + amountChange);
            View messageChunk = getLayoutInflater().inflate(R.layout.chunk_gamehistory, myGameHistory, false);
            TextView senderLabel = messageChunk.findViewById(R.id.history);
            senderLabel.setText(historyList.get(gamenumber - 1));
            // Do something with any other views in the chunk...
            myGameHistory.addView(messageChunk);
            count++;
        }

        myGamePlayer = findViewById(R.id.myGamePlayer);
        int countb = 0;
        for (String a : playerName) {
            View messageChunk = getLayoutInflater().inflate(R.layout.chunk_playerlist, myGamePlayer, false);
            TextView senderLabel = messageChunk.findViewById(R.id.playersInGame);
            senderLabel.setText(playerName.get(countb));
            // Do something with any other views in the chunk...
            myGamePlayer.addView(messageChunk);
            countb++;
        }

        /**View viewmyrecord = getLayoutInflater().inflate(R.layout.chunk_gamehistory, myGameHistory, false);

        myRecordScroll = viewmyrecord.findViewById(R.id.myRecordScroll);
        TextView history = viewmyrecord.findViewById(R.id.history);
        //TextView textView = new TextView(GameRecordList.this);


        for (int i = 0; i < historyList.size(); i++ ) {

        }
        myGameHistory.addView(viewmyrecord);
         */


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
