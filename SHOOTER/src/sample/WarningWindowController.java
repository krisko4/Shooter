package sample;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroler okna wymuszającego wpisanie pseudonimu
 */
public class WarningWindowController extends Controller {
    public Button OKButton;

    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        OKButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                window.close();
            }
        });
    }
}
