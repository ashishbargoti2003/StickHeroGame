package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class TestController {

    @FXML
    private TextArea text;
    @FXML
    Text scoreText;

    @FXML
   void display(){
        System.out.println(scoreText.getText());
        text.setText("ashish");
       System.out.println(text.getText());

   }

}
