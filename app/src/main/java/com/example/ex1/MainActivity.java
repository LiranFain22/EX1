package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //-----------------For Version 1----------------//
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

    //-----------------For Version 2----------------//

    public static final Random RANDOM = new Random();

    private ImageView main_IMG_arrow;

    private LottieAnimationView main_ANIMATION_flipCoin;

    private Button main_BTN_start;
    private Button main_BTN_result;

    private TextView main_TXT_SpiderManText;
    private TextView main_TXT_NinjaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_main);

                    findView();

                    initializeGame();
                    
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

                    main_BTN_start.setOnClickListener(buttonClickListener);
                    main_BTN_result.setOnClickListener(buttonClickListener);
    }

    private void setListener() {
        buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = (String) view.getTag();

                if(tag.equals("flip !"))
                    flipCoin();
                if(tag.equals("result")){
                    Intent i = new Intent(getApplicationContext(),Result_Activity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
    }

    private void startGame() {
        turn = RANDOM.nextInt();
        makeTurn();
    }

    /**
     * This method playing the "flip coin" Animation
     */
    private void flipCoin() {
        main_BTN_start.setVisibility(View.INVISIBLE);
        main_ANIMATION_flipCoin.setVisibility(View.VISIBLE);
        main_ANIMATION_flipCoin.playAnimation();
        main_ANIMATION_flipCoin.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                //main_ANIMATION_flipCoin.setVisibility(View.VISIBLE);
                Log.d("pttt","Animation START");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                main_ANIMATION_flipCoin.setVisibility(View.GONE);
                Log.d("pttt","Animation GONE");
                startGame();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    private void damageAndMessage(int attack, String message, ProgressBar progressBar) {
                    if(turn % 2 != 0){ // SpiderMan attack
                        if((ninjaHealth - attack) > 0){
                            ninjaHealth -= attack;
                            progressBar.setProgress(ninjaHealth);
                            turn++;
                            makeTurn();
                            return;
                        }
                    }else{
                        if((spiderManHealth - attack) > 0){
                            spiderManHealth -= attack;
                            progressBar.setProgress(spiderManHealth);
                            turn++;
                            makeTurn();
                            return;
                        }
                    }
                    progressBar.setProgress(0);
                    main_IMG_arrow.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    gameOver();
                    main_BTN_result.setVisibility(View.VISIBLE);
    }

    /**
     * The method disable all buttons
     */
    private void gameOver() {

        main_BTN_spidermanAtt1.setEnabled(false);
        main_BTN_spidermanAtt2.setEnabled(false);
        main_BTN_spidermanAtt3.setEnabled(false);

        main_BTN_ninjaAtt1.setEnabled(false);
        main_BTN_ninjaAtt2.setEnabled(false);
        main_BTN_ninjaAtt3.setEnabled(false);

        main_TXT_SpiderManText.setText("");
        main_TXT_NinjaText.setText("");
    }

    private void initializeGame() {

        spiderManHealth = 100;
        ninjaHealth = 100;

        main_PB_spiderman.setProgress(spiderManHealth);
        main_PB_ninja.setProgress(ninjaHealth);

        main_PB_ninja.getProgressDrawable().setColorFilter(Color.GREEN , PorterDuff.Mode.MULTIPLY);
        main_PB_spiderman.getProgressDrawable().setColorFilter(Color.GREEN , PorterDuff.Mode.MULTIPLY);

        main_BTN_result.setVisibility(View.INVISIBLE);

        // Calling to this method, before clicking on "start" button
        gameOver();

        main_ANIMATION_flipCoin.setVisibility(View.INVISIBLE);
    }

    /**
     * This method enable the buttons for the player,
     * depends on the turn
     */
    private void makeTurn() {
            if(turn % 2 != 0){ // spiderMan turn
                main_IMG_arrow.setImageResource(R.drawable.left_arrow);

                main_BTN_spidermanAtt1.setEnabled(true);
                main_BTN_spidermanAtt2.setEnabled(true);
                main_BTN_spidermanAtt3.setEnabled(true);
                main_BTN_ninjaAtt1.setEnabled(false);
                main_BTN_ninjaAtt2.setEnabled(false);
                main_BTN_ninjaAtt3.setEnabled(false);

                final Handler handler = new Handler();
                final int delay = 2000; //milliseconds
                handler.postDelayed(new Runnable(){
                    public void run(){
                        randomAttack();
                        //handler.postDelayed(this, delay);
                    }
                }, delay);
            }else{           // ninja turn
                main_IMG_arrow.setImageResource(R.drawable.right_arrow);

                main_BTN_spidermanAtt1.setEnabled(false);
                main_BTN_spidermanAtt2.setEnabled(false);
                main_BTN_spidermanAtt3.setEnabled(false);
                main_BTN_ninjaAtt1.setEnabled(true);
                main_BTN_ninjaAtt2.setEnabled(true);
                main_BTN_ninjaAtt3.setEnabled(true);

                final Handler handler = new Handler();
                final int delay = 2000; //milliseconds
                handler.postDelayed(new Runnable(){
                    public void run(){
                        randomAttack();
                        //handler.postDelayed(this, delay);
                    }
                }, delay);
            }
    }

    private void randomAttack() {
        int chooseAttack = RANDOM.nextInt(3);
        if(turn % 2 != 0){ // SpiderMan turn
            if(chooseAttack == 0) {
                damageAndMessage(SMALL_ATTACK, "SpiderMan Won!", main_PB_ninja);
                main_TXT_SpiderManText.setText("Punch!");
            }
            if(chooseAttack == 1) {
                damageAndMessage(MEDIUM_ATTACK, "SpiderMan Won!", main_PB_ninja);
                main_TXT_SpiderManText.setText("Kick!");
            }
            if(chooseAttack == 2) {
                damageAndMessage(LARGE_ATTACK, "SpiderMan Won!", main_PB_ninja);
                main_TXT_SpiderManText.setText("Web!");
            }
        }
        else{
            if(chooseAttack == 0) {
                damageAndMessage(SMALL_ATTACK, "Ninja Won!", main_PB_spiderman);
                main_TXT_NinjaText.setText("Punch!");
            }
            if(chooseAttack == 1) {
                damageAndMessage(MEDIUM_ATTACK, "Ninja Won!", main_PB_spiderman);
                main_TXT_NinjaText.setText("Kick!");
            }
            if(chooseAttack == 2) {
                damageAndMessage(LARGE_ATTACK, "Ninja Won!", main_PB_spiderman);
                main_TXT_NinjaText.setText("Star!");
            }
        }
        updateProgressBar();
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

        //------------------- Button for Start -------------------------//
        main_BTN_start = findViewById(R.id.MainActivity_Btn_start);

        //------------------- Arrow left and right -------------------------//
        main_IMG_arrow = (ImageView)findViewById(R.id.MainActivity_IMG_arrow);

        //------------------- Flip coin Animation -------------------------//
        main_ANIMATION_flipCoin = findViewById(R.id.MainActivity_ANIMATION_flipCoin);

        //------------------- Text for SpiderMan & Ninja Attack -------------------------//
        main_TXT_SpiderManText = (TextView) findViewById(R.id.MainActivity_TXT_SpiderManText);
        main_TXT_NinjaText = (TextView) findViewById(R.id.MainActivity_TXT_NinjaText);

        //------------------- Button for result -------------------------//
        main_BTN_result = findViewById(R.id.MainActivity_Btn_result);
    }
}