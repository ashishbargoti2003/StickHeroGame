package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.animation.RotateTransition;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;

import static java.lang.Thread.sleep;


/*
* New Things Done -
* I have added falling of hero when distance of stick less than distance between pillar using
* function HeroFalls but now this is just checking for constant distance
*
* I have also removed the error when coming while extending stick
*
* Also added scorecard scene on which hitting play will take you to again the game scene
*
* */

/*
* Things remaining
* Check the distance of the pillar and make the Herofalls function in accordance if it, now it is
* just checking the constant distance.
*
*Score is not updated and not stored best score also
*
 */
public class Controller implements Initializable{
    @FXML
    public Rectangle stick2;

    @FXML
    private Rectangle StickI; // This is the main stick

    final double Original_heightofStick = 10; // This is the original Height of the Stick

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button myButton;

    private String GameScreen = "game1.fxml";
    private String ScorecardScreen = "scorecard.fxml";
    private String EntryScreen = "game.fxml";
    private String ExitScreen = "exit.fxml";


    @FXML
    public void switchToPlayground(ActionEvent event) throws IOException {

        root= FXMLLoader.load(this.getClass().getResource("game1.fxml"));
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void SwitchToScoreCard() throws IOException {
        Parent menu_parent = FXMLLoader.load(getClass().getResource("scorecard.fxml"));
        Scene SceneMenu = new Scene(menu_parent);
        Stage stage = (Stage) TheHero.getParent().getScene().getWindow();
        stage.setScene(SceneMenu);
        stage.show();
    }


    @FXML
    private ImageView TheHero; // This is the Hero character

    @FXML
    private Label welcomeText; // This is just a welcome label

    @FXML
    void onHelloButtonClick(MouseEvent event) {
        System.out.println("clicked");

    }

//    Score variable for the initail hero
    ScoreCard score = new ScoreCard(0);
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
    void stopElongation(MouseEvent event) throws InterruptedException, IOException {
        System.out.println("Stopped stretching stick length");

        if (elongateTimeline != null) {
            elongateTimeline.stop();
            rotateStick();
            // Initially just making for constant distance
            // checking if height of stick less than what is required, then hero falls
            if(StickI.getHeight() < 280) {
                HeroFalls();
                // Set Best score accordingly
                // Here after this, Switch scene to the revival or scorecard screen...
            }
            else{
                // if this happens then calculating  the incrementing score of the player
                score.setCurrentScore(score.getCurrentScore() + 10);
                handleButtonAction();
            }

        }
    }

    @FXML
    private Button PauseButton; // THis is for the pause button

    @FXML
    void Pause(ActionEvent e) { System.out.println("pause!!!"); }

    @FXML
    void printf(MouseEvent event) {
        System.out.println("this is clicked now");
    }


    @FXML
    private void increaseSize() {

        double originalY = StickI.getY();
        double originalHeight = StickI.getHeight();
        double newHeight = originalHeight + 70; // You can adjust the value as needed

        // Adjust the position of the rectangle to keep the base fixed
        StickI.setY(originalY - (newHeight - originalHeight));

        // Set the new height
        StickI.setHeight(newHeight);
    }

    @FXML
    private void decreaseSize() {
        // Example: Decrease height
        // I have removed width part from here as there is not need to increase and decrease the
        // width of the stick
        double newHeight = StickI.getHeight() - 10;
        StickI.setHeight(newHeight);
    }

    @FXML
    private TextField DisplayScore;

    @FXML
    private Button PlayAgain;

    private Rotate rotateTransform;
    @FXML
    public void rotateStick(){
        Timeline rotationTimeline;
        // Calculate the base of the stick
        double baseX = StickI.getX() + StickI.getWidth() / 2;
        double baseY = StickI.getY() + StickI.getHeight();

        // Create a Rotate transform
        rotateTransform = new Rotate(0, baseX, baseY);

        // Apply the Rotate transform to the Stick
        StickI.getTransforms().add(rotateTransform);

        // Rotate the Stick by 90 degrees clockwise around the base
        rotateTransform.setAngle(90);

        // Adjust layout parameters if needed
        StickI.setX(baseX - StickI.getWidth() / 2);
        StickI.setY(baseY - StickI.getHeight());
        // Create a Timeline for the rotation animation

    }

    @FXML
    public void rotateStick2(){
        double baseX = StickI.getX() + StickI.getWidth() / 2;
        double baseY = StickI.getY() + StickI.getHeight();
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(500), StickI);
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
        LengthtoMove.setLengthofStick((int)StickI.getHeight());
//        will make sure user traverses only till stick length
//        need to make sure
//        if distance between pillars!=stick.length then hero falls
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


    public void InitializePillars() {
        System.out.printf("value is %f\n, %f\n", Pillar1.getFitWidth(), Pillar2.getFitWidth());
    }


    int Counter = 0;

    @FXML
    private void HeroFalls() {
        // This function is for moving the hero uptil the end of stick
        // And when he reaches there he falls...
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(1000));
        transition.setNode(TheHero);
        System.out.println(MoveHero());
        transition.setByX(StickI.getHeight());
        transition.setAutoReverse(true);
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TranslateTransition HeroFalling = new TranslateTransition();
                HeroFalling.setDuration(Duration.millis(1000));
                HeroFalling.setNode(TheHero);
                HeroFalling.setByY(500);
                HeroFalling.setOnFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
//                            DisplayScore.setText("" + score.getCurrentScore());
                            SwitchToScoreCard();

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }


                });
                HeroFalling.play();
            }
        });
        transition.play();
    }

    @FXML
    private void handleButtonAction() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(1000));
        transition.setNode(TheHero);
        System.out.println(MoveHero());
        transition.setByX(MoveHero());
        transition.setAutoReverse(true);
        if(Counter == 0) {
            Counter = 1;
            transition.setOnFinished(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Pillar1.setVisible(false);
                    StickI.setVisible(false); // This is the user stick

                    // This part is for moving the Hero
                    TranslateTransition MovingHero = new TranslateTransition();
                    MovingHero.setDuration(Duration.millis(1000));
                    MovingHero.setNode(TheHero);
                    MovingHero.setByX(-1 * MoveHero());

                    // THis part is for moving pillar2
                    TranslateTransition MovingPillar2 = new TranslateTransition();
                    MovingPillar2.setDuration(Duration.millis(1000));
                    MovingPillar2.setNode(Pillar2);
                    MovingPillar2.setByX(-1 * MoveHero());

                    // This part is for moving Stick
                    TranslateTransition MovingStick = new TranslateTransition();
                    MovingStick.setDuration(Duration.millis(1000));
                    MovingStick.setNode(StickI);
                    MovingStick.setByX(-1 * MoveHero());

                    // This part is for moving Pillar1
                    TranslateTransition MovingPillar1 = new TranslateTransition();
                    MovingPillar1.setDuration(Duration.millis(1000));
                    MovingPillar1.setNode(Pillar1);
                    MovingPillar1.setByX(MoveHero());

                    MovingPillar2.play();
                    MovingHero.play();
                    MovingPillar1.play();
                    MovingStick.play();

                    MovingPillar1.setOnFinished(new EventHandler<ActionEvent>() {
                        // when Pillar1 is at its place ...
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            Pillar1.setVisible(true);
                            StickI.setHeight(Original_heightofStick); // Redo the height of stick
                            // Rotating the stick 270Deg as initial condition
                            rotateStick();
                            rotateStick();
                            rotateStick();
                            StickI.setVisible(true);
                        }
                    });
                }
            });
            transition.play();
        }
        else {
            Counter = 0;
            transition.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Pillar2.setVisible(false);
                    StickI.setVisible(false);

                    // For moving hero
                    TranslateTransition MovingHero = new TranslateTransition();
                    MovingHero.setDuration(Duration.millis(1000));
                    MovingHero.setNode(TheHero);
                    MovingHero.setByX(-1 * MoveHero());

                    // for moving pillar1
                    TranslateTransition MovingPillar1 = new TranslateTransition();
                    MovingPillar1.setDuration(Duration.millis(1000));
                    MovingPillar1.setNode(Pillar1);
                    MovingPillar1.setByX(-1 * MoveHero());

                    // for moving pillar2
                    TranslateTransition MovingPillar2 = new TranslateTransition();
                    MovingPillar2.setDuration(Duration.millis(1000));
                    MovingPillar2.setNode(Pillar2);
                    MovingPillar2.setByX(MoveHero());

                    // for moving stick
                    TranslateTransition MovingStick = new TranslateTransition();
                    MovingStick.setDuration(Duration.millis(1000));
                    MovingStick.setNode(StickI);
                    MovingStick.setByX(-1 * MoveHero());

                    MovingPillar1.play();
                    MovingHero.play();
                    MovingPillar2.play();
                    MovingStick.play();
                    MovingPillar2.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            Pillar2.setVisible(true);

                            StickI.setHeight(Original_heightofStick);
                            rotateStick();
                            rotateStick();
                            rotateStick();
                            StickI.setVisible(true);
                        }
                    });
                }
            });

            transition.play();
        }
    }
}
