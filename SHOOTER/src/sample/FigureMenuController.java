package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Levels.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroler okna wyboru obiektu gry w trybie treningowym
 */
public class FigureMenuController extends Controller {


    public ImageView Pilka;
    public ImageView Kolko;
    public ImageView Trojkat;
    public Button BackButton;
    public ImageView Kwadrat;
    public int identifier;
    public AnchorPane pane;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        pane.getStyleClass().add("anchor");
        pane.getStylesheets().add("anchor3.css");
        setChoosable(Kolko);
        setChoosable(Pilka);
        setChoosable(Trojkat);
        setChoosable(Kwadrat);
        fileName = "Poziom.gra";

        BackButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                                         @Override
                                         public void handle(MouseEvent mouseEvent) {
                                             closeWindow(mouseEvent);
                                              readFromFile("par.txt");
                                              ParametricFile.getInstance().GameFigure=wlasnosci.getProperty("figuraObiektuGry");
                                         }
                                     }
        );


    }

    /**
     * Metoda modyfikuje wartość zmiennej GameFigure dla każdego z obrazków, by później na podstawie tej wartości utworzyć poszczególne obiekty
     *
     * @param image
     */
    public void setObjectIdentifiers(ImageView image) {
        if (image == Kolko) {
            ParametricFile.getInstance().GameFigure = "kółka";
        } else if (image == Kwadrat) {
            ParametricFile.getInstance().GameFigure = "kwadraty";
        } else if (image == Trojkat) {
            ParametricFile.getInstance().GameFigure = "trójkąty";
        } else if (image == Pilka) {
            ParametricFile.getInstance().GameFigure = "plikGraficzny";
        }
    }

    /**
     * Metoda ustawia dużą ilość amunicji i czasu, by zapobiec zmianie poziomów w trakcie rozgrywki treningowej
     */
    public void setTrainingParameters() {
        LevelFile reader = LevelFile.getInstance();
        reader.readFile();
        reader.LevelTime = "999999";
        reader.Ammo = "9999999";
        reader.PointsForHit = "0";
    }

    /**
     * Metoda włączająca tryb rozgrzewkowy
     *
     * @param image
     * @param level
     */

    public void setTrainingGame(ImageView image, Level level) {

        saveLevelToFile(level);
        setTrainingParameters();
        GameStage stage = new GameStage();
        stage.setClosable();
        setObjectIdentifiers(image);
        stage.startTraining();
    }

    /**
     * Metoda zwiększa wymiary obrazka gdy użytkownik nakieruje na niego kursor myszy.
     * Ładuje odpowiedni poziom i tryb rozgrzewkowy po naciśnięciu na przycisk
     *
     * @param image
     */

    public void setChoosable(ImageView image) {
        image.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                image.setFitHeight(130);
                image.setFitWidth(130);
            }
        });
        image.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                image.setFitHeight(80);
                image.setFitWidth(100);
            }
        });
        image.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    FXMLLoader loader = setPopUpWindow("FXMLs/LevelChooser.fxml");
                    LevelChooserController controller = loader.getController();
                    controller.silverLevelButton.setOnMousePressed(new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            closeWindow(mouseEvent);
                            SilverLevel level = new SilverLevel();
                            setTrainingGame(image, level);
                        }
                    });

                    controller.goldLevelButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            closeWindow(mouseEvent);
                            GoldLevel level = new GoldLevel();
                            setTrainingGame(image, level);
                        }

                    });

                    controller.eagleLevelButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            closeWindow(mouseEvent);
                            EagleLevel level = new EagleLevel();
                            setTrainingGame(image, level);
                        }


                    });
                    controller.supremeLevelButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            closeWindow(mouseEvent);
                            SupremeLevel level = new SupremeLevel();
                            setTrainingGame(image, level);
                        }

                    });

                    controller.globalLevelButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            closeWindow(mouseEvent);
                            GlobalLevel level = new GlobalLevel();
                            setTrainingGame(image, level);
                        }

                    });


                } catch (IOException ie) {
                    System.out.println("Failed to load LevelChooser");
                }
            }
        });
    }
}


