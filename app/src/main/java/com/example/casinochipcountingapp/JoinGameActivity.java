package com.example.casinochipcountingapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.gson.JsonObject;

//

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JoinGameActivity extends LoginActivity {
    private ProgressDialog progressDialog;
    private FirebaseDatabase firebaseDatabase;
    private List<DatabaseReference> keys;
    private Button CreateGame;

    private TextView createRoom;
    private EditText createRoomEdit;
    private Button createRoomAgree;
    private AlertDialog createRoomdialog;
    public String roomID;
    public StorageReference myRoomIDRef;
    private TextView gameID;
    private EditText typeGameID;
    private Button joinGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joingame);
        CreateGame = findViewById(R.id.createGame);
        CreateGame.setOnClickListener(unused -> createGame());
        /*firebaseDatabase = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(JoinGameActivity.this);

        gameID.setTextColor(Color.GRAY);
        TextView typeGameId = findViewById(R.id.typeGameID);
        typeGameId.setTextColor(Color.GREEN);
        Button joinGame = findViewById(R.id.joinGame);
        joinGame.setOnClickListener(unused -> joinTheGame(typeGameId.getText().toString()));
        Button createGame = findViewById(R.id.createGame);
        createGame.setOnClickListener(unused -> createGame());
        keys = new ArrayList<>();

         */
        gameID = findViewById(R.id.gameId);
        typeGameID = findViewById(R.id.typeGameID);
        typeGameID.setTextColor(Color.WHITE);
        joinGame = findViewById(R.id.joinGame);
        joinGame.setOnClickListener(unused -> joinTheGame(typeGameID.getText().toString()));

    }
    public void joinTheGame(String gameId) {
        //FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        //DatabaseReference gameToStart = firebaseDatabase.getReference("gamesToStart");


        if (!gameId.equals("")) {
            progressDialog.setMessage("Joining Game");
            progressDialog.show();
            StorageReference listRef = storage.getReference().child("allRoomID/uid");
            listRef.listAll()
                    .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                        @Override
                        public void onSuccess(ListResult listResult) {
                            for (StorageReference prefix : listResult.getPrefixes()) {
                                // All the prefixes under listRef.
                                // You may call listAll() recursively on them.
                                if (gameId.equals(prefix.getName())) {
                                    emialListRef =  myRoomIDRef.child(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                                    start();
                                }
                            }
                            progressDialog.dismiss();
                            // throw an message here says"Game id doesn't exist, please create or try a different one.
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    });
        } else {
            TextView errorMessage = findViewById(R.id.errorMessage);
            errorMessage.setText("Type In Game ID");
            errorMessage.setTextColor(Color.RED);
            errorMessage.setTextSize(20);
        }
    }
    public void createGame() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(JoinGameActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_createroom, null);
        createRoom = mView.findViewById(R.id.createRoom);
        createRoomEdit = mView.findViewById(R.id.createRoomEdit);
        createRoomAgree = mView.findViewById(R.id.createRoomAgree);
//retrieve https://firebase.google.com/docs/storage/android/create-reference?authuser=0;
        createRoomAgree.setOnClickListener(unused -> {
            roomID = createRoomEdit.getText().toString();
            StorageReference listRef = storage.getReference().child("allRoomID/uid");
            listRef.listAll()
                    .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                        @Override
                        public void onSuccess(ListResult listResult) {
                            for (StorageReference prefix : listResult.getPrefixes()) {
                                // All the prefixes under listRef.
                                // You may call listAll() recursively on them.
                                if (roomID.equals(prefix.getName())) {
                                    //"Room id has already existed. Type in New One.
                                }
                            }
                            myRoomIDRef = allRoomIDRef.child(roomID);
                            emialListRef =  myRoomIDRef.child(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                            start();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    });
        });
        mBuilder.setView(mView);
        AlertDialog k = mBuilder.create();
        createRoomdialog = k;
        createRoomdialog.show();





        /*progressDialog.setMessage("Creating game");
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

         */
    }


    public void start() {
        Intent intent = new Intent(this, StartGame.class);
        startActivity(intent);
        finish();
    }


}
