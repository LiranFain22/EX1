package com.example.ex1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Fragment_List extends Fragment {

    protected View view;

    private RecyclerView list_RECLER_recyclerView;
    private WinnerAdapter rAdapter;
    private RecyclerView.LayoutManager rLayoutManager;
    private ArrayList<Winner> winnerArrayList;


    public static Fragment_List newInstance(){
        Log.d("pttt", "newInstance");
        Fragment_List fragment = new Fragment_List();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("pttt", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView - Fragment_Step");

        if(view==null){
            view = inflater.inflate(R.layout.fragment_list, container, false);
        }

        initWinners();

        findView(view,winnerArrayList);

        setOnClickListener();

        return view;
    }

    private void setOnClickListener() {
        rAdapter.setOnWinnerClickListener(new WinnerAdapter.OnWinnerClickListener() {
            @Override
            public void onWinnerClick(int position) {
                // TODO: 13/09/2020 pass location from list fragment to map fragment
                Bundle result = new Bundle();
                Gson gson = new Gson();
                result.putDouble("locationLat",winnerArrayList.get(position).getLocationLat());
                result.putDouble("locationLng",winnerArrayList.get(position).getLocationLng());
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.setFragmentResult("location",result);
            }
        });
    }

    private void initWinners() {
        SharedPreferences appSharedPrefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(getContext());
        Gson gson = new Gson();
        Type type = new TypeToken<List<Winner>>(){}.getType();
        //get the arrayList of winners
        String json = appSharedPrefs.getString("MyWinner", "");
        winnerArrayList = gson.fromJson(json, type);
        winnerArrayList = sortForTop10(winnerArrayList);
    }

    private ArrayList<Winner> sortForTop10(ArrayList<Winner> winnerArrayList) {
        Collections.sort(winnerArrayList);
        if(winnerArrayList.size()>10)
            return new ArrayList<Winner>(winnerArrayList.subList(0,10));
        return winnerArrayList;
    }

    private void findView(View view, ArrayList<Winner> winnerArrayList) {
        list_RECLER_recyclerView = view.findViewById(R.id.list_RECLER_recyclerView);
        list_RECLER_recyclerView.setHasFixedSize(true);
        rLayoutManager = new LinearLayoutManager(getContext());
        rAdapter = new WinnerAdapter(winnerArrayList);

        list_RECLER_recyclerView.setLayoutManager(rLayoutManager);
        list_RECLER_recyclerView.setAdapter(rAdapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("pttt", "onSaveInstanceState");

        super.onSaveInstanceState(outState);
    }
}
