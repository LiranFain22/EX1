package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
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

    private int spiderManHealth; // spiderMan health point
    private int ninjaHealth; // ninja health point

    private static final int SMALL_ATTACK = 10;
    private static final int MEDIUM_ATTACK = 20;
    private static final int LARGE_ATTACK = 30;

    private View.OnClickListener buttonClickListener;
    /*
        if turn is even - ninja turn
        else - spiderMan turn
     */
    private int turn = 1;

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_main);

                    findView();

                    startGame();
                    
                    setListener();
                    
                    setButtons();
                    
                    
    }

    /**
     * Checking if Health of SpiderMan and Ninja is under 40
     * if true, making the progress bar color RED
     * otherwise, keeping the progress bar color GREEN
     */
    private void updateProgressBar() {
                    if(turn % 2 != 0 && ninjaHealth <= 40){ // SpiderMan Turn
                        main_PB_ninja.getProgressDrawable().setColorFilter(Color.RED , PorterDuff.Mode.MULTIPLY);
                    }
                    else if(turn % 2 == 0 && spiderManHealth <= 40){ // Ninja turn
                        main_PB_spiderman.getProgressDrawable().setColorFilter(Color.RED , PorterDuff.Mode.MULTIPLY);
                    }
    }

    private void setButtons() {
                    main_BTN_spidermanAtt1.setOnClickListener(buttonClickListener);
                    main_BTN_spidermanAtt2.setOnClickListener(buttonClickListener);
                    main_BTN_spidermanAtt3.setOnClickListener(buttonClickListener);
                    main_BTN_ninjaAtt1.setOnClickListener(buttonClickListener);
                    main_BTN_ninjaAtt2.setOnClickListener(buttonClickListener);
                    main_BTN_ninjaAtt3.setOnClickListener(buttonClickListener);
    }

    private void setListener() {
        buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = (String) view.getTag();

                if(tag.equals("SpiderMan_punch")) {
                    damageAndMessage(SMALL_ATTACK,"SpiderMan Won!",main_PB_ninja);
                }

                if(tag.equals("SpiderMan_kick")) {
                    damageAndMessage(MEDIUM_ATTACK, "SpiderMan Won!",main_PB_ninja);
                }

                if(tag.equals("SpiderMan_web")) {
                    damageAndMessage(LARGE_ATTACK, "SpiderMan Won!",main_PB_ninja);
                }

                if(tag.equals("Ninja_punch")) {
                    damageAndMessage(SMALL_ATTACK, "Ninja Won!",main_PB_spiderman);
                }

                if(tag.equals("Ninja_Kick")){
                    damageAndMessage(MEDIUM_ATTACK, "Ninja Won!",main_PB_spiderman);
                }

                if(tag.equals("Ninja_star")){
                    damageAndMessage(LARGE_ATTACK, "Ninja Won!",main_PB_spiderman);
                }
                updateProgressBar();
            }
        };
    }

    private void damageAndMessage(int attack, String message, ProgressBar progressBar) {
                    if(turn % 2 != 0){ // SpiderMan attack
                        if((ninjaHealth - attack) > 0){
                            ninjaHealth -= attack;
                            progressBar.setProgress(ninjaHealth);
                            turns();
                            return;
                        }
                    }else{
                        if((spiderManHealth - attack) > 0){
                            spiderManHealth -= attack;
                            progressBar.setProgress(spiderManHealth);
                            turns();
                            return;
                        }
                    }
                    gameOver(message,progressBar);
    }

    /**
     * The method disable all buttons and show message of the winning player
     */
    private void gameOver(String message, ProgressBar progressBar) {

        main_BTN_spidermanAtt1.setEnabled(false);
        main_BTN_spidermanAtt2.setEnabled(false);
        main_BTN_spidermanAtt3.setEnabled(false);

        main_BTN_ninjaAtt1.setEnabled(false);
        main_BTN_ninjaAtt2.setEnabled(false);
        main_BTN_ninjaAtt3.setEnabled(false);

        progressBar.setProgress(0);
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void startGame() {

        spiderManHealth = 100;
        ninjaHealth = 100;

        main_PB_spiderman.setProgress(spiderManHealth);
        main_PB_ninja.setProgress(ninjaHealth);

        main_PB_ninja.getProgressDrawable().setColorFilter(Color.GREEN , PorterDuff.Mode.MULTIPLY);
        main_PB_spiderman.getProgressDrawable().setColorFilter(Color.GREEN , PorterDuff.Mode.MULTIPLY);


        turns();


    }

    /**
     * This method enable the buttons for the player,
     * depends on the turn
     */
    private void turns() {
            if(turn % 2 != 0){ // spiderMan turn
                main_BTN_spidermanAtt1.setEnabled(true);
                main_BTN_spidermanAtt2.setEnabled(true);
                main_BTN_spidermanAtt3.setEnabled(true);
                main_BTN_ninjaAtt1.setEnabled(false);
                main_BTN_ninjaAtt2.setEnabled(false);
                main_BTN_ninjaAtt3.setEnabled(false);
            }else{           // ninja turn
                main_BTN_ninjaAtt1.setEnabled(true);
                main_BTN_ninjaAtt2.setEnabled(true);
                main_BTN_ninjaAtt3.setEnabled(true);
                main_BTN_spidermanAtt1.setEnabled(false);
                main_BTN_spidermanAtt2.setEnabled(false);
                main_BTN_spidermanAtt3.setEnabled(false);
            }
            turn++;
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