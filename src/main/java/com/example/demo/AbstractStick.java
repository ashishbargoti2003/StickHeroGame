package com.example.demo;

abstract public class AbstractStick {
    private int length;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    abstract public void extend();

}
