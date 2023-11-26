package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;
import javafx.scene.Node;


public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML

    public void switchToPlayground(ActionEvent event) throws IOException {
     root= FXMLLoader.load(this.getClass().getResource("game1.fxml"));
//     stage=(Stage)((javafx.scene.Node)event.getSource()).getScene.getWindow();
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    private Label welcomeText;

    @FXML
    void onHelloButtonClick(ActionEvent event) {
        System.out.println("clicked");

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