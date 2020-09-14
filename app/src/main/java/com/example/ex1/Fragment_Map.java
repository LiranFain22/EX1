package com.example.ex1;


import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

public class Fragment_Map extends Fragment {
    protected View view;
    private MapView map_mapView;

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

    @Override
    public void onStart() {
        super.onStart();
        map_mapView.onStart();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView - Fragment_Step");

        if(view==null){
            view = inflater.inflate(R.layout.fragment_map, container, false);
        }

        findView(view);
        map_mapView.onCreate(savedInstanceState);

        getParentFragmentManager().setFragmentResultListener("location", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                final double locationLat = bundle.getDouble("locationLat",0);
                final double locationLng = bundle.getDouble("locationLng",0);



                map_mapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        LatLng nav = new LatLng(locationLat, locationLng);
                        googleMap.addMarker(new MarkerOptions().position(nav));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(nav));
                    }
                });

                Toast.makeText(getContext(),"lat: " + locationLat + " lng: " + locationLng,Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }

    private void findView(View view) {
        //map_LBL_info = view.findViewById(R.id.map_LBL_info);
        map_mapView = view.findViewById(R.id.map_mapView);
    }

    @Override
    public void onResume() {
        super.onResume();
        map_mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map_mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        map_mapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        map_mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("pttt", "onSaveInstanceState");

        super.onSaveInstanceState(outState);
        map_mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        map_mapView.onLowMemory();
    }
}
