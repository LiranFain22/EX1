package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Result_Activity extends AppCompatActivity {

    private TextView RESULT_LBL_winnerName;
    private TextView RESULT_LBL_numberOfMoves;
    private TextView RESULT_LBL_timeCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        findViews();

        Intent i = getIntent();

        RESULT_LBL_winnerName.setText("" + i.getExtras().getString("winner"));
        RESULT_LBL_numberOfMoves.setText("" + i.getExtras().getInt("movesCounter"));

        /*
        TODO -
            1. add two buttons: "menu" and "Top10"
            2.
         */
    }



    private void findViews() {
        //------------------- Results -------------------------//
        RESULT_LBL_winnerName = (TextView) findViewById(R.id.RESULT_LBL_playerName);
        RESULT_LBL_numberOfMoves = (TextView) findViewById(R.id.RESULT_LBL_numberOfMoves);
        RESULT_LBL_timeCounter = (TextView) findViewById(R.id.RESULT_LBL_timeCounter);
    }
}