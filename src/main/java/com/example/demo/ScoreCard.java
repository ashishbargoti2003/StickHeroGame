package com.example.demo;

public class ScoreCard {

    private int bestScore;
    private int currentScore;

    public ScoreCard(int currentScore) {

        this.currentScore = currentScore;

    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    @Override
    public String toString() {
        return "ScoreCard{" +
                "bestScore=" + bestScore +
                ", currentScore=" + currentScore +
                '}';
    }

    public void resetScore(){

    }

}
