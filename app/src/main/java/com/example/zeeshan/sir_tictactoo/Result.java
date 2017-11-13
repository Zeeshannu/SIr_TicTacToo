package com.example.zeeshan.sir_tictactoo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends AppCompatActivity {


    public static   int player_1_Score=5;
    public static   int player_2_Score=2;

    private TextView mTVPlayer1;
    private TextView mTVPlayer2;


    Intent intent=getIntent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();


        //mTVPlayer1.setText(bundle.getString("player_1_Score"));


    mTVPlayer1.setText(String.valueOf(player_1_Score));
        //mTVPlayer2.setText(player_2_Score);
        //mTVPlayer2.setText(bundle.get("player_2_Score").toString());



    }

    @Override
    protected void onPause() {
        super.onPause();
    }












}
