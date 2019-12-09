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
        View viewmyrecord = getLayoutInflater().inflate(R.layout.chunk_gamehistory, myGameHistory, false);
        myRecordScroll = viewmyrecord.findViewById(R.id.myRecordScroll);
        for (int i = 0; i < historyList.size(); i++ ) {
            TextView textView = new TextView(GameRecordList.this);
            textView.setText(historyList.get(i));

        }
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
