package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.URL;

import java.util.ResourceBundle;

/**
 * Kontroler okna w którym użytkownik wpisuje pseudonim
 */
public class EnterYourNicknameController extends Controller {


    public Button OKButton;
    public Button cancelButton;
    public javafx.scene.control.TextField TextField;




    public void initialize(URL url, ResourceBundle resourceBundle) {
        sceneName = "FXMLs/GameScene.fxml";
        PopUpWindowName = "FXMLs/EnterYourNickname.fxml";
        loadLevelChooser();






    }

    /**
     * Jeśli użytkownik wpisze pseudonim tworzy nowego gracza, zapisuje pseudonim i otwiera okno z wyborem poziomu, w innym wypadku wymusza wpisanie pseudonimu
     */


public void loadLevelChooser()
{
    OKButton.setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {

            String x = TextField.getText();
            if(x.equals(""))
            {
                try{
                    setPopUpWindow("FXMLs/WarningWindow.fxml");
                }
                catch (IOException ie) {
                    System.out.println("Failed to load WarningWindow");
                }
            }
            else {
                closeWindow(mouseEvent);
                Player player = Player.getInstance(); //Tworzenie nowego gracza
                player.Nickname = x; //zapis pseudonimu
                try{
                    FXMLLoader loader=setPopUpWindow("FXMLs/LevelChooser.fxml");
                    LevelChooserController controller=loader.getController();
                    controller.setLevelsToButtons();
                    controller.showAvailableLevels();
                }
                catch (IOException ie){
                    System.out.println("Failed to load LevelChooser");
                }

            }

        }
    });
}


    }
















