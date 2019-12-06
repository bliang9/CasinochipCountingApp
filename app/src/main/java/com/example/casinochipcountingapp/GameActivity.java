package com.example.casinochipcountingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    /*
    * The total amount of player chips.
     */
    private int playerChipAmount;
    private TextView playerAmount;
    private Button add;
    private Button minus;
    private TextView addtextView;
    private TextView minustextView;
    private EditText addAmount;
    private EditText minusAmount;
    private Button addAgree;
    private Button minusAgree;
    private AlertDialog adddialog;
    private AlertDialog minusdialog;
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        add = findViewById(R.id.add);
        minus = findViewById(R.id.minus);
        playerAmount = findViewById(R.id.playerAmount);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(GameActivity.this);
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

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(GameActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_minus, null);
                minusAmount = mView.findViewById(R.id.minusAmount);
                minusAgree = mView.findViewById(R.id.minusAgree);
                minustextView = mView.findViewById(R.id.minustextView);


                minusAgree.setOnClickListener(unused -> minusChips());
                mBuilder.setView(mView);
                AlertDialog k = mBuilder.create();
                minusdialog = k;
                minusdialog.show();
                minusAgree.setOnClickListener(unused -> minusChips());
            }

        });



    }
    /**
     * Called when user is adding chips.
*/
    private void addChips() {
        String typein = addAmount.getText().toString();
        if (isNumeric(typein)) {
            int addamount = Integer.parseInt(typein);
            if (addamount % 5 !=0) {
                Toast.makeText(GameActivity.this,
                        "Number has to be the Multiples of 5",
                        Toast.LENGTH_SHORT).show();
            } else {
                playerChipAmount += addamount;
                String amount = Integer.toString(playerChipAmount);
                ((TextView) findViewById(R.id.playerAmount)).setText(amount);
                Toast.makeText(GameActivity.this,
                        "Add Successful",
                        Toast.LENGTH_SHORT).show();

                adddialog.cancel();
            }

        } else {
            Toast.makeText(GameActivity.this,
                    "Only Type In Number",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void minusChips() {
        String typein = minusAmount.getText().toString();
        if (isNumeric(typein)) {
            int minusamount = Integer.parseInt(typein);
            if (minusamount % 5 !=0) {
                Toast.makeText(GameActivity.this,
                        "Number has to be the Multiples of 5",
                        Toast.LENGTH_SHORT).show();
            } else {
                playerChipAmount -= minusamount;
                String amount = Integer.toString(playerChipAmount);
                ((TextView) findViewById(R.id.playerAmount)).setText(amount);
                Toast.makeText(GameActivity.this,
                        "Minus Successful",
                        Toast.LENGTH_SHORT).show();

                minusdialog.cancel();
            }

        } else {
            Toast.makeText(GameActivity.this,
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
