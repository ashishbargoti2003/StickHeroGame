package com.example.demo;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.assertEquals;

public class GameTesting {
    @Test
    public void updateScore(){
        ScoreCard score=new ScoreCard(10);
        score.setCurrentScore(20);
        int expected=20;
        assertEquals(expected,score.getBestScore());

    }
    @Test(expected = NullPointerException.class)
    public void setScore(){
        ScoreCard score=null;

        score.setCurrentScore(10);
        System.out.println("NullPointerException caught ssuccessfuly!!");

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void trySetScore(){
        ScoreCard score=null;

        score.setCurrentScore(10);


    }
    @Test
    public void FailedupdateScore(){
        ScoreCard score=new ScoreCard(10);
        score.setCurrentScore(20);
        int expected=10;
        assertEquals(expected,score.getBestScore());

    }

    @Test
    public void checkScore(){
        Player player=new Player();
        player.setScore(10);
        assertEquals(10,player.getHighestScore());
    }
    @Test
    public void checkScore2(){
        Player player=new Player();
        player.setScore(100);
        assertEquals(10,player.getHighestScore());
    }
    @Test(expected = NullPointerException.class)
    public void playerTest(){
        Player player=null;
        player.setScore(100);

    }
    @Test(expected = RuntimeException.class)
    public void playerTest2(){
        Player player=null;
        player.setScore(100);

    }

    @Test(timeout = 100)
    public void checkScoreTime(){
        Player player=new Player();
        player.setScore(100);
        assertEquals(10,player.getHighestScore());
    }






    public static void main(String[] args) {
        Result result=
                JUnitCore.runClasses(GameTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
