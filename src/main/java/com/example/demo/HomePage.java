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

    public class SceneFactory {
        public Scene getScene(String type, ActionEvent event) throws IOException {
            Parent root;
            if ("Playground".equalsIgnoreCase(type)) {
                root = FXMLLoader.load(getClass().getResource("game1.fxml"));
            } else if ("Playground1".equalsIgnoreCase(type)) {
                root = FXMLLoader.load(getClass().getResource("game.fxml"));
            } else if ("Help".equalsIgnoreCase(type)) {
                root = FXMLLoader.load(getClass().getResource("help.fxml"));
            } else {
                throw new IllegalArgumentException("Invalid type: " + type);
            }
            return new Scene(root);
        }
    }

    @FXML
    public void switchToPlayground(ActionEvent event) throws IOException {
        music();
        SceneFactory sceneFactory = new SceneFactory();
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene = sceneFactory.getScene("Playground", event);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToPlayground1(ActionEvent event) throws IOException {
        SceneFactory sceneFactory = new SceneFactory();
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene = sceneFactory.getScene("Playground1", event);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void Help(ActionEvent event) throws IOException {
        SceneFactory sceneFactory = new SceneFactory();
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene = sceneFactory.getScene("Help", event);
        stage.setScene(scene);
        stage.show();
    }


    MediaPlayer mediaPlayer;

    public void music() {
        String resourcePath = "/com/example/demo/stranger-things-124008.mp3";
        String resourceUrl = getClass().getResource(resourcePath).toExternalForm();

        Media media = new Media(resourceUrl);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
//        String s = "D:\\college\\gitProject\\StickHeroGame\\src\\main\\resources\\com\\example\\demo\\stranger-things-124008.mp3";
//        Media h = new Media(Paths.get(s).toUri().toString());
//        mediaPlayer = new MediaPlayer(h);
//        mediaPlayer.play();

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
    @FXML
    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public static void main(String[] args) {
        launch(args);
//        it is important to call start method
//        static method in application

    }
}