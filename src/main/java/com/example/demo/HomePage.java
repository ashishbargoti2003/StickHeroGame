package com.example.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;
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
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button PlayButton;

    @FXML
    private Button Exit;

    @FXML
    private Button BestScore;


    @FXML
    public void switchToPlayground(ActionEvent event) throws IOException {
        music();
        root= FXMLLoader.load(this.getClass().getResource("game1.fxml"));
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    MediaPlayer mediaPlayer;

    public void music() {
        String s = "D:\\college\\gitProject\\StickHeroGame\\src\\main\\resources\\com\\example\\demo\\stranger-things-124008.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();

    }

    @FXML
    public void switchToPlayground1(ActionEvent event) throws IOException {
        root= FXMLLoader.load(this.getClass().getResource("game.fxml"));
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void Help(ActionEvent event) throws IOException {
        root= FXMLLoader.load(this.getClass().getResource("help.fxml"));
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }






    public void gameControl() { }
    @Override
    public void play(){}

    @Override
    public void resume() {}

    @Override
    public void viewScore() {}

    @Override
    public void exit() {}

    public static void main(String[] args) {
        launch(args);
//        it is important to call start method
//        static method in application

    }
}