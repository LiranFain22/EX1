package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar main_PB_spiderman;
    private ProgressBar main_PB_ninja;

    private Button main_BTN_spidermanAtt1;
    private Button main_BTN_spidermanAtt2;
    private Button main_BTN_spidermanAtt3;

    private Button main_BTN_ninjaAtt1;
    private Button main_BTN_ninjaAtt2;
    private Button main_BTN_ninjaAtt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();

        /* TODO
        *   1. Fix the Disable button problem
        *   2. correct the position of the buttons of players
        */


        main_PB_spiderman.setProgress(100);
        main_PB_ninja.setProgress(100);

        main_BTN_ninjaAtt1.setEnabled(false);
        main_BTN_ninjaAtt2.setEnabled(false);
        main_BTN_ninjaAtt3.setEnabled(false);

        main_BTN_spidermanAtt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"SpiderMan PUNCH!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findView() {
        //------------------- ProgressBar for SpiderMan & Ninja -------------------------//
        main_PB_spiderman = findViewById(R.id.spiderMan_progressBar);
        main_PB_ninja = findViewById(R.id.ninja_progressBar);

        //------------------- Buttons for SpiderMan -------------------------//
        main_BTN_spidermanAtt1 = findViewById(R.id.MainActivity_Btn_Att1_spiderMan);
        main_BTN_spidermanAtt2 = findViewById(R.id.MainActivity_Btn_Att2_spiderMan);
        main_BTN_spidermanAtt3 = findViewById(R.id.MainActivity_Btn_Att3_spiderMan);

        //------------------- Buttons for Ninja -------------------------//
        main_BTN_ninjaAtt1 = findViewById(R.id.MainActivity_Btn_Att1_Ninja);
        main_BTN_ninjaAtt2 = findViewById(R.id.MainActivity_Btn_Att2_Ninja);
        main_BTN_ninjaAtt3 = findViewById(R.id.MainActivity_Btn_Att3_Ninja);
    }
}