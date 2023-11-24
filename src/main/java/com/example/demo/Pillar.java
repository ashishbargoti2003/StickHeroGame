package com.example.demo;

public class Pillar {
    private int  width;

    private int distance;

    public Pillar(int width, int distance) {
        this.width = width;
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void increaseWidth(int n){

    }

    public Pillar(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Pillar{" +
                "width=" + width +
                '}';
    }
}
