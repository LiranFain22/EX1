package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Start_Activity extends AppCompatActivity {
    //get access to location permission
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    //-----------------For Version 2----------------//

    private Button startActivity_BTN_startGame;
    private Button startActivity_BTN_top10;

    private View.OnClickListener buttonClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        findView();

        setListener();

        setButtons();

        locationPermission();
    }

    private void locationPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                                android.Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // Permission Denied
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
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
                if(view.getTag().equals("top10")){
                    Intent i = new Intent(getApplicationContext(),Top10.class);
                    startActivity(i);
                    finish();
                }
            }
        };
    }

    private void setButtons() {
        startActivity_BTN_startGame.setOnClickListener(buttonClickListener);
        startActivity_BTN_top10.setOnClickListener(buttonClickListener);
    }

    private void findView() {
        //------------------- Buttons -------------------------//
        startActivity_BTN_startGame = findViewById(R.id.StartActivity_BTN_startGame);
        startActivity_BTN_top10 = findViewById(R.id.StartActivity_BTN_top10);
    }
}