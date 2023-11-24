package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller {

    @FXML
    private Label welcomeText;
    @FXML
    private Button CLickButton;

    @FXML
    private TextField InformationPane;

    @FXML
    private Button PauseButton;

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    void Click(ActionEvent event) throws IOException {
//        System.out.println("clicked");
        // ON click the Play Button switch the scene to the next Scene
        // Which is the main scene on which course of game is played
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game1.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void RemoveTextField(ActionEvent e) throws IOException {
        // ON pressing enter remove the Text field
//        System.out.println("hello\n");
        // So remove that text field
        InformationPane.setVisible(false); // this makes the field invisible


    }


    @FXML
    void printf(MouseEvent event) {
        System.out.println("this is clicked now");

    }

}






//package com.example.demo;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//
//public class HelloController {
//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
//}