package sample;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroler okna potwierdzającego daną decyzję
 */
public class ConfirmationWindowController extends Controller {


public Button BackButton;
public Button YesButton;

    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        BackButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                closeWindow(mouseEvent);
            }
        });


    }
}
