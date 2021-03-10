package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import sample.Levels.*;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroler okna w którym wybiera się poziom trudności
 */
public class LevelChooserController extends Controller {

    public AnchorPane pane;
    public Button silverLevelButton;
    public Button goldLevelButton;
    public Button supremeLevelButton;
    public Button eagleLevelButton;
    public Button globalLevelButton;
    public String Levels;







    public void initialize(URL url, ResourceBundle resourceBundle){




        sceneName="FXMLs/TrainingScene.fxml";
        PopUpWindowName="FXMLs/EscapeMenu.fxml";
        fileName= ParametricFile.getInstance().Filename;




    }



    /**
     * W zależności od wciśniętego przycisku ładuje dany poziom i zapisuje jego parametry do pliku o nazwie pobranej z pliku parametrycznego, nast
     */

    public void setLevelsToButtons() {

        silverLevelButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {

                closeWindow(me); //zamknięcie bieżącego okna
                SilverLevel level= new SilverLevel();
                Player player=Player.getInstance();
                Player.getInstance().Difficulty="Silver"; //zmienna Difficulty posłuży jako klucz klasyfikujący wyniki w tabeli wyników
                startGame(level); //rozpoczęcie gry


            }
        });


        goldLevelButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {

                closeWindow(me);
                GoldLevel level=new GoldLevel();
                Player.getInstance().Difficulty="Gold";
                startGame(level);



            }

        });
        eagleLevelButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {

                closeWindow(me);
                EagleLevel level=new EagleLevel();
                Player.getInstance().Difficulty="Eagle";
                startGame(level);


            }

        });
        supremeLevelButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {

                closeWindow(me);
                SupremeLevel level=new SupremeLevel();
                Player.getInstance().Difficulty="Supreme";
                startGame(level);


            }

        });
        globalLevelButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {

                closeWindow(me);;
                GlobalLevel level=new GlobalLevel();
                Player.getInstance().Difficulty="Global";
                startGame(level);


            }

        });
    }

    /**
     * Rozpoczyna grę
     * @param level
     */

    public void startGame(Level level){
        LevelOne level1=new LevelOne();
        level1.setStartParameters(level);
        saveLevelToFile(level1);
        LevelFile.getInstance().readFile();//przypisanie Singletonowi LevelFile wartości odczytanych z pliku parametrycznego
        GameStage stage=new GameStage();
        stage.setClosable();
        stage.start();
    }

    /**
     * Wyświetla ilość dostępnych poziomów w zależności od pliku parametrycznego
     */

    public void showAvailableLevels()
    {

        if(ParametricFile.getInstance().NumberOfLevels.equals("2"))
        {
            eagleLevelButton.setVisible(false);
            supremeLevelButton.setVisible(false);
            globalLevelButton.setVisible(false);
        }
        else if(ParametricFile.getInstance().NumberOfLevels.equals("3"))
        {
            supremeLevelButton.setVisible(false);
            globalLevelButton.setVisible(false);
        }
        else if(ParametricFile.getInstance().NumberOfLevels.equals("4"))
        {
            globalLevelButton.setVisible(false);
        }
    }

}


