package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Levels.LevelOne;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroler okna z ustawieniami
 */
public class SettingsMenuController  extends Controller {


    public Button OKButton;
    public MenuButton Button;
    public MenuItem FootballPitch;
    public MenuItem Basketball;
    public MenuItem TennisCourt;




    public void initialize(URL url, ResourceBundle resourceBundle) {
        String x = FootballPitch.toString();
        fileName=ParametricFile.getInstance().Filename;

    }

    /**
     * Umożliwia zmianę tła planszy
     * @param controller
     * @param x
     * @param menuItem
     */



    public void setNewBackground(EscapeMenuController controller, GameSceneController x, MenuItem menuItem) {



            //Stage z = (Stage) OKButton.getScene().getWindow();
            //z.close();



        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    FXMLLoader loader = setPopUpWindow("FXMLs/ConfirmationWindow.fxml");
                    ConfirmationWindowController controller2 = loader.getController();
                    controller2.YesButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            closeWindow(mouseEvent);




                        }
                    });
                } catch (IOException ie) {
                    System.out.println("Failed to load confirmation window");
                }
            }
        });
    }
}