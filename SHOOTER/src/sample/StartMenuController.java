package sample;



import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Controller;
import sample.ParametricFile;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroler okna startowego programu
 */

public class StartMenuController  extends Controller {



    public Button newGameButton;
    public Button exitButton;
    public Button trainingButton;
    public Button scoreboardButton;
    public Pane pane;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane.getStyleClass().add("anchor");
        pane.getStylesheets().add("anchor3.css");
    }


    /**
     * Metoda przydzielająca poszczególnym przyciskom zadania związane z otwieraniem nowych okien
     * @param stage
     */

    public void setScenesToButtons(Stage stage)
        {
            newGameButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    try {
                        setPopUpWindow("FXMLs/EnterYourNickname.fxml");
                        ParametricFile.getInstance();

                    }
                    catch(IOException ie)
                    {
                        System.out.println("Loading failed");
                    }

                }
            });

            scoreboardButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try{


                       setPopUpWindow("FXMLs/Scoreboard.fxml");


                    }
                    catch(IOException ie)
                    {
                        System.out.println("Loading failed");
                    }
                }
            });


            trainingButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    try {
                        setPopUpWindow("FXMLs/FigureMenu.fxml");
                    }
                    catch(IOException ie)
                    {
                        System.out.println("Loading failed");
                    }
                }
            });

           exitButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    Platform.exit();
                }
            });
        }

    }

