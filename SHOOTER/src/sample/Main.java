package sample;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Klasa rozpoczynająca działanie aplikacji
 */
public class Main extends Application {


    /**
     * Rozpoczyna działanie aplikacji - ładuje plik FXML do sceny i wyświetla okno
     * @param primaryStage
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException{


        GeneratorPlikuParametrycznegoGry generator=new GeneratorPlikuParametrycznegoGry();
        generator.zapiszPlikParametryczny("par.txt");
        FXMLLoader loader= new FXMLLoader(getClass().getResource("FXMLs/StartMenu.fxml"));
        Parent root =loader.load();
        Scene scene =new Scene(root,600,400);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e-> Platform.exit());
        primaryStage.setResizable(false);
        Stage stage1=new Stage();

        StartMenuController x=loader.getController();
        x.setScenesToButtons(stage1);


        /*Media pick = new Media(new File("ema.mp3").toURI().toString()); // replace this with your own audio file
        player = new MediaPlayer(pick);
        player.play();*/
    }





    public static void main(String[] args)  {
        launch(args);
    }
}
