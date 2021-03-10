package sample.Figures;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import sample.Figures.Kolko;
import sample.Figures.Prostokat;
import sample.Figures.Trojkat;
import sample.ParametricFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;


/**
 * Klasa zajmująca się tworzeniem obiektów, wyświetlaniem ich na planszy i tworzeniem ich animacji.
 */
public class FigureCreator {

    public Scene scene;
    public AnchorPane anchorPane;
    public Label ScoreLabel;
    public Label TimeLabel,AmmoLabel;
    public Rectangle r1,r2;
    public Circle c1,c2;
    public Polygon t1,t2;
    public float ObjectWidth,ScreenWidth;


    /**
     * Konstruktor pobierający parametry planszy
     * @param scene
     * @param anchorPane
     * @param ScoreLabel
     * @param TimeLabel
     * @param AmmoLabel
     */
    public FigureCreator(Scene scene, AnchorPane anchorPane, Label ScoreLabel,Label TimeLabel, Label AmmoLabel)
    {
        this.scene=scene;
        this.anchorPane=anchorPane;
        this.ScoreLabel=ScoreLabel;
        this.TimeLabel= TimeLabel;
        this.AmmoLabel=AmmoLabel;
        ObjectWidth=Float.parseFloat(ParametricFile.getInstance().ObjectWidth);
        ScreenWidth=Float.parseFloat(ParametricFile.getInstance().Width);


    }

    /**
     * Tworzy dwa prostokąty lub kwadraty, w zależności od pliku parametrycznego i tworzy ich animacje
     */

    public void getRectangles()
    {

        Prostokat prostokat=new Prostokat();
        Prostokat prostokat1=new Prostokat();
        if (ParametricFile.getInstance().GameFigure.equals("kwadraty")) {

                r1 = prostokat.generateRectangle(100, 100,(ObjectWidth*ScreenWidth)/100, (ObjectWidth*ScreenWidth)/100, Color.RED);
                r2 = prostokat1.generateRectangle(400, 300,(ObjectWidth*ScreenWidth)/100, (ObjectWidth*ScreenWidth)/100, Color.BLUE);
            } else if (ParametricFile.getInstance().GameFigure.equals("prostokąty")) {
                r1 = prostokat.generateRectangle(100, 100, (ObjectWidth*ScreenWidth)/100, (ObjectWidth*ScreenWidth)/200, Color.RED);
                r2 = prostokat1.generateRectangle(400, 300, (ObjectWidth*ScreenWidth)/100, (ObjectWidth*ScreenWidth)/200, Color.BLUE);
            }
            addToPane(r1,r2);
            prostokat.setRoute(scene, anchorPane, ScoreLabel, AmmoLabel, TimeLabel);
            prostokat1.setRoute(scene, anchorPane, ScoreLabel, AmmoLabel, TimeLabel);

    }

    /**
     * Wypełnia kółko plikiem graficznym
     */
    public void addGraphics()
    {
        try {
            Image image = new Image(new FileInputStream("pilka.jpg"));
            ImagePattern imagePattern = new ImagePattern(image);
            c1.setFill(imagePattern);
            c2.setFill(imagePattern);
        } catch (IOException ie) {
            System.out.println("Loading image in FigureCreator failed");
        }
    }

    /**
     * Tworzy dwa kółka i ich animacje
     */
    public void getCircles()
    {
        Kolko kolko=new Kolko();
        Kolko kolko1=new Kolko();
        c1 = kolko.generateCircle(109, 50, (ObjectWidth*ScreenWidth)/200, Color.RED);
        c2 = kolko1.generateCircle(230, 125, (ObjectWidth*ScreenWidth)/200, Color.BLUE);
        addToPane(c1,c2);
        kolko.setRoute(scene, anchorPane, ScoreLabel, AmmoLabel, TimeLabel);
        kolko1.setRoute(scene, anchorPane, ScoreLabel, AmmoLabel, TimeLabel);


    }

    /**
     * Dodaje obiekty na planszę
     * @param shape
     * @param shape1
     */
    public void addToPane(Shape shape, Shape shape1)
    {
        anchorPane.getChildren().add(shape);
        anchorPane.getChildren().add(shape1);
        shape.setVisible(false);
        shape1.setVisible(false);
    }

    /**
     * Tworzy dwa trójkąty i dodaje je na planszę
     */
    public void getTriangles()
    {
        Trojkat trojkat=new Trojkat();
        Trojkat trojkat1=new Trojkat();
        t1 = trojkat.generateTriangle(200, 200, (ObjectWidth*ScreenWidth/100)+200, 150, Color.YELLOW);
        t2 = trojkat1.generateTriangle(100, 100, (ObjectWidth*ScreenWidth/100)+100, 50, Color.BLUE);
        anchorPane.getChildren().add(t1);
        anchorPane.getChildren().add(t2);

        t1.setVisible(false);
        t2.setVisible(false);
        trojkat.setRoute(scene, anchorPane, ScoreLabel, AmmoLabel, TimeLabel);
        trojkat1.setRoute(scene, anchorPane, ScoreLabel, AmmoLabel, TimeLabel);

    }





}

