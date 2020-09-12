package com.example.ex1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_List extends Fragment {

    protected View view;

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

        findView(view);

        return view;
    }

    private void findView(View view) {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("pttt", "onSaveInstanceState");

        super.onSaveInstanceState(outState);
    }
}
