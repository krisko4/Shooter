package sample.Figures;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import sample.Figures.Figures;
import sample.LevelFile;

import java.util.Properties;
import java.util.Random;

/**
 * Klasa realizująca proces tworzenia kółka i jego animacji
 */
public class Kolko extends Figures {


    public Bounds bounds;
    public int random1, random2;
    public Circle circle;
    public Rectangle rectangle;
    public Properties x;
    private static MediaPlayer player;
    public Timeline timeline;
    public Integer startTime;

    public Polygon triangle;
    public Integer seconds = startTime;

    /**
     * Rysuje i zwraca kółko
     * @param x
     * @param y
     * @param r
     * @param color
     * @return
     */

    public Circle generateCircle(float x, float y, float r, Color color) {
        circle = new Circle();
        circle.setLayoutX(x);
        circle.setLayoutY(y);
        circle.setRadius(r);
        circle.setFill(color);

        return circle;
    }

    /**
     * Ustala położenie kółka na planszy gdy obiekt się wyświetla
     * @param circle
     * @param anchorPane
     */
    public void setLayout(Circle circle, AnchorPane anchorPane) {
        random1 = (int) (Math.random() * (anchorPane.getPrefWidth() - 200) + circle.getRadius()); //losowanie liczby z przedziału [promień,szerokość - 100)
        random2 = (int) (Math.random() * (anchorPane.getPrefHeight() - 200) + circle.getRadius()); //losowanie liczby z przedziału [promień,wysokość - 100)
        circle.setVisible(true);
        circle.setLayoutX(random1); // początkowe położenie obiektu na osi OX
        circle.setLayoutY(random2); // początkowe położenie obiektu na osi OY
    }


    /**
     * Zatrzymuje animację po naciśnięciu na obiekt, zwiększa ilość punktów, obiekt znika i animacja wywołana jest ponownie
     * @param ScoreLabel
     * @param scene
     * @param anchorPane
     * @param TimeLabel
     * @param AmmoLabel
     */

    public void onClickAction(Label ScoreLabel, Scene scene, AnchorPane anchorPane, Label TimeLabel, Label AmmoLabel)
    {

        circle.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                timeline.stop(); //po naciśnięciu na obiekt animacja zatrzymuje się
                circle.setVisible(false); //obiekt znika
                  /* Media pick = new Media(new File("ema.mp3").toURI().toString()); // replace this with your own audio file
                   player = new MediaPlayer(pick);
                   player.play();*/
                int abc = Integer.valueOf(ScoreLabel.getText()) + Integer.valueOf(LevelFile.getInstance().PointsForHit);
                ScoreLabel.setText(String.valueOf(abc));

                seqTransition.setOnFinished(event ->
                {


                    setRoute(scene, anchorPane, ScoreLabel, AmmoLabel, TimeLabel);


                });

                seqTransition.play(); //po pauzie ponowne wywołanie setRoute(...) dla obiektu

            }
        });
    }


    /**
     * Tworzy animację
     * @param scene
     * @param anchorPane
     * @param ScoreLabel
     * @param AmmoLabel
     * @param TimeLabel
     */
    public void setRoute(Scene scene, AnchorPane anchorPane, Label ScoreLabel, Label AmmoLabel, Label TimeLabel)
    {
        startTime = Integer.valueOf(LevelFile.getInstance().LevelTime);
        setLayout(circle,anchorPane);


        timeline = new Timeline(new KeyFrame(Duration.millis(Double.parseDouble(LevelFile.getInstance().ObjectSpeed)), //czas trwania pojedynczego ruchu
                new EventHandler<ActionEvent>() {


                    double dx = new Random().nextInt(7) + 3; //przemieszczenie w osi OX
                    double dy = new Random().nextInt(7) + 3; //przemieszczenie w osi OY


                    @Override
                    public void handle(ActionEvent t) {

                        Bounds bounds = anchorPane.getBoundsInLocal();//pobiera parametry krawędzi okna
                        //bounce(circle, TimeLabel, AmmoLabel, dx, dy,bounds);
                            setNewLayout(circle,dx,dy, TimeLabel,AmmoLabel);


                        //odbicie od okna w przypadku zderzenia z krawędzią boczną
                        if (circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius()) ||
                                circle.getLayoutX() >= (bounds.getMaxX() - circle.getRadius())) {

                            dx = -dx;

                        }

                        //odbicie od okna w przypadku zderzenia z krawędzią górną/dolną
                        if ((circle.getLayoutY() >= (bounds.getMaxY() - circle.getRadius())) ||
                                (circle.getLayoutY() <= (bounds.getMinY() + circle.getRadius()))) {

                            dy = -dy;

                        }


                    }
                }));

        timeline.setCycleCount(Timeline.INDEFINITE); //powtarzanie animacji bez końca
        timeline.play();

        onClickAction(ScoreLabel,scene,anchorPane,TimeLabel,AmmoLabel);


    }


    }



