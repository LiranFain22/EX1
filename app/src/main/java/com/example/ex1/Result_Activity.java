package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Result_Activity extends AppCompatActivity {

    private TextView RESULT_LBL_winnerName;
    private TextView RESULT_LBL_numberOfMoves;

    private Button RESULT_BTN_menu;
    private Button RESULT_BTN_top10;

    private View.OnClickListener buttonClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        findViews();

        Intent i = getIntent();

        RESULT_LBL_winnerName.setText("" + i.getExtras().getString("winner"));
        RESULT_LBL_numberOfMoves.setText("" + i.getExtras().getInt("movesCounter"));

        putInListWinners(i);

        setListener();

        setButtons();

    }

    // TODO: 12/09/2020 - continue to write 'putInListWinners' Method
    private void putInListWinners(Intent i){
       // Winner winner = new Winner(i.getExtras().getString("winner"),i.getExtras().getInt("movesCounter"),)
    }

    private void setButtons() {
        RESULT_BTN_menu.setOnClickListener(buttonClickListener);
        RESULT_BTN_top10.setOnClickListener(buttonClickListener);
    }

    private void setListener() {
        buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = (String) view.getTag();

                if(tag.equals("menu")){
                    Intent i = new Intent(getApplicationContext(),Start_Activity.class);
                    startActivity(i);
                    finish();
                }
                if(view.getTag().equals("top10")){
                    Intent i = new Intent(getApplicationContext(),Top10.class);
                    startActivity(i);
                    finish();
                }
            }
        };
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