package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePage extends Application implements HomePageInterface{
    @Override
    public void start(Stage stage) throws IOException {
//        stage is like a window
//     Stage stage2= new Stage(); that how we create a stage

//        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("hello-view.fxml"));
        Parent root= FXMLLoader.load(getClass().getResource("game.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Stick Hero");
        stage.setScene(scene);
        stage.show();

//        Button button=new Button();
//        button.setText("ashish bargoti");
//        StackPane layout=new StackPane();
//        layout.getChildren().add(button);
//        Scene scene=new Scene(layout,300,250);
////        for creation of scene we must pass a root node node
//        stage.setScene(scene);
//        stage.show();
//        necessary to show the window or stage
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