package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Top10 extends AppCompatActivity {

    private Fragment_List fragment_list;
    private Fragment_Map fragment_map;
    private ArrayList<Winner> winnerArrayList;

    private Button top10_BTN_main;

    private View.OnClickListener buttonClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10);

        initFragments();
        findViews();
        setListener();
        initWinners();


        top10_BTN_main.setOnClickListener(buttonClickListener);
    }

    private void initWinners() {
        SharedPreferences appSharedPrefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        Gson gson = new Gson();
        Type type = new TypeToken<List<Winner>>(){}.getType();
        //get the arrayList of winners
        String json = appSharedPrefs.getString("MyWinner", "");
        List<Winner> winnerArrayList = gson.fromJson(json, type);
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
            }
        };
    }

    private void findViews() {
        top10_BTN_main = findViewById(R.id.top10_BTN_main);
    }

    private void initFragments() {
        fragment_list = Fragment_List.newInstance();
        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        transaction1.replace(R.id.top10_LAY_list,fragment_list);
        transaction1.commit();

        fragment_map = Fragment_Map.newInstance();
        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        transaction2.replace(R.id.top10_LAY_map,fragment_map);
        transaction2.commit();
    }
}