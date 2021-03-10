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
 * Klasa realizująca proces tworzenia prostokąta i kwadratu oraz ich animacji
 */
public class Prostokat extends Figures {


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
     * Tworzy nowy prostokąt
     * @param x
     * @param y
     * @param w
     * @param h
     * @param color
     * @return
     */
    public Rectangle generateRectangle(float x, float y, float w, float h, Color color) {

        rectangle = new Rectangle();
        rectangle.setHeight(h);
        rectangle.setWidth(w);
        rectangle.setLayoutY(y);
        rectangle.setFill(color);
        return rectangle;
    }

    /**
     * Ustala początkowe położenie obiektu na planszy
     * @param rectangle
     * @param anchorPane
     */
    public void setLayout(Rectangle rectangle, AnchorPane anchorPane) {
        random1 = (int) (Math.random() * (anchorPane.getPrefWidth() - 100) + rectangle.getWidth()); //losowanie liczby z przedziału [promień,szerokość - 100)
        random2 = (int) (Math.random() * (anchorPane.getPrefHeight() - 100) + rectangle.getHeight()); //losowanie liczby z przedziału [promień,wysokość - 100)
        rectangle.setVisible(true);
        rectangle.setLayoutX(random1); // początkowe położenie obiektu na osi OX
        rectangle.setLayoutY(random2); // początkowe położenie obiektu na osi OY
    }


    /**
     * Generuje animację dla obiektu
     * @param scene
     * @param anchorPane
     * @param ScoreLabel
     * @param AmmoLabel
     * @param TimeLabel
     */
    public void setRoute(Scene scene, AnchorPane anchorPane, Label ScoreLabel, Label AmmoLabel, Label TimeLabel)
    {
        startTime = Integer.valueOf(LevelFile.getInstance().LevelTime);
        setLayout(rectangle,anchorPane);

        timeline = new Timeline(new KeyFrame(Duration.millis(Double.parseDouble(LevelFile.getInstance().ObjectSpeed)), //czas trwania pojedynczego ruchu
                new EventHandler<ActionEvent>() {


                    double dx = new Random().nextInt(7) + 3; //przemieszczenie w osi OX
                    double dy = new Random().nextInt(7) + 3; //przemieszczenie w osi OY


                    @Override
                    public void handle(ActionEvent t) {

                        Bounds bounds = anchorPane.getBoundsInLocal();//pobiera parametry krawędzi okna
                        setNewLayout(rectangle,dx,dy, TimeLabel,AmmoLabel);


                        //odbicie od okna w przypadku zderzenia z krawędzią boczną
                        if (rectangle.getLayoutX() <= (bounds.getMinX()) ||
                                rectangle.getLayoutX() >= (bounds.getMaxX() - rectangle.getWidth())) {

                            dx = -dx;

                        }

                        //odbicie od okna w przypadku zderzenia z krawędzią górną/dolną
                        if (rectangle.getLayoutY() >= (bounds.getMaxY() - rectangle.getHeight()) ||
                                rectangle.getLayoutY() <= (bounds.getMinY())) {

                            dy = -dy;

                        }


                    }
                }));

        timeline.setCycleCount(Timeline.INDEFINITE); //powtarzanie animacji bez końca
        timeline.play();

        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                timeline.stop(); //po naciśnięciu na obiekt animacja zatrzymuje się
                rectangle.setVisible(false); //obiekt znika


                int abc = Integer.valueOf(ScoreLabel.getText()) + Integer.valueOf(LevelFile.getInstance().PointsForHit);
                ScoreLabel.setText(String.valueOf(abc));
                seqTransition.setOnFinished(event ->
                {

                    setRoute(scene,anchorPane, ScoreLabel, AmmoLabel, TimeLabel);


                });

                seqTransition.play();  //po pauzie ponowne wywołanie setRoute(...) dla obiektu
            }
        });

    }


}



