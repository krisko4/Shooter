package sample;


import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroler okienka z opcjami
 */
public class EscapeMenuController extends Controller {

    public AnchorPane pane;
    public Button OptionsButton;
    public Button ResumeButton;
    public Button BackToMenuButton;
    public Button ExitButton;

    public void initialize(URL url, ResourceBundle resourceBundle){
        sceneName="FXMLs/StartMenu.fxml";
        PopUpWindowName="FXMLs/SettingsMenu.fxml";

        ResumeButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                closeWindow(mouseEvent);

            }
        });






    }



}
