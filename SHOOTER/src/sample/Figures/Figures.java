package sample.Figures;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import sample.LevelFile;

/**
 * Klasa abstrakcyjna po której dziedziczą klasy Kolko i Prostokat.
 */
public class Figures extends Shape{

    public final SequentialTransition seqTransition=new SequentialTransition(new PauseTransition(Duration.millis(Double.parseDouble(LevelFile.getInstance().PauseTime)))
    );
    public final SequentialTransition sequTransition = new SequentialTransition(
                new PauseTransition(Duration.millis(Double.parseDouble(LevelFile.getInstance().VisibleTime)) // określa czas oczekiwania na ponowne wywołanie metody setRoute(...) po naciśnięciu na obiekt
            ));


    /**
     * Ustala nowe położenie obiektu na osi OX i OY. Jeśli skończy się czas lub amunicja obiekt znika.
     * @param shape
     * @param dx
     * @param dy
     * @param TimeLabel
     * @param AmmoLabel
     */
    public void setNewLayout(Shape shape, double dx, double dy, Label TimeLabel, Label AmmoLabel)
    {
        shape.setLayoutX(shape.getLayoutX() + dx); //mechanizm ruchu w osi OX
        shape.setLayoutY(shape.getLayoutY() + dy); //mechanizm ruchu w osi OY


        if (TimeLabel.getText().equals("0") || AmmoLabel.getText().equals("0")) {
            shape.setVisible(false);
        }



    }


}
