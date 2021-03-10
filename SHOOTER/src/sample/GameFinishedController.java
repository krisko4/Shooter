package sample;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroler okna informującego o zakończeniu gry
 */
public class GameFinishedController extends Controller {

public Button OKButton;
public Label Label;

public void initialize(URL url, ResourceBundle resourceBundle)
{

   Label.setText(Player.getInstance().Score);

}

}
