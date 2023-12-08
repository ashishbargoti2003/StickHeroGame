package com.example.demo;

import javafx.animation.*;
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
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;

import static java.lang.Thread.sleep;


/*
* New Things Done -
* I have added falling of hero when distance of stick less than distance between pillar using
* function HeroFalls but now this is just checking for constant distance
*
* Added Pillar for taking distance and added score variable for score
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
        music();

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
    private ImageView TheHero1;

    @FXML
    private Label welcomeText; // This is just a welcome label

    @FXML
    void onHelloButtonClick(MouseEvent event) {
        System.out.println("clicked");
    }

//    Score variable for the initail hero
    private ScoreCard score;

    public void InitializeScore() {
        score = new ScoreCard(0);
        score.setCurrentScore(0);
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
    void stopElongation(MouseEvent event) throws InterruptedException, IOException {
        System.out.println("Stopped stretching stick length");
        InitializePillars();
        InitializeScore();
        TheHero1.setVisible(false);

        if (elongateTimeline != null) {
            elongateTimeline.stop();
            rotateStick();
            Cherry.setVisible(true);
            // Initially just making for constant distance
            // checking if height of stick less than what is required, then hero falls
            if(StickI.getHeight() < Pillar_1.getDistance() - 20) {
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

    MediaPlayer mediaPlayer;
    public void music() {
        String s = "C:\\Users\\91828\\Downloads\\ProjectGame\\StickHeroGame\\src\\main\\music\\neon-gaming-128925.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();

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
        // This is amount of elongation
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

    private Pillar Pillar_1, Pillar_2;


    public void InitializePillars() {
        // This is for initializing the two pillar's
        Pillar_1 = new Pillar(Pillar1.getFitWidth(), Pillar2.getLayoutX() - Pillar1.getLayoutX());
        Pillar_2 = new Pillar(Pillar2.getFitWidth(), Pillar2.getLayoutX() - Pillar1.getLayoutX());
        System.out.printf("value is %f, %f, %f, %f\n", Pillar_1.getDistance(), Pillar_2.getDistance(), Pillar_1.getWidth(), Pillar_2.getWidth());
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
    private ImageView Cherry;

    @FXML
    private Button Press;

    int FlipCount = 0, Status = 0;

    @FXML
    public void Flip() {
        // THis function is for flipping the hero
        if(FlipCount % 2 == 0) {
            if(Cherry.getX() == TheHero.getX() && Status == 0) {
                Cherry.setVisible(false);
                Status = 1;
                TheHero.setVisible(false);
                TheHero1.setVisible(true);
            }
            else {
                TheHero.setVisible(false);
                TheHero1.setVisible(true);
            }

        }
        else {
            TheHero.setVisible(true);
            TheHero1.setVisible(false);
        }
        FlipCount ++;
    }

    @FXML
    private void handleButtonAction() {

        // Assume 'imageView' is your ImageView and 'totalDistance' is the total distance you want to move it
        double totalDistance = MoveHero(); // replace with your actual total distance
        double halfDistance = Cherry.getLayoutX() - 45;

        // Create a Timeline for the animation
        Timeline timeline1 = new Timeline();
        // First KeyFrame: move the ImageView to halfDistance over half the total time
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(5000), new KeyValue(TheHero.translateXProperty(), halfDistance));
        timeline1.getKeyFrames().add(keyFrame1);



        Timeline Hero1 = new Timeline();
        // First KeyFrame: move the ImageView to halfDistance over half the total time
        KeyFrame keyFrameHero1 = new KeyFrame(Duration.millis(5000), new KeyValue(TheHero1.translateXProperty(), halfDistance));
        Hero1.getKeyFrames().add(keyFrameHero1);

        Hero1.play();
        timeline1.play();

        Timeline timeline2 = new Timeline();
        // Second KeyFrame: move the ImageView from halfDistance to totalDistance over the remaining time
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(10000), new KeyValue(TheHero.translateXProperty(), totalDistance));
        timeline2.getKeyFrames().add(keyFrame2);

        Timeline hero2 = new Timeline();
        // Second KeyFrame: move the ImageView from halfDistance to totalDistance over the remaining time
        KeyFrame keyFramehero2 = new KeyFrame(Duration.millis(10000), new KeyValue(TheHero1.translateXProperty(), totalDistance));
        hero2.getKeyFrames().add(keyFramehero2);
// Set an event handler for when the first KeyFrame is finished (i.e., when the ImageView has reached half its distance)
        timeline2.setOnFinished(event ->
        {
            if(Counter == 0) {
                Counter = 1;
                Pillar1.setVisible(false);
                StickI.setVisible(false); // This is the user stick

                // This part is for moving the Hero
                TranslateTransition MovingHero = new TranslateTransition();
                MovingHero.setDuration(Duration.millis(1000));
                MovingHero.setNode(TheHero);
                MovingHero.setByX(-1 * MoveHero());

                TranslateTransition MovingHero1 = new TranslateTransition();
                MovingHero1.setDuration(Duration.millis(1000));
                MovingHero1.setNode(TheHero1);
                MovingHero1.setByX(-1 * MoveHero());


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
                MovingHero1.play();

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
            else {
                Counter = 0;
                Pillar2.setVisible(false);
                StickI.setVisible(false);

                // For moving hero
                TranslateTransition MovingHero = new TranslateTransition();
                MovingHero.setDuration(Duration.millis(1000));
                MovingHero.setNode(TheHero);
                MovingHero.setByX(-1 * MoveHero());

                TranslateTransition MovingHero1 = new TranslateTransition();
                MovingHero1.setDuration(Duration.millis(1000));
                MovingHero1.setNode(TheHero1);
                MovingHero1.setByX(-1 * MoveHero());

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
                MovingHero1.play();
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
            TheHero.setVisible(true);
            TheHero1.setVisible(false);
            Status = 0;
            FlipCount = 0;
        });
        hero2.play();
        timeline2.play();
    }
}
