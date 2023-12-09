package com.example.demo;
import java.io.Serializable;
import java.util.*;


public class Player implements Serializable {
    private String name;
    private int HighestScore;
    private int score;
    private int cherriesCollected;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHighestScore() {
        return HighestScore;
    }

    public void setHighestScore(int highestScore) {
        HighestScore = highestScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        if(this.score>this.getHighestScore()){
            this.setHighestScore(this.getScore());
        }
    }

    public int getCherriesCollected() {
        return cherriesCollected;
    }

    public void setCherriesCollected(int cherriesCollected) {
        this.cherriesCollected = cherriesCollected;
    }
}
