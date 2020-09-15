package com.example.ex1;

import android.location.Location;

public class Winner implements Comparable<Winner>{
    private String name;
    private int counterMoves;
    private double locationLat;
    private double locationLng;

    public  Winner() { };

    public Winner(String name, int counterMoves, Location location) {
        this.name = name;
        this.counterMoves = counterMoves;
        this.locationLat = location.getLatitude();
        this.locationLng = location.getLongitude();
    }

    public String getName() {
        return name;
    }

    public int getCounterMoves() {
        return counterMoves;
    }

    public double getLocationLat() {
        return locationLat;
    }

    public double getLocationLng() {
        return locationLng;
    }

    @Override
    public int compareTo(Winner winner) {
        if(counterMoves == winner.getCounterMoves())
            return 1;
        else if(counterMoves > winner.counterMoves)
            return 1;
        return  -1;
    }
}
