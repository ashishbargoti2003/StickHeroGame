package com.example.demo;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Hero implements Runnable{
    private ImageView TheHero;
    private Rectangle StickI;

    public Hero(ImageView theHero, Rectangle stickI) {
        TheHero = theHero;
        StickI = stickI;

    }



    @Override
    public void run() {
        TranslateTransition HeroFalling1 = new TranslateTransition();
        HeroFalling1.setDuration(Duration.millis(1000));
        HeroFalling1.setNode(TheHero);
        HeroFalling1.setByY(-1 * 500);
        HeroFalling1.setByX(-1 * StickI.getHeight());

        HeroFalling1.play();

    }
}
