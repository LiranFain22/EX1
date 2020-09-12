package com.example.ex1;

import android.location.Location;

public class Winner {
    private String name;
    private int counterMoves;
    private Location location;

    public  Winner() {

    };

    public Winner(String name, int counterMoves, Location location) {
        this.name = name;
        this.counterMoves = counterMoves;
        this.location = location;
    }
}
