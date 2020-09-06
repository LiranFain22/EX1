package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Result_Activity extends AppCompatActivity {

    private TextView RESULT_LBL_winnerName;
    private TextView RESULT_LBL_numberOfMoves;

    private Button RESULT_BTN_menu;
    private Button RESULT_BTN_top10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        findViews();

        Intent i = getIntent();

        RESULT_LBL_winnerName.setText("" + i.getExtras().getString("winner"));
        RESULT_LBL_numberOfMoves.setText("" + i.getExtras().getInt("movesCounter"));

        // TODO: 06/09/2020 conneting "menu" button
    }



    private void findViews() {
        //------------------- Results -------------------------//
        RESULT_LBL_winnerName = (TextView) findViewById(R.id.RESULT_LBL_playerName);
        RESULT_LBL_numberOfMoves = (TextView) findViewById(R.id.RESULT_LBL_numberOfMoves);

        //------------------- Buttons -------------------------//
        RESULT_BTN_menu = findViewById(R.id.RESULT_BTN_menu);
        RESULT_BTN_top10 = findViewById(R.id.RESULT_BTN_Top10);
    }
}