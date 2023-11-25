package com.example.demo;

public class StickHero implements Character {
    private int noOfLives;
    private int score;
    private int cherries;

    public int getNoOfLives() {
        return noOfLives;
    }

    public void setNoOfLives(int noOfLives) {
        this.noOfLives = noOfLives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCherries() {
        return cherries;
    }

    public void setCherries(int cherries) {
        this.cherries = cherries;
    }
    @Override

    public void revive(int cherries){

    }
    @Override
    public void deductCherries(){

    }


}
