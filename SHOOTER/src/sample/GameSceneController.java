package sample;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.shape.Rectangle;

import javafx.stage.Stage;
import javafx.util.Duration;


import sample.Figures.FigureCreator;
import sample.Levels.*;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.*;

/**
 * Kontroler głównej planszy
 */
public class GameSceneController extends Controller {


    public GameStage gameStage;
    public Stage stage;
    public Scene scene;
    public SplitPane pane;
    public Integer startTime;
    public Integer seconds;
    public Rectangle r1,r2;
    public Timeline time;
    public AnchorPane anchorPane2;
    public AnchorPane anchorPane1;
    public Label NicknameLabel, LevelLabel, ScoreLabel, AmmoLabel, TimeLabel;


    public void initialize(URL url, ResourceBundle resourceBundle) {



        NicknameLabel.setText(Player.getInstance().Nickname);
        fileName = ParametricFile.getInstance().Filename;
        setSizes();
        setLevel();
        setBackground(anchorPane2);
        reduceAmmo();
        setTimer();








    }

    /**
     * Ustawia odliczanie czasu na planszy
     */
    public void setTimer()
    {
        time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);

        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                seconds--;
                TimeLabel.setText(seconds.toString());
                if ((TimeLabel.getText().equals("0") && Integer.parseInt(ScoreLabel.getText()) < Integer.parseInt(LevelFile.getInstance().PromotionPoints)) || AmmoLabel.getText().equals("0")) {
                    time.stop();
                    gameOver();
                }


