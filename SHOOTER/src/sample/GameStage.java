package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Figures.FigureCreator;
import sample.Levels.LevelOne;

import java.io.IOException;

/**
 * Klasa tworząca główne okno gry i wyświetlająca na nim obiekty oraz animacje
 */
public class GameStage extends Controller {

    public AnchorPane anchorPane;
    public Scene scene;
    public Stage stage;
    public Label ScoreLabel;
    public FXMLLoader loader;
    public ImageView Kolko;


    /**
     * Konstruktor tworzy planszę gry
     */

GameStage()
{
    try {
        fileName=ParametricFile.getInstance().Filename;
        sceneName = "FXMLs/GameScene.fxml";
        PopUpWindowName = "FXMLs/EscapeMenu.fxml";
        loader = new FXMLLoader(getClass().getResource(sceneName));
        Parent root = loader.load();
        stage = new Stage();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setTitle(ParametricFile.getInstance().GameName);
    }
    catch(IOException ie)
    {
        System.out.println("Failed to load GameStage");
    }
}

    /**
     * Otwiera menu z opcjami po naciśnięciu ESC i zatrzymuje grę. Wznawia grę po naciśnięciu RESUME. Wraca do menu głównego po naciśnięciu BACK TO MENU
     */
    public void setClosable() {
        GameSceneController x=loader.getController();

    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            if (e.getEventType() == KeyEvent.KEY_PRESSED && e.getCode() == KeyCode.ESCAPE) {

                x.time.stop();

                try {
                    FXMLLoader loader=setPopUpWindow("FXMLs/EscapeMenu.fxml");
                    EscapeMenuController controller = loader.getController();
                     stage=(Stage)controller.ResumeButton.getScene().getWindow();
                    scene=stage.getScene();
                    controller.ResumeButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            stage.close();
                            x.time.play();

                        }
                    });
                    controller.BackToMenuButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {

                          try{
                              FXMLLoader loader=setPopUpWindow("FXMLs/ConfirmationWindow.fxml");
                              ConfirmationWindowController confirmController=loader.getController();
                              confirmController.YesButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                                  @Override
                                  public void handle(MouseEvent mouseEvent) {
                                      closeWindow(mouseEvent);
                                      stage.close();
                                      stage=(Stage)x.anchorPane1.getScene().getWindow();
                                      stage.close();

                                  }
                              });
                          }
                          catch (IOException ie){
                              System.out.println("Failed to load confirmation window");
                          }
                        }
                    });

                    controller.OptionsButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {

                            try {

                                x.anchorPane2.getStyleClass().add("anchor");
                                FXMLLoader loader = setPopUpWindow("FXMLs/SettingsMenu.fxml");
                                SettingsMenuController controller1=loader.getController();
                                controller1.FootballPitch.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {
                                        setNewBackground(controller1.FootballPitch,x);
                                    }
                                });
                                controller1.Basketball.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {
                                        setNewBackground(controller1.Basketball,x);
                                    }
                                });
                                controller1.TennisCourt.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {
                                        setNewBackground(controller1.TennisCourt,x);
                                    }
                                });



                                //setNewBackground(controller,x,controller1.FootballPitch);
                                //controller1.setNewBackground(controller,x,controller1.Basketball);
                                //controller1.setNewBackground(controller,x,controller1.TennisCourt);


                            }
                            catch(IOException ie)
                            {
                                System.out.println("Failed to load SettingsMenu");
                            }
                        }
                    });


                    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent keyEvent) {
                            if (e.getEventType() == KeyEvent.KEY_PRESSED && e.getCode() == KeyCode.ESCAPE) {
                                x.time.play();
                            }

                        }
                    });


                } catch (IOException ie) {
                    System.out.println("Failed to load escape menu");
                }
            }
        }
    });
}

    /**
     *Generuje nowe tło i przypisuje do planszy
     * @param menuItem
     * @param x
     */

public void setNewBackground(MenuItem menuItem, GameSceneController x)
{
    x.anchorPane2.getStylesheets().clear();
    if(menuItem.getText().equals("FOOTBALL PITCH")) {

        x.anchorPane2.getStylesheets().add("anchor1.css");

    }
    else if(menuItem.getText().equals("TENNIS COURT")) {

        x.anchorPane2.getStylesheets().add("anchor.css");
    }
    else if(menuItem.getText().equals("BASKETBALL")) {

        x.anchorPane2.getStylesheets().add("anchor2.css");
    }
}

    /**
     * Rozpoczyna działanie gry
     */
    public void start()
       {
                    GameSceneController x=loader.getController();
                    x.setGame(scene);
       }



    /**
     * Tworzy obiekty i wywołuje dla nich animacje w zależności od wybranego obiektu
     */
    public void startTraining()
{
    GameSceneController x=loader.getController();
    x.AmmoLabel.setVisible(false);
    x.ScoreLabel.setVisible(false);
    x.TimeLabel.setVisible(false);
    x.LevelLabel.setVisible(false);
    FigureCreator creator=new FigureCreator(scene, x.anchorPane2, x.ScoreLabel, x.AmmoLabel, x.TimeLabel);


    if(ParametricFile.getInstance().GameFigure.equals("kółka")) {
        creator.getCircles();
    }
    else if(ParametricFile.getInstance().GameFigure.equals("kwadraty"))
    {
        creator.getRectangles();
    }
    else if(ParametricFile.getInstance().GameFigure.equals("trójkąty"))
    {
        creator.getTriangles();
    }
    else
    {
        creator.getCircles();
        creator.addGraphics();
    }

    }
}










