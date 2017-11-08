package com.example.zeeshan.sir_tictactoo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toast.makeText(this, "THis is second activity", Toast.LENGTH_SHORT).show();

    }
}
