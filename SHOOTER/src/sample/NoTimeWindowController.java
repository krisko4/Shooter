package sample;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Levels.LevelOne;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroler okna wyskakującego gdy skończy się czas lub amunicja
 */
public class NoTimeWindowController extends GameSceneController {

    public Button TryAgainButton;


    public void initialize(URL url, ResourceBundle resourceBundle){
        fileName="Poziom.gra";

    }
}
