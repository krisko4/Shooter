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
 * Klasa realizująca proces tworzenia trójkąta i jego animacji
 */
public class Trojkat extends Figures {


    public Bounds bounds;
    public int random1, random2;
    public Circle circle;
    public Rectangle rectangle;
    public Properties x;
    public ScaleTransition scaleTransition;
    private static MediaPlayer player;
    public Timeline timeline;
    public Integer startTime;

    public Polygon triangle;
    public Integer seconds = startTime;


    /**
     * Rysuje trójkąt
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param color
     * @return
     */
    public Polygon generateTriangle(double x1, double y1, double x2, double y2, Color color) {

        triangle = new Polygon(x1, y1, x2, y1, (x1 + x2) / 2, y2);
        triangle.setFill(color);
        return triangle;


    }

    /**
     * Przydziela pierwotne położenie na planszy
     * @param polygon
     * @param anchorPane
     */
    public void setLayout(Polygon polygon, AnchorPane anchorPane) {
        random1 = (int) (Math.random() * (anchorPane.getPrefWidth() - 100)); //losowanie liczby z przedziału [promień,szerokość - 100)
        random2 = (int) (Math.random() * (anchorPane.getPrefHeight() - 100));//losowanie liczby z przedziału [promień,wysokość - 100)
        triangle.setVisible(true);
    }

    /**
     * Przydziela nowe położenie na planszy
     * @param polygon
     * @param dx
     * @param dy
     * @param TimeLabel
     * @param AmmoLabel
     * @param bounds
     */
    public void setNewLayout(Polygon polygon, double dx, double dy, Label TimeLabel, Label AmmoLabel,Bounds bounds) {
        triangle.getPoints().set(0, triangle.getPoints().get(0) + dx);
        triangle.getPoints().set(2, triangle.getPoints().get(2) + dx);
        triangle.getPoints().set(4, triangle.getPoints().get(4) + dx);

        triangle.getPoints().set(1, triangle.getPoints().get(1) + dy);
        triangle.getPoints().set(3, triangle.getPoints().get(3) + dy);
        triangle.getPoints().set(5, triangle.getPoints().get(5) + dy);

        if (TimeLabel.getText().equals("0") || AmmoLabel.getText().equals("0")) {
            triangle.setVisible(false);
        }



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
        setLayout(triangle,anchorPane);



        timeline = new Timeline(new KeyFrame(Duration.millis(Double.parseDouble(LevelFile.getInstance().ObjectSpeed)), //czas trwania pojedynczego ruchu
                new EventHandler<ActionEvent>() {


                    double dx = new Random().nextInt(7) + 3; //przemieszczenie w osi OX
                    double dy = new Random().nextInt(7) + 3; //przemieszczenie w osi OY


                    @Override
                    public void handle(ActionEvent t) {

                        Bounds bounds = anchorPane.getBoundsInLocal();//pobiera parametry krawędzi okna
                        setNewLayout(triangle, dx, dy, TimeLabel, AmmoLabel,bounds);
                        //odbicie od okna w przypadku zderzenia z krawędzią boczną
                        if (triangle.getPoints().get(0) <= (bounds.getMinX()) ||
                                triangle.getPoints().get(2) >= (bounds.getMaxX())) {

                            dx = -dx;

                        }

                        //odbicie od okna w przypadku zderzenia z krawędzią górną/dolną
                        if (triangle.getPoints().get(1) >= (bounds.getMaxY()) ||
                                triangle.getPoints().get(5) <= (bounds.getMinY())) {

                            dy = -dy;

                        }


                    }
                }));

        timeline.setCycleCount(Timeline.INDEFINITE); //powtarzanie animacji bez końca
        timeline.play();

        triangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                timeline.stop(); //po naciśnięciu na obiekt animacja zatrzymuje się
                triangle.setVisible(false); //obiekt znika


                int abc = Integer.valueOf(ScoreLabel.getText()) + Integer.valueOf(LevelFile.getInstance().PointsForHit);
                ScoreLabel.setText(String.valueOf(abc));

                seqTransition.setOnFinished(event ->
                {

                    setRoute(scene,anchorPane, ScoreLabel, AmmoLabel, TimeLabel);


                });

                seqTransition.play(); //po pauzie ponowne wywołanie setRoute(...) dla obiektu
            }
        });
    }


}



