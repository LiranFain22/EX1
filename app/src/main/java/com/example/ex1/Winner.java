package com.example.ex1;

import android.location.Location;

public class Winner implements Comparable<Winner>{
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

    public String getName() {
        return name;
    }

    public int getCounterMoves() {
        return counterMoves;
    }

    public Location getLocation() {
        return location;
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
