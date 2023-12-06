package com.example.demo;

public class Pillar {
    private double  width;

    private double distance;

    public Pillar(double width, double distance) {
        this.width = width;
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void increaseWidth(double n){

    }

    public Pillar(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Pillar{" +
                "width=" + width +
                '}';
    }
}
