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

    Stage stage;
    @Override
    public void start(Stage stage) throws IOException {

        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Stick Hero");
        stage.setScene(scene);
        stage.show();
        
    }

    public void gameControl() {  }
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
        stage.close();
    }

    public static void main(String[] args) {
        launch(args);


    }
}