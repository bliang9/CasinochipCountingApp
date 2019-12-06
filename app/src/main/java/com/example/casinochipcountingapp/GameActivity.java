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
    private Button add;
    private TextView addtextView;
    private EditText addAmount;
    private Button addAgree;
    private AlertDialog dialog;
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        add = findViewById(R.id.add);

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
                dialog = k;
                dialog.show();
                addAgree.setOnClickListener(unused -> addChips());
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
            playerChipAmount += addamount;
            Toast.makeText(GameActivity.this,
                    "Add Successful",
                    Toast.LENGTH_SHORT).show();

            dialog.cancel();

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
