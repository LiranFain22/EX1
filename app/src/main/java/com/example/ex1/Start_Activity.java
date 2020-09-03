package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Start_Activity extends AppCompatActivity {

    //-----------------For Version 2----------------//

    private Button startActivity_BTN_startGame;
    private Button startActivity_BTN_records;

    private View.OnClickListener buttonClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        findView();

        setListener();

        setButtons();
    }

    private void setListener() {
        buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getTag().equals("startGame")){
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                }
                if(view.getTag().equals("records")){
                    Toast.makeText(Start_Activity.this,"Records Button clicked",Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private void setButtons() {
        startActivity_BTN_startGame.setOnClickListener(buttonClickListener);
        startActivity_BTN_records.setOnClickListener(buttonClickListener);
    }

    private void findView() {
        //------------------- Buttons -------------------------//
        startActivity_BTN_startGame = findViewById(R.id.StartActivity_BTN_startGame);
        startActivity_BTN_records = findViewById(R.id.StartActivity_BTN_records);
    }
}