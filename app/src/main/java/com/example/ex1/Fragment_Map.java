package com.example.ex1;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Map extends Fragment {
    protected View view;
    private TextView map_LBL_info;

    public static Fragment_Map newInstance(){
        Log.d("pttt", "newInstance");
        Fragment_Map fragment = new Fragment_Map();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("pttt", "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView - Fragment_Step");

        if(view==null){
            view = inflater.inflate(R.layout.fragment_map, container, false);
        }

        findView(view);



        return view;
    }

    private void findView(View view) {
        map_LBL_info = view.findViewById(R.id.map_LBL_info);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("pttt", "onSaveInstanceState");

        super.onSaveInstanceState(outState);
    }

}
