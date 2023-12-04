package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;

import javafx.scene.Node;
import javafx.util.Duration;


public class Controller implements Initializable{
    @FXML
    public Rectangle stick2;
    @FXML
    private Rectangle Stick;


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button myButton;

    @FXML
    public void switchToPlayground(ActionEvent event) throws IOException {
     root= FXMLLoader.load(this.getClass().getResource("game1.fxml"));
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    private ImageView TheHero;




    @FXML
    private Label welcomeText;

    @FXML
    void onHelloButtonClick(MouseEvent event) {
        System.out.println("clicked");

    }
    boolean isMousePressed = true;


    private Timeline elongateTimeline;

    @FXML
    void elongateStick(MouseEvent event) {
        System.out.println("stretching stick length");

        elongateTimeline = new Timeline(
                new KeyFrame(Duration.millis(500), e -> {
                    increaseSize();
                })
        );

        elongateTimeline.setCycleCount(Timeline.INDEFINITE);

        elongateTimeline.play();
    }

    @FXML
    void stopElongation(MouseEvent event) {
        System.out.println("Stopped stretching stick length");

        if (elongateTimeline != null) {
            elongateTimeline.stop();
            rotateStick();
            handleButtonAction();
        }
    }





//    @FXML
//    void elongateStick(MouseEvent event) {
//
//        System.out.println("stretching stick length");
//        // Flag to control the while loop
//        boolean isMousePressed = true;
//
//        // Run the loop while the mouse button is pressed
//        while (isMousePressed) {
//            System.out.println("printing");
//
//            // Check if the primary mouse button is not pressed
//            if (!event.isPrimaryButtonDown()) {
//                isMousePressed = false; // Stop the loop when the mouse button is released
//            }
//        }
//
//        System.out.println("Stopped stretching stick length");
//
//    }
//    @FXML
//    void stopElongation(MouseEvent event) {
//        isMousePressed=false;
//
//        System.out.println("stopped stretching stick length");
//
//
//    }
    @FXML
    private Button PauseButton;

    @FXML
    void Pause(ActionEvent e) { System.out.println("pause!!!"); }

    @FXML
    void printf(MouseEvent event) {
        System.out.println("this is clicked now");

    }


    @FXML
    private void increaseSize() {

        double originalY = Stick.getY();
        double originalHeight = Stick.getHeight();
        double newHeight = originalHeight + 15; // You can adjust the value as needed

        // Adjust the position of the rectangle to keep the base fixed
        Stick.setY(originalY - (newHeight - originalHeight));

        // Set the new height
        Stick.setHeight(newHeight);



    }

    @FXML
    private void decreaseSize() {
        // Example: Decrease width and height
        double newWidth = Stick.getWidth() - 10;
        double newHeight = Stick.getHeight() - 10;

        Stick.setWidth(newWidth);
        Stick.setHeight(newHeight);
    }


//    @FXML
//    public void rotateStick3() {
//        // Calculate the base of the stick
//        double baseX = Stick.getX() + Stick.getWidth() / 2;
//        double baseY = Stick.getY() + Stick.getHeight();
//
//        // Create a RotateTransition for the rotation animation
//        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), Stick);
//
//        // Set the axis of rotation (base of the stick)
//        rotateTransition.setPivotX(baseX);
//        rotateTransition.setPivotY(baseY);
//
//        // Set the target angle (90 degrees clockwise)
//        rotateTransition.setByAngle(90);
//
//        // Play the rotation animation
//        rotateTransition.play();
//    }
//}
    private Rotate rotateTransform;
    @FXML
    public void rotateStick(){
        Timeline rotationTimeline;
        // Calculate the base of the stick
        double baseX = Stick.getX() + Stick.getWidth() / 2;
        double baseY = Stick.getY() + Stick.getHeight();

        // Create a Rotate transform
        rotateTransform = new Rotate(0, baseX, baseY);

        // Apply the Rotate transform to the Stick
        Stick.getTransforms().add(rotateTransform);

        // Rotate the Stick by 90 degrees clockwise around the base
        rotateTransform.setAngle(90);

        // Adjust layout parameters if needed
        Stick.setX(baseX - Stick.getWidth() / 2);
        Stick.setY(baseY - Stick.getHeight());
        // Create a Timeline for the rotation animation

    }


    @FXML
    public void rotateStick2(){
        double baseX = Stick.getX() + Stick.getWidth() / 2;
        double baseY = Stick.getY() + Stick.getHeight();
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(500), Stick);
        rotateTransition.setByAngle(90);

        rotateTransition.play();
    }
    @FXML
    private Label LabelText;

    @FXML
    void RemoveLabel(MouseEvent event) {
        LabelText.setVisible(false);
        System.out.println("Removed");
    }

    @FXML
    int MoveHero() {
        Stick LengthtoMove = new Stick();
        LengthtoMove.setLengthofStick(300);
        return LengthtoMove.getLengthofStick();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // nothing - - - -
    }
    @FXML
    private ImageView Pillar1;

    @FXML
    private ImageView Pillar2;

    @FXML
    private Rectangle StickI;

    int cnt = 0;

    @FXML
    private void handleButtonAction() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(1000));
        transition.setNode(TheHero);
        transition.setByX(MoveHero());
        transition.setAutoReverse(true);
//        transition.setCycleCount(1);
        if(cnt == 0) {
            cnt = 1;
            transition.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Pillar1.setVisible(false);
                    StickI.setVisible(false);
                    TranslateTransition newTransition1 = new TranslateTransition();
                    newTransition1.setDuration(Duration.millis(1000));
                    newTransition1.setNode(TheHero);
                    newTransition1.setByX(-1 * MoveHero());


                    TranslateTransition newTransition2 = new TranslateTransition();
                    newTransition2.setDuration(Duration.millis(1000));
                    newTransition2.setNode(Pillar2);
                    newTransition2.setByX(-1 * MoveHero());

                    TranslateTransition newTransition3 = new TranslateTransition();
                    newTransition3.setDuration(Duration.millis(1000));
                    newTransition3.setNode(Pillar1);
                    newTransition3.setByX(MoveHero());

                    newTransition2.play();
                    newTransition1.play();
                    newTransition3.play();
                    newTransition3.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            Pillar1.setVisible(true);
                        }
                    });
                    //                Pillar1.setVisible(true);
                }
            });

            transition.play();
        }
        else {
            cnt = 0;
            transition.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Pillar2.setVisible(false);
                    StickI.setVisible(false);
                    TranslateTransition newTransition1 = new TranslateTransition();
                    newTransition1.setDuration(Duration.millis(1000));
                    newTransition1.setNode(TheHero);
                    newTransition1.setByX(-1 * MoveHero());


                    TranslateTransition newTransition2 = new TranslateTransition();
                    newTransition2.setDuration(Duration.millis(1000));
                    newTransition2.setNode(Pillar1);
                    newTransition2.setByX(-1 * MoveHero());

                    TranslateTransition newTransition3 = new TranslateTransition();
                    newTransition3.setDuration(Duration.millis(1000));
                    newTransition3.setNode(Pillar2);
                    newTransition3.setByX(MoveHero());

                    newTransition2.play();
                    newTransition1.play();
                    newTransition3.play();
                    newTransition3.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            Pillar2.setVisible(true);
                        }
                    });
                    //                Pillar1.setVisible(true);
                }
            });

            transition.play();
        }
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