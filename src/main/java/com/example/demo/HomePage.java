package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomePage extends Application implements HomePageInterface{
    @Override
    public void start(Stage stage) throws IOException {

        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game.fxml")));

        Scene scene = new Scene(root);
        stage.setTitle("Stick Hero");
        stage.setScene(scene);
        Parent root2= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game1.fxml")));

        stage.show();
    }
    public void gameControl(){

    }
    @Override
    public void play(){

    }

    @Override
    public void resume() {

    }

    @Override
    public void viewScore() {

    }

    @Override
    public void exit() {

    }

    public static void main(String[] args) {
        launch(args);
//        it is important to call start method
//        static method in application

    }
}