                if (Integer.parseInt(ScoreLabel.getText()) >= Integer.parseInt(LevelFile.getInstance().PromotionPoints) && !AmmoLabel.getText().equals("0") && TimeLabel.getText().equals("0")) {
                    time.stop();
                    savePlayerToGson();
                    startNewLevel();

                }
            }
        });

        time.getKeyFrames().add(frame);
        time.playFromStart();
    }

    /**
     * Uruchamia kolejny poziom gry
     */
    public void startNewLevel(){
        try {
            setPopUpWindow("FXMLs/LevelChangeWindow.fxml");
        } catch (IOException ie) {
            System.out.println("Loading failed(LevelChangeWindow)");
        }

        seqTransition = new SequentialTransition(new PauseTransition(Duration.seconds(5.5))
        );
        seqTransition.setOnFinished(event ->
                {
                    Stage stage = (Stage) anchorPane2.getScene().getWindow();
                    Scene scene=stage.getScene();
                    //stage.close();
                    changeLevel();
                   // gameStage = new GameStage();
                   // gameStage.setClosable();
                  //  gameStage.start();
                    setLevel();
                    setGame(scene);
                    setTimer();
                }
        );
        seqTransition.play();
    }

    /**
     * Otwiera komunikat o przegranej grze, umożliwia ponowne rozpoczęcie rozgrywki od poziomu 1.
     */
    public void gameOver()
    {
        try {

            FXMLLoader loader=setPopUpWindow("FXMLs/NoTimeWindow.fxml");
            NoTimeWindowController controller = loader.getController();

            controller.TryAgainButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Stage window = (Stage) anchorPane2.getScene().getWindow();
                    Scene scene=window.getScene();
                    closeWindow(mouseEvent);
                    LevelOne level = new LevelOne();
                    level.getDifficulty();
                    saveLevelToFile(level);
                    LevelFile.getInstance().readFile();
                    setLevel();
                    setGame(scene);
                    setTimer();
                }
            });
        } catch (IOException ie) {
            System.out.println("Failed to load NoTimeWindow");
        }
    }

    /**
     * Ustala rozmiary planszy
     */
    public void setSizes()
    {
        setLayout(pane);
        anchorPane1.setPrefWidth(pane.getPrefWidth());
        anchorPane2.setPrefHeight(pane.getPrefHeight() - anchorPane1.getPrefHeight());
        anchorPane2.setPrefWidth(pane.getPrefWidth());
    }

    /**
     * Tworzy mapę, w której kluczami są poziomy trudności a elementami listy wyników.
     * Zapisuje mapę do pliku JSON.
     */
    public void savePlayerToGson()
{
    Player player = Player.getInstance();
    Gson gson = new Gson();
    List<Player> foo;
    Map<String, List<Player>> map;
    try (JsonReader reader = new JsonReader(new FileReader("Output.json"))) {

        Type mapType = new TypeToken<Map<String, List<Player>>>() {
        }.getType();
        map = gson.fromJson(reader, mapType);

    } catch (IOException ie) {
        map = new HashMap<>();
        map.put(player.Difficulty, new ArrayList<Player>());
    }
    if (!map.containsKey(player.Difficulty)) {
        map.put(player.Difficulty, new ArrayList<Player>());
    }


    foo = map.get(Player.getInstance().Difficulty);


    player.Score = ScoreLabel.getText();
    player.Level = LevelLabel.getText();

    foo.add(player);

    Collections.sort(foo);
    if (foo.size() > 5) {
        foo.remove(foo.size() - 1);
    }

    try (Writer writer = new PrintWriter("Output.json","UTF-8")) {
        Gson gson1 = new GsonBuilder().create();
        gson1.toJson(map, writer);
    } catch (IOException ie) {
        System.out.println("Failed to save player to Gson");
    }

}

    /**
     * Redukuje ilość amunicji po każdym naciśnięciu myszki
     */
    public void reduceAmmo(){

        anchorPane2.setOnMousePressed((new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {


            int def = Integer.valueOf(AmmoLabel.getText()) - 1;
            AmmoLabel.setText(String.valueOf(def));
            if (Integer.valueOf(AmmoLabel.getText()) <= 0) {
                AmmoLabel.setText("0");
            }

        }


    }));
}

    /**
     * Przydziela elementom planszy wartości zależnie od poziomu gry
     */
    public void setLevel() {

        startTime = Integer.valueOf(LevelFile.getInstance().LevelTime);
        seconds = startTime;
        LevelLabel.setText(LevelFile.getInstance().LevelNumber);
        ScoreLabel.setText("0");
        AmmoLabel.setText(String.valueOf(LevelFile.getInstance().Ammo));
        TimeLabel.setText(String.valueOf(LevelFile.getInstance().LevelTime));
    }

    /**
     * W zależności od pliku parametrycznego tworzy odpowiednie obiekty i wywołuje animacje
     * @param scene
     */
    public void setGame(Scene scene) {

        FigureCreator creator = new FigureCreator(scene, anchorPane2, ScoreLabel, AmmoLabel, TimeLabel);
        if (ParametricFile.getInstance().GameFigure != null) {
            if (ParametricFile.getInstance().GameFigure.equals("prostokąty") || ParametricFile.getInstance().GameFigure.equals("kwadraty")) {
                creator.getRectangles();
            } else if (ParametricFile.getInstance().GameFigure.equals("kółka")) {
                creator.getCircles();
            } else if (ParametricFile.getInstance().GameFigure.equals("trójkąty")) {
                creator.getTriangles();
            }
        } else {
            creator.getCircles();
            creator.addGraphics();

        }
    }

    /**
     * Zmienia poziom gry - tworzy poziom, zapisuje do pliku i przypisuje nowe wartości Singletonowi LevelFile
     */
    public void changeLevel()
        {
            switch (LevelFile.getInstance().LevelNumber) {
                case "1":
                    LevelTwo level2 = new LevelTwo();
                    saveLevelToFile(level2);
                    LevelFile.getInstance().readFile();
                    break;
                case "2":
                    LevelThree level3 = new LevelThree();
                    saveLevelToFile(level3);
                    LevelFile.getInstance().readFile();

                    break;
                case "3":
                    LevelFour level4 = new LevelFour();
                    saveLevelToFile(level4);
                    LevelFile.getInstance().readFile();

                    break;
                case "4":
                    LevelFive level5 = new LevelFive();
                    saveLevelToFile(level5);
                    LevelFile.getInstance().readFile();

                    break;

                case "5":
                    try {
                        FXMLLoader loader=setPopUpWindow("FXMLs/GameFinished.fxml");
                        GameFinishedController controller=loader.getController();
                        controller.OKButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                closeWindow(mouseEvent);
                                stage=(Stage)anchorPane2.getScene().getWindow();
                                stage.close();
                            }
                        });
                        break;

                    } catch (IOException ie) {
                        System.out.println("Loading failed");
                    }


    }
}
}